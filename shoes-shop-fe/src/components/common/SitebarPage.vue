<template>
  <v-sheet style="width: 20%">
    <!--    Phần trăm giảm-->
    <p class="custom-select" @click="onclickDiscount">
      Phần trăm giảm
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListDiscount">
      <v-checkbox label="Dưới 10%" hide-details value="0-10"/>
      <v-checkbox label="10-20%" hide-details value="10-20"/>
      <v-checkbox label="20-30%" hide-details value="20-30"/>
      <v-checkbox label="30-40%" hide-details value="30-40"/>
      <v-checkbox label="40-50%" hide-details value="40-50"/>
      <v-checkbox label="Trên 50%" hide-details value="50-100"/>
    </v-list>

    <!--    Giới tính-->
    <p class="custom-select" @click="onclickGender">
      Giới tính
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListGender">
      <v-radio label="Nam" hide-details value="male" />
      <v-radio label="Nữ" hide-details value="female"/>
      <v-radio label="Unisex" hide-details value ="unisex"/>
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
        :value = "collection.id"
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
      <v-checkbox label="Dưới 500.000 đ" hide-details value="0-500"/>
      <v-checkbox label="500.000 đ - 1.000.000 đ" hide-details value="500-1000"/>
      <v-checkbox label="1.000.000 đ - 2.000.000 đ" hide-details value="1000-2000"/>
      <v-checkbox label="2.000.000 đ - 3.000.000 đ" hide-details value="2000-3000"/>
      <v-checkbox label="3.000.000 đ - 4.000.000 đ" hide-details value="3000-4000"/>
      <v-checkbox label="Trên 5.000.000 đ" hide-details value="5000"/>
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
  id:number
  brandName: string
}

interface Collection {
  id:number
  collectionName: string
}

interface Color {
  id:number
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
