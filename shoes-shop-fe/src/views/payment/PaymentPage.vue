<template>
  <Header />
  <v-container style="margin-bottom: 15px;">
    <v-row >
      <v-col cols="7">
        <v-sheet>
          <h2 class="bold-text">Thông tin liên lạc</h2>
          <v-text-field label="Email" variant="outlined" hide-details density="compact" v-model="payment.buyerEmail"/>
          <v-checkbox label="Gửi email cho tôi tin tức và ưu đãi qua mail" hide-details />
        </v-sheet>

        <v-sheet>
          <h2 class="bold-text">Giao hàng</h2>

          <v-select
            label="Tỉnh/thành phố"
            :items="listProvinces"
            item-title="ProvinceName"
            item-value="ProvinceID"
            v-model="payment.province"
            variant="outlined"
            hide-details
            density="compact"
            @update:model-value="onProvinceChange"
          />
          <div class="input-location">
            <v-select
              label="Quận/huyện"
              :items="listDistricts"
              item-title="DistrictName"
              item-value="DistrictID"
              v-model="payment.district"
              variant="outlined"
              hide-details
              density="compact"
              style="margin-right: 10px"
              :disabled="!isSelectedProvince"
              @update:model-value="onDistrictChange"
            />

            <v-select
              label="Phường/xã"
              :items="listCommunes"
              item-title="WardName"
              item-value="WardCode"
              v-model="payment.commune"
              variant="outlined"
              hide-details
              density="compact"
              :disabled = "!isSelectedDistrict"
              @update:model-value="caculationFee"
            />
          </div>

            <v-text-field
              label="Tên"
              style="margin-bottom: 10px"
              variant="outlined"
              hide-details
              density="compact"
              v-model="payment.buyerName"
            ></v-text-field>

          <v-text-field
            label="Địa chỉ"
            variant="outlined"
            hide-details
            density="compact"
            align-top="5"

          ></v-text-field>
          <v-text-field
            label="Số điện thoại"
            variant="outlined"
            hide-details
            density="compact"
            style="margin-top: 10px"
            v-model="payment.buyerPhoneNumber"
          ></v-text-field>
          <v-checkbox label="Lưu thông tin này" hide-details />
        </v-sheet>

        <v-sheet>
          <h2 class="bold-text">Thanh toán</h2>
          <v-radio-group v-model="payment.paymentType">
            <v-radio label="Thanh toán khi nhận hàng (COD)" value="COD" />
            <v-radio label="Thanh toán bằng chuyển khoản" value="BANK_TRANSFER" />
          </v-radio-group>

          <v-checkbox label="Yêu cầu xuất hóa đơn" hide-details />
          <v-checkbox
            label="Tôi đồng ý với Điều khoản và Chính sách quy định bởi shop"
            hide-details
          />
        </v-sheet>
        <v-btn class="btn">THANH TOÁN NGAY</v-btn>
      </v-col>
      <v-col cols="5" class="show-info-order">

        <v-sheet class="summary-order-product">
           <div class="product-info-payment" v-for="pro in selectedItems " :key="pro.productDetailId">
             <img
               :src="pro.imageUrl"
               class="img-product-payment"
               alt=""
             />
             <div  class="order-summary">
               <p class="bold-text">{{pro.productName}}</p>
               <p class="brand-name">{{pro.colorName}}/{{pro.sizeValue}}</p>
             </div>
             <div class="product-quantity">
               <p>{{formatPrice(pro.finalPrice)}}</p>
               <p class="brand-name">x{{pro.quantity}}</p>
             </div>
           </div>
        </v-sheet>

        <div style="display: flex; align-items: center">
          <v-text-field
            label="Số điện thoại"
            variant="outlined"
            hide-details
            density="compact"
          ></v-text-field>
          <v-btn variant="outlined" style="margin-left: 10px">Áp dụng</v-btn>
        </div>

        <div class="wrap-div">
          <p>Tổng tiền hàng:</p>
          <p>{{formatPrice(totalPrice)}}</p>
        </div>
        <div class="wrap-div">
          <p>Phí vận chuyển:</p>
          <p>{{formatPrice(shippingFee)}}</p>
        </div>
        <div class="wrap-div">
          <h3 class="bold-text">Tổng:</h3>
          <h3 class="bold-text">{{formatPrice(totalPrice + shippingFee)}}</h3>
        </div>

      </v-col>
    </v-row>
  </v-container>
  <Footer />
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'
import { useCartStore } from '@/stores/cartStore'
import { onMounted, ref } from 'vue'
import {paymentService} from '@/services/PaymentService.ts'
import {type ShippingFee} from '@/interface/interface.ts'
import {Payment} from '@/model/Payment.ts'

const cartStore = useCartStore()
const selectedItems = cartStore.selectedCartItems
const totalPrice = cartStore.totalPrice;
const listProvinces = ref<[]>([])
const listDistricts = ref<[]>([])
const listCommunes = ref<[]>([])
const payment = ref(new Payment())
const shippingFee = ref<number>(0)
const isSelectedProvince = ref(false)
const isSelectedDistrict = ref(false)
const formatPrice=(price: number) =>{
  return Math.round(price).toLocaleString("vi-VN") + " đ"
}
const getProvinces = async () => {
  const res = await paymentService.getProvince()
  if (res.code === 200 && Array.isArray(res.data)) {
    listProvinces.value = res.data
  }
}

const getDistricts = async (provinceId: number) => {
  const res = await paymentService.getDistrict(provinceId)
  if (res.code === 200 && Array.isArray(res.data)) {
    listDistricts.value = res.data
  }
}

const getCommune = async (districtId: number) => {
  const res = await paymentService.getCommune(districtId)
  if (res.code === 200 && Array.isArray(res.data)) {
    listCommunes.value = res.data

  }
}

const getShippingFee = async () => {
  // Chỉ gửi khi đủ điều kiện
  if (!payment.value.district || !payment.value.commune) return

  const payload: ShippingFee = {
    from_district_id: 1450, // Mã cố định kho xuất phát của shop
    to_district_id: payment.value.district,
    to_commune_code: payment.value.commune,
    height: 10,
    length: 20,
    weight: 1000,
    width: 15,
    insurance_value: totalPrice || 0,
  }

  try {
    const res = await paymentService.calculateShippingFee(payload)
    if (res.code === 200 && res.data?.total) {
      shippingFee.value = res.data.total
    }
  } catch (error) {
    console.error("Lỗi lấy phí ship:", error)
  }
}
const onProvinceChange = async (provinceId: number | null) => {
  if (provinceId) {
    isSelectedProvince.value = true
    payment.value.district = null // reset huyện cũ
    await getDistricts(provinceId)
  } else {
    isSelectedProvince.value = false
    listDistricts.value = []
    payment.value.district = null
  }
}
const onDistrictChange= async (districtId: number | null) => {
  if (districtId) {
    isSelectedDistrict .value = true
    await getCommune(districtId)
  }
  else{
    isSelectedDistrict.value = false
    listCommunes.value = []
    payment.value.district = null
  }
}

const caculationFee = async (communeId: number | null) => {
  if (communeId){
    await getShippingFee()
  }
}
// watch(() => payment.value.province, (newVal) => {
//   isSelectedProvince.value = newVal !== null && newVal !== undefined
//
// // Auto-load districts when a province is selected
//   if (newVal) {
//     getDistricts(newVal)
//   } else {
//     listDistricts.value = []
//     payment.value.district = null
//   }
// })

//
// watch(selectedDistrictId, (newVal) => {
//   if (newVal) {
//     getCommune(newVal)
//     selectedWardId.value = null
//   }
// })
// watch([selectedDistrictId, selectedWardId], ([district, ward]) => {
//   if (district && ward) {
//     getShippingFee()
//   }
// })
onMounted(() => {
   getProvinces()
})
</script>
