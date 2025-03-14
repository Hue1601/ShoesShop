<template>
  <v-sheet style="width: 20%">
    <!--    Phần trăm giảm-->
    <p class="custom-select" @click="onclickDiscount">
      Phần trăm giảm
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListDiscount">
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
    </v-list>

    <!--    Giới tính-->
    <p class="custom-select" @click="onclickGender">
      Giới tính
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListGender">
      <v-radio label="Nam" hide-details />
      <v-radio label="Nữ" hide-details />
      <v-radio label="Unisex" hide-details />
    </v-list>

    <!--    Thương hiệu-->
    <p class="custom-select" @click="onclickBrand">
      Thương hiệu
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListBrand">
      <v-checkbox
        :label="brand.brandName || 'Không có tên'"
        hide-details
        v-for="(brand, i) in listBrand"
        :key="i"
      ></v-checkbox>
    </v-list>

    <!--    Bộ sưu tập-->
    <p class="custom-select" @click="onclickCollection">
      Bộ sưu tập
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListCollection">
      <v-checkbox
        :label="collection.collectionName"
        hide-details
        v-for="(collection, index) in collection"
        :key="index"
      ></v-checkbox>
    </v-list>

    <!--    Khoảng giá-->
    <p class="custom-select" @click="onclickPrice">
      Khoảng giá
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListPrice">
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
    </v-list>

    <!--    Màu sắc-->
    <p class="custom-select" @click="onclickColor">
      Màu sắc
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListColor">
      <v-checkbox
        :label="color.colorName"
        hide-details
        v-for="(color, index) in color"
        :key="index"
      ></v-checkbox>
    </v-list>
  </v-sheet>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { sitebarService } from '@/services/SitebarService.ts'

const showListDiscount = ref(false)
const showListGender = ref(false)
const showListBrand = ref(false)
const showListCollection = ref(false)
const showListPrice = ref(false)
const showListColor = ref(false)

interface Brand {
  brandName: string
}

interface Collection {
  collectionName: string
}

interface Color {
  colorName: string
}

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
onMounted(() => {
  getAll()
  getCollection()
  getColor()
})
</script>
