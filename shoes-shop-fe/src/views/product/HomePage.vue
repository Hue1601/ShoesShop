<template>
  <Header />
  <v-container max-width="90%">
    <h2 class="bold-text">{{ t('home-page.new-product') }}</h2>
    <v-sheet max-width="100%">
      <v-slide-group class="pa-4" selected-class="bg-success" show-arrows>
        <v-slide-group-item v-for="(product, index) in products" :key="index">
          <v-card
            class="ma-4"
            height="380"
            width="230"
            style="box-shadow: none"
            :to="`/product-detail/${product.id}`"
          >
            <img :src="product.imageUrl" class="img-product" alt="" />
            <p class="name-product">{{ product.productName }}</p>
            <p class="brand-name">{{ product.brandName }}</p>

            <div v-if="product.discountPercentage" style="display: flex;justify-content: space-between;">
              <p class="price" :class="{ 'origin-price': product.discountPercentage }">
                {{ formatPrice(product.price) }}
              </p>
              <p class="product-discount-badge" v-if="product.discountPercentage">
                {{ product.discountPercentage }} %
              </p>
            </div>
            <p class="discount-price" v-if="product.discountPercentage">
              {{ formatPrice(product.price * (1 - product.discountPercentage / 100)) }}
            </p>
          </v-card>
        </v-slide-group-item>
      </v-slide-group>
    </v-sheet>

    <h2 class="bold-text">{{ t('home-page.best-selling-product') }}</h2>
    <v-sheet max-width="100%">
      <v-slide-group class="pa-4" selected-class="bg-success" show-arrows>
        <v-slide-group-item v-for="(product, index) in latestCollection" :key="index">
          <v-card
            class="ma-4"
            height="400"
            width="275"
            style="box-shadow: none"
            :to="`/product-detail/${product.id}`"
          >
            <img :src="product.imageUrl" class="img-product" alt="" />
            <!--              <v-btn  class="mx-1 " icon width="20" height="20" >color</v-btn>-->
            <p class="name-product">{{ product.productName }}</p>
            <p class="brand-name">{{ product.brandName }}</p>
            <p class="price">{{ formatPrice(product.price) }}</p>
          </v-card>
        </v-slide-group-item>
      </v-slide-group>
    </v-sheet>
  </v-container>
  <Footer />
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'

import { useI18n } from 'vue-i18n'
import { productService } from '@/services/ProductService.ts'
import { ref, onMounted } from 'vue'
import { type Product, type LatestCollection } from '@/interface/interface.ts'

const { t } = useI18n()

const products = ref<Product[]>([])
const latestCollection = ref<LatestCollection[]>([])

const listProduct = async () => {
  try {
    const res = await productService.getProduct()
    products.value = res.data
  } catch (e) {
    console.log(e)
  }
}

const getProductByLatestCollection = async () => {
  try {
    const response = await productService.getProductByLatestCollection()
    latestCollection.value = response.data
  } catch (e) {
    console.log(e)
  }
}

const formatPrice = (price: number) => {
  return Math.round(price).toLocaleString('vi-VN') + ' ₫';
}

onMounted(() => {
  listProduct()
  getProductByLatestCollection()
})
</script>
