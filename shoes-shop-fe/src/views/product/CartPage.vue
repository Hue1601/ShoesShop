<template>
  <Header />
  <v-container style="min-height: 550px; ">
    <h2 class="bold-text">{{ t('cart.cart') }}</h2>
    <div class="product-cart" style="height: 45px">
      <v-checkbox
        v-model="selectAll"
      ></v-checkbox>

    </div>

    <div class="cart-container">
      <div class="product-in-cart">
        <div class="product-cart" v-for="item in cart" :key="item.productDetailId">
          <v-checkbox
            :model-value="isSelected(item.productDetailId)"
            @change="toggleSelectItem(item.productDetailId)"
          ></v-checkbox>

          <img
            :src="item.imageUrl"
            class="img-product-cart"
            alt=""
          />
          <div class="product-info">
            <p>{{item.brandName}}</p>
            <p class="name-product-cart">{{item.productName}}</p>
            <p class="attribute-product">{{item.colorName}} - {{item.sizeValue}}</p>
          </div>

          <div>
            <p class="product-price" :class="{'origin-price' : item.discountPercentage}">{{formatPrice(item.price)}}</p>
            <p v-if="item.discountPercentage" class="discount-price" style="font-size: 13px">{{formatPrice(item.price*(1-item.discountPercentage/100))}}</p>
          </div>

          <v-text-field
            type="number"
            variant="outlined"
            density="compact"
            class="quantity"
            hide-details
            v-model="item.quantity"
            @change="updateQuantity(item)"
          ></v-text-field>
          <div>
            <p class="product-price">{{ formatPrice(getFinalPrice(item)) }}</p>
            <p class="text-trash" @click="deleteProduct(item.productDetailId)">{{ t('cart.delete') }}</p>
          </div>
        </div>
      </div>

      <div class="total">
        <v-row class="row">
          <v-col>
            <p class="right-text">({{ totalQuantity }}) sản phẩm</p>
          </v-col>
          <v-col>
            <p class="total-price">{{ formatPrice(totalPrice) }}</p>
          </v-col>
        </v-row>

        <v-divider />

        <v-row class="row">
          <v-col>
            <p class="right-text">{{ t('cart.discount') }}</p>
          </v-col>
          <v-col>
            <p class="left-text">{{ t('cart.discount-note') }}</p>
          </v-col>
        </v-row>

        <v-divider />

        <v-row class="row">
          <v-col>
            <p class="right-text">{{ t('cart.shipping-fee') }}</p>
          </v-col>
          <v-col>
            <p class="left-text">{{ t('cart.shipping-note') }}</p>
          </v-col>
        </v-row>
        <v-divider />
        <v-row class="row">
          <v-col>
            <p class="right-text">{{ t('cart.into-money') }}</p>
          </v-col>
          <v-col>
            <p class="total-price">1.195.000 đ</p>
          </v-col>
        </v-row>

        <v-btn class="btn" to="/payment">{{ t('cart.btn-payment') }}</v-btn>
        <p class="notice">{{ t('cart.note') }}</p>
      </div>
    </div>
  </v-container>
  <Footer />
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'
import { useI18n } from 'vue-i18n'
import { cartService } from '@/services/CartService.ts'
import { computed, onMounted, ref, watch } from 'vue'
import { type Cart } from '@/interface/interface.ts'
import debounce from 'lodash/debounce';

const { t } = useI18n()
const cart = ref<Cart[]>([])
const selectAll = ref(false)
const selectedIds = ref<number[]>([])

const getCart = async () => {
  const id = localStorage.getItem('userId')
  if(id === null){
    alert("vui lòng đăng nhập ")
    return
  }
  const response = await cartService.getCart(id)
  cart.value = response.data
}

const updateQuantity = debounce (async (item) => {
  if (item.quantity < 1) {
    item.quantity = 1;
  }
  try {
    await cartService.updateQuantity(item.productDetailId, item.quantity);
  } catch (error) {
    console.error('Cập nhật số lượng thất bại', error);
  }
}, 500); // Đợi 500ms sau khi người dùng ngừng nhập mới gửi request

const deleteProduct = async (id:number) =>{
   await cartService.deleteProduct(id)
  if(cart.value === undefined) return
  cart.value = cart.value.filter(item => item.productDetailId !== id);
}
const formatPrice=(price: number) =>{
 // return Math.round(price).toLocaleString('vi-VN' + ' đ')
  return Math.round(price).toLocaleString('vi-VN') + ' ₫';
}
const getFinalPrice = (item: Cart) => {
  const discount = item.discountPercentage || 0;
  return item.price * (1 - discount / 100) * item.quantity;
};

function toggleSelectItem(productDetailId:number) {
  const index = selectedIds.value.indexOf(productDetailId)
  if (index === -1) {
    selectedIds.value.push(productDetailId)
  } else {
    selectedIds.value.splice(index, 1)
  }
}

const isSelected = (id:number) => selectedIds.value.includes(id)

watch(selectAll, (val) => {
  if (val) {
    selectedIds.value = cart.value.map(item => item.productDetailId)
  } else {
    selectedIds.value = []
  }
})

const selectedItems = computed(() =>
  cart.value.filter(item => selectedIds.value.includes(item.productDetailId))
)

const totalQuantity = computed(() => selectedItems.value.length)

const totalPrice = computed(() =>
  selectedItems.value.reduce((acc, item) => {
    const finalPrice = item.discountPercentage
      ? item.price * (1 - item.discountPercentage / 100)
      : item.price
    return acc + finalPrice * item.quantity
  }, 0)
)
onMounted(() => {
   getCart()

})
</script>
