<template>
  <Header />
  <v-container style="margin-bottom: 15px;">
    <v-row >
      <v-col cols="7">
        <v-sheet>
          <h2 class="bold-text">Thông tin liên lạc</h2>
          <v-text-field label="Email" variant="outlined" hide-details density="compact" />
          <v-checkbox label="Gửi email cho tôi tin tức và ưu đãi qua mail" hide-details />
        </v-sheet>

        <v-sheet>
          <h2 class="bold-text">Giao hàng</h2>

          <v-select
            label="Tỉnh/thành phố"
            :items="provinces"
            item-title="ProvinceName"
            item-value="ProvinceID"
            v-model="selectedProvinceId"
            variant="outlined"
            hide-details
            density="compact"
          />
          <div class="input-location">
            <v-select
              label="Quận/huyện"
              :items="districts"
              item-title="DistrictName"
              item-value="DistrictID"
              v-model="selectedDistrictId"
              variant="outlined"
              hide-details
              density="compact"
              style="margin-right: 10px"
            />

            <v-select
              label="Phường/xã"
              :items="commune"
              item-title="WardName"
              item-value="WardCode"
              v-model="selectedWardId"
              variant="outlined"
              hide-details
              density="compact"
            />
          </div>

          <div class="input-location">
            <v-text-field
              label="Tên"
              style="margin-right: 10px"
              variant="outlined"
              hide-details
              density="compact"
            ></v-text-field>
            <v-text-field
              label="Họ"
              variant="outlined"
              hide-details
              density="compact"
            ></v-text-field>
          </div>

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
          ></v-text-field>
          <v-checkbox label="Lưu thông tin này" hide-details />
        </v-sheet>

        <v-sheet>
          <h2 class="bold-text">Thanh toán</h2>
          <v-radio label="Thanh toán khi nhận hàng (COD)" />
          <v-radio label="Thanh toán bằng chuyển khoản" />
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
import { onMounted, ref, watch } from 'vue'
import {paymentService} from '@/services/PaymentService.ts'
import {type ShippingFee} from '@/interface/interface.ts'
const cartStore = useCartStore()

const selectedItems = cartStore.selectedCartItems
const totalPrice = cartStore.totalPrice;
const provinces = ref<[]>([])
const districts = ref<[]>([])
const commune = ref<[]>([])
const selectedProvinceId = ref<number | null | undefined>(null)
const selectedDistrictId = ref<number | null | undefined>(null)
const selectedWardId = ref<number | null | undefined>(null)
const shippingFee = ref<number>(0)

const formatPrice=(price: number) =>{
  return Math.round(price).toLocaleString("vi-VN") + " đ"
}
const getProvinces = async () => {
  const res = await paymentService.getProvince()
  if (res.code === 200 && Array.isArray(res.data)) {
    provinces.value = res.data
  }
}

const getDistricts = async (provinceId: number) => {
  const res = await paymentService.getDistrict(provinceId)
  if (res.code === 200 && Array.isArray(res.data)) {
    districts.value = res.data
  }
}

const getCommune = async (districtId: number) => {
  const res = await paymentService.getCommune(districtId)
  if (res.code === 200 && Array.isArray(res.data)) {
    commune.value = res.data
  }
}

const getShippingFee = async () => {
  // Chỉ gửi khi đủ điều kiện
  if (!selectedDistrictId.value || !selectedWardId.value) return

  const payload: ShippingFee = {
    from_district_id: 1450, // Mã cố định kho xuất phát của bạn
    to_district_id: selectedDistrictId.value,
    to_commune_code: selectedWardId.value,
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
      console.warn(" vận chuyển:", res.data.total)
    } else {
      console.warn("Không lấy được phí vận chuyển:", res)
    }
  } catch (error) {
    console.error("Lỗi lấy phí ship:", error)
  }
}
watch(selectedProvinceId, (newVal) => {
  if (newVal) {
    getDistricts(newVal)
    selectedDistrictId.value = null
    commune.value = []
  }
})

watch(selectedDistrictId, (newVal) => {
  if (newVal) {
    getCommune(newVal)
    selectedWardId.value = null
  }
})
watch([selectedDistrictId, selectedWardId], ([district, ward]) => {
  if (district && ward) {
    getShippingFee()
  }
})
onMounted(() => {
  console.log("Aaa " + cartStore.selectedCartItems)
   getProvinces()
})
</script>
