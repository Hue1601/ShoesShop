<template>
  <v-sheet style="width: 20%">
    <!--    Phần trăm giảm-->
    <p class="custom-select" @click="onclickDiscount">
      Phần trăm giảm
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListDiscount">
      <v-checkbox
        v-for="option in discountOptions"
        :key="option.value"
        v-model="selectedFilters.discount"
        :label="option.label"
        :value="option.value"
        hide-details
        @change="updateQueryParams"
      />
    </v-list>

    <!--    Giới tính-->
    <p class="custom-select" @click="onclickGender">
      Giới tính
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListGender">
      <v-radio-group v-model="selectedFilters.gender" @change="updateQueryParams">
        <v-radio label="Nam" value="male" />
        <v-radio label="Nữ" value="female" />
        <v-radio label="Unisex" value="unisex" />
      </v-radio-group>
    </v-list>

    <!--    Thương hiệu-->
    <p class="custom-select" @click="onclickBrand">
      Thương hiệu
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListBrand">
      <v-checkbox
        :label="brand.brandName"
        :value="brand.id"
        hide-details
        v-for="brand in listBrand"
        :key="brand.id"
        v-model="selectedFilters.brand"
        @change="updateQueryParams"
      ></v-checkbox>
    </v-list>

    <!--    Bộ sưu tập-->
    <p class="custom-select" @click="onclickCollection">
      Bộ sưu tập
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListCollection">
      <v-checkbox
        :value="collection.id"
        :label="collection.collectionName"
        hide-details
        v-for="(collection, index) in collection"
        :key="index"
        v-model="selectedFilters.collection"
        @change="updateQueryParams"
      ></v-checkbox>
    </v-list>

    <!--    Khoảng giá-->
    <p class="custom-select" @click="onclickPrice">
      Khoảng giá
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListPrice">
      <v-checkbox
        v-for="price in priceOptions"
        :key="price.value"
        :label="price.label"
        :value="price.value"
        v-model="selectedFilters.price"
        @change="updateQueryParams"
        hide-details
      />
    </v-list>

    <!--    Màu sắc-->
    <p class="custom-select" @click="onclickColor">
      Màu sắc
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListColor">
      <v-checkbox
        :label="color.colorName"
        :value="color.id"
        hide-details
        v-for="(color, index) in color"
        :key="index"
        v-model="selectedFilters.color"
        @change="updateQueryParams"
      ></v-checkbox>
    </v-list>
  </v-sheet>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { sitebarService } from '@/services/SitebarService.ts'
import { useRouter } from 'vue-router'

const showListDiscount = ref(false)
const showListGender = ref(false)
const showListBrand = ref(false)
const showListCollection = ref(false)
const showListPrice = ref(false)
const showListColor = ref(false)

const router = useRouter()

interface Brand {
  id: number
  brandName: string
}

interface Collection {
  id: number
  collectionName: string
}

interface Color {
  id: number
  colorName: string
}

const discountOptions = ref([
  { label: 'Dưới 10%', value: '0-10' },
  { label: '10-20%', value: '10-20' },
  { label: '20-30%', value: '20-30' },
  { label: '30-40%', value: '30-40' },
  { label: '40-50%', value: '40-50' },
  { label: 'Trên 50%', value: '50-100' },
])

const priceOptions = ref([
  { label: 'Dưới 500.000 đ', value: '0-500' },
  { label: '500.000 đ - 1.000.000 đ', value: '500-1000' },
  { label: '1.000.000 đ - 2.000.000 đ', value: '1000-2000' },
  { label: '2.000.000 đ - 3.000.000 đ', value: '2000-3000' },
  { label: '3.000.000 đ - 4.000.000 đ', value: '3000-4000' },
  { label: 'Trên 5.000.000 đ', value: '5000' },
])

const listBrand = ref<Brand[]>([])
const collection = ref<Collection[]>([])
const color = ref<Color[]>([])

const onclickDiscount = () => {
  showListDiscount.value = !showListDiscount.value
}

const onclickGender = () => {
  showListGender.value = !showListGender.value
}

const onclickBrand = () => {
  showListBrand.value = !showListBrand.value
}

const onclickCollection = () => {
  showListCollection.value = !showListCollection.value
}

const onclickPrice = () => {
  showListPrice.value = !showListPrice.value
}

const onclickColor = () => {
  showListColor.value = !showListColor.value
}

const getAll = async () => {
  const res = await sitebarService.getAll()
  listBrand.value = res.data
}
const getCollection = async () => {
  const res = await sitebarService.getCollection()
  collection.value = res.data
}
const getColor = async () => {
  const res = await sitebarService.getColor()
  color.value = res.data
}

// push select data to param
const selectedFilters = ref({
  discount: [],
  gender: '',
  brand: [],
  collection: [],
  color: [],
  price: [],
})

const updateQueryParams = () => {
  const params: Record<string, string> = {}

  if (selectedFilters.value.gender) {
    params.gender = selectedFilters.value.gender
  }
  if (Array.isArray(selectedFilters.value.brand) && selectedFilters.value.brand.length) {
    params.brand = selectedFilters.value.brand.join(',')
  }
  if (Array.isArray(selectedFilters.value.collection) && selectedFilters.value.collection.length) {
    params.collection = selectedFilters.value.collection.join(',')
  }
  if (Array.isArray(selectedFilters.value.discount) && selectedFilters.value.discount.length) {
    params.discount = selectedFilters.value.discount.join(',')
  }
  if (Array.isArray(selectedFilters.value.color) && selectedFilters.value.color.length) {
    params.color = selectedFilters.value.color.join(',')
  }
  if (Array.isArray(selectedFilters.value.price) && selectedFilters.value.price.length) {
    params.price = selectedFilters.value.price.join(',')
  }

  router.push({ path: '/list-product', query: params })
}

onMounted(() => {
  getAll()
  getCollection()
  getColor()
})
</script>
