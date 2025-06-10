<template>
  <Header />
  <v-container max-width="94%">
    <div style="display: flex">
      <Sitebar />
      <div class="list-product">
        <v-text-field
          v-model="selectSearch.keyword"
          append-inner-icon="mdi-magnify"
          density="compact"
          label="Tìm kiếm sản phẩm"
          variant="outlined"
          hide-details
          @change="updateQuery"
        />

        <p class="text-arrange" @click="onclickArrange">
          {{ t('list-product.btn-arrange') }}
          <v-icon>mdi-chevron-down</v-icon>
        </p>

        <p class="text-not-found" v-if="products.length === 0">
          {{ t('list-product.text-not-found') }}
        </p>
        <v-list class="list-arrange" v-if="showListArrange">
          <v-list-item @click="getAll">{{ t('list-product.new-to-old') }}</v-list-item>
          <v-list-item @click="arrangeOldToNew">{{ t('list-product.old-to-new') }}</v-list-item>
          <v-list-item @click="arrangePriceAsc">{{ t('list-product.price-asc') }}</v-list-item>
          <v-list-item @click="arrangePriceDesc">{{ t('list-product.price-desc') }}</v-list-item>
        </v-list>

        <v-list class="product-list">
          <v-card
            class="ma-4"
            height="380"
            width="249"
            style="box-shadow: none"
            v-for="(product, index) in products"
            :key="index"
            :to="`/product-detail/${product.id}`"
          >
            <div class="">
              <img :src="product.imageUrl" class="img-product" alt="" />
              <div class="color-options">
                <v-btn>
                  v-for="(color, i) in product.color" :key="i" class="mx-1 color-btn" icon
                  width="20" height="20" :style="{ backgroundColor: color }">
                </v-btn>
              </div>
              <p class="name-product">{{ product.productName }}</p>
              <p class="brand-name">{{ product.brandName }}</p>

              <div style="display: flex">
                <p class="price" :class="{ 'origin-price': product.discountPercentage }">
                  {{ formatPrice(product.price) }}
                </p>

                <p class="discount-price display-discount" v-if="product.discountPercentage">
                  {{ formatPrice(product.price * (1 - product.discountPercentage / 100)) }}
                </p>
                <p class="product-discount-badge" v-if="product.discountPercentage">
                  {{ product.discountPercentage }} %
                </p>
              </div>
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
import { type Product } from '@/interface/interface.ts'
import { onMounted, onUnmounted, ref, watch } from 'vue'
import { useI18n } from 'vue-i18n'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const { t } = useI18n()
const showListArrange = ref(false)
const products = ref<Product[]>([])

const selectSearch = ref<{ keyword: string }>({ keyword: '' })
const page = ref(0)
const size = 16
const isLoading = ref(false)
const hasMore = ref(true)

const formatPrice = (price: number) => {
  return new Intl.NumberFormat('vi-VN', { maximumFractionDigits: 0 }).format(price) + ' đ'
}

const onclickArrange = () => {
  showListArrange.value = !showListArrange.value
}

const updateQuery = () => {
  const params: Record<string, string> = {}
  if (selectSearch.value.keyword) {
    params.keyword = selectSearch.value.keyword
  }
  router.push({ path: '/list-product', query: params })
}

const getAll = async () => {
  if (isLoading.value || !hasMore.value) return
  isLoading.value = true

  const params = {
    gender: route.query.gender,
    color: route.query.color,
    brand: route.query.brand,
    collection: route.query.collection,
    price: route.query.price,
    discount: route.query.discount,
    keyword: route.query.keyword,
    page: page.value,
    size: size,
  }

  try {
    const res = await productService.getAllProduct(params)
    const newProducts = res.data.content
    if (newProducts.length < size) {
      hasMore.value = false
    }
    products.value.push(...newProducts)
    page.value++
  } finally {
    isLoading.value = false
  }
}

const arrangeOldToNew = async () => {
  const res = await productService.findAllProductOrderByAsc()
  products.value = res.data
}

const arrangePriceAsc = async () => {
  const res = await productService.findAllProductOrderByPriceAsc()
  products.value = res.data
}

const arrangePriceDesc = async () => {
  const res = await productService.findAllProductOrderByPriceDesc()
  products.value = res.data
}

const handleScroll = () => {
  const scrollTop = window.scrollY //Lấy vị trí cuộn hiện tại
  const windowHeight = window.innerHeight //Chiều cao vùng hiển thị của trình duyệt (viewport).
  const fullHeight = document.documentElement.scrollHeight //Chiều cao toàn bộ nội dung trang, kể cả phần vượt khỏi vùng hiển thị.
  //Khi người dùng cuộn xuống gần cuối (cách đáy 300px)
  if (scrollTop + windowHeight >= fullHeight - 300) {
    getAll()
  }
}

watch(
  () => route.query,
  () => {
    products.value = []
    page.value = 0
    hasMore.value = true
    getAll()
  },
  { deep: true },
)

onMounted(() => {
  window.addEventListener('scroll', handleScroll)
  getAll()
})

onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
})
</script>
