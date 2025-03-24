<template >
  <v-toolbar class="header" density="comfortable">
    <span class="shop-name">{{ t('header.shop-name') }}</span>
    <router-link to="/home" class="header-item text"> {{ t('header.home') }}</router-link>
    <router-link to="/list-product" class="header-item text">
      {{ t('header.product') }}
    </router-link>

    <v-menu open-on-hover close-on-content-click>
      <template v-slot:activator="{ props }">
        <div class="brand-wrapper" v-bind="props" style="display: flex; align-items: center">
          <router-link to="/list-brand" class="header-item text">
            {{ t('header.brand') }}
          </router-link>
          <v-icon class="dropdown-icon">mdi-chevron-down</v-icon>
        </div>
      </template>

      <v-card class="brand-dropdown">
        <v-container>
          <v-row>
            <ul>
              <li v-for="brand in listBrand" :key="brand.id">
                <router-link :to="`/list-product?brand=${brand.id}`" class="dropdown-item text">
                  {{ brand.brandName }}
                </router-link>
              </li>
            </ul>
          </v-row>
        </v-container>
      </v-card>
    </v-menu>

    <router-link to="/register" class="header-item text">{{ t('header.introduce') }}</router-link>

    <v-text-field
      append-inner-icon="mdi-magnify"
      density="compact"
      label="Search "
      variant="solo"
      hide-details
      single-line
      style="margin-right: 5px"
      v-model="selectSearch.keyword"
      @change="updateQuery"
    ></v-text-field>

    <img
      src="../../components/icons/listicon/Cart.png"
      class="icon-header"
      alt=""
      @click="() => router.push('/cart')"
    />
    <img src="../../components/icons/listicon/User.png" class="icon-header" alt="" />
  </v-toolbar>

  <v-card class="card-search" v-if="selectSearch.keyword && filterProduct.length > 0">
       <div class="product-search" v-for="product in filterProduct" :key="product.id">
         <img :src="product.imageUrl" class="img-product-search" alt="" />
         <p class="bold-text">{{product.productName}}</p>
       </div>
    </v-card>

</template>
<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n'
import { type Interface } from '@/interface/interface.ts'
import { brandService } from '@/services/BrandService.ts'
import { computed, onMounted, ref } from 'vue'
import { productService } from '@/services/ProductService.ts'
import {type Product} from '@/interface/interface.ts'

const { t } = useI18n()
const router = useRouter()
const listBrand = ref<Interface[]>([])
const products = ref<Product[]>([])
const selectSearch = ref<{ keyword: string }>({ keyword: '' })

const getAllBrand = async () => {
  const res = await brandService.getAll()
  listBrand.value = res.data
}

const updateQuery = () => {
  const params: Record<string, string> = {}
  if (selectSearch.value.keyword) {
    params.keyword = selectSearch.value.keyword
  }
  router.push({ path: '/list-product', query: params })
}

const getAll = async () => {
  const res = await productService.findProductBySearch()
  products.value = res.data
}

const filterProduct = computed(() => {
  return products.value.filter((product) =>
    product.productName.toLowerCase().includes(selectSearch.value.keyword.toLowerCase()),
  )
})
onMounted(() => {
  getAllBrand()
  getAll()
})
</script>
