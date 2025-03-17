<template>
  <Header />
  <v-container max-width="94%">
    <div style="display: flex">
      <Sitebar />
      <div class="list-product">
        <v-text-field
          v-model="searchQuery"
          append-inner-icon="mdi-magnify"
          density="compact"
          label="Tìm kiếm sản phẩm"
          variant="outlined"
          hide-details
        />


        <p class="text-arrange" @click="onclickArrange">
          {{ t('list-product.btn-arrange') }}
          <v-icon>mdi-chevron-down</v-icon>
        </p>

        <v-list class="list-arrange" v-if="showListArrange">
          <v-list-item @click="getAll">{{ t('list-product.new-to-old') }}</v-list-item>
          <v-list-item @click="
          arrangeOldToNew">{{ t('list-product.old-to-new') }}</v-list-item>
          <v-list-item @click="arrangePriceAsc">{{ t('list-product.price-asc') }}</v-list-item>
          <v-list-item @click="arrangePriceDesc">{{ t('list-product.price-desc') }}</v-list-item>
        </v-list>

        <v-list class="product-list">
          <v-card
            class="ma-4"
            height="380"
            width="230"
            style="box-shadow: none"
            v-for="(product, index) in filteredProducts"
            :key="index"
            :to="`/product-detail/${product.id}`"
          >

          <div class="">
              <img :src="product.imageUrl" class="img-product" alt="" />
<!--              <div class="color-options">-->
<!--                <v-btn-->
<!--                  v-for="(color, i) in product.color"-->
<!--                  :key="i"-->
<!--                  class="mx-1 color-btn"-->
<!--                  icon-->
<!--                  width="20"-->
<!--                  height="20"-->
<!--                  :style="{ backgroundColor: color }">-->
<!--                </v-btn>-->
<!--              </div>-->
              <p class="name-product">{{ product.productName }}</p>
              <p class="brand-name">{{ product.brandName }}</p>
              <p class="price">{{ formatPrice(product.price) }} </p>
            </div>
          </v-card>
        </v-list>
      </div>
    </div>
  </v-container>
  <Footer />
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'
import Sitebar from '../../components/common/SitebarPage.vue'

import { productService } from '@/services/ProductService.ts'
import router from '@/router'
import { computed, onMounted, ref } from 'vue'
import { useI18n } from 'vue-i18n'

const { t } = useI18n()

const showListArrange = ref(false)

interface Product {
  id:number
  productName: string
  brandName: string
  price: number
  imageUrl: string
}
const formatPrice = (price:number) => {
    return new Intl.NumberFormat("vi-VN", { maximumFractionDigits: 0 }).format(price) + " đ";
};

const products = ref<Product[]>([])
const searchQuery = ref<string>('')

const onclickArrange = () => {
  showListArrange.value = !showListArrange.value
}


const getAll = async () => {
  const res = await productService.getAllProduct()
  products.value = res.data
}

const arrangeOldToNew = async () => {
  const res = await productService.findAllProductOrderByAsc()
  products.value = res.data
}
const arrangePriceAsc =async () => {
  const res = await productService.findAllProductOrderByPriceAsc()
  products.value = res.data
}
const arrangePriceDesc =async () => {
  const res = await productService.findAllProductOrderByPriceDesc()
  products.value = res.data
}

const filteredProducts = computed(() =>{
  if(!products.value) return products.value
  return products.value.filter(products =>
   products.productName.toLowerCase().includes(searchQuery.value.toLowerCase())
  )
})

onMounted(() => {
  getAll()
})
</script>
