<template>
  <v-sheet style="width:20%">

<!--    Phần trăm giảm-->
    <p class="custom-select" @click="onclickDiscount" >Phần trăm giảm
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListDiscount">
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
    </v-list>

<!--    Giới tính-->
    <p class="custom-select" @click="onclickGender" >Giới tính
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListGender">
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
    </v-list>

<!--    Thương hiệu-->
    <p class="custom-select" @click="onclickBrand" >Thương hiệu
      <v-icon >mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListBrand">
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
    </v-list>

<!--    Bộ sưu tập-->
    <p class="custom-select" @click="onclickCollection" >Bộ sưu tập
      <v-icon >mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListCollection">
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
    </v-list>

<!--    Khoảng giá-->
    <p class="custom-select" @click="onclickPrice" >Khoảng giá
      <v-icon >mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListPrice">
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
    </v-list>

<!--    Màu sắc-->
    <p class="custom-select" @click="onclickColor" >Màu sắc
      <v-icon>mdi-chevron-down</v-icon>
    </p>
    <v-list v-if="showListColor">
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
      <v-checkbox label="Checkbox" hide-details></v-checkbox>
    </v-list>
  </v-sheet>

</template>
<script setup lang="ts">
import { ref } from "vue";

const showListDiscount = ref(false);
const showListGender = ref(false);
const showListBrand = ref(false);
const showListCollection = ref(false);
const showListPrice = ref(false);
const showListColor = ref(false);

const onclickDiscount = () => {
  showListDiscount.value = !showListDiscount.value;
};

const onclickGender = () =>{
 showListGender.value = !showListGender.value;
}

const onclickBrand = () =>{
 showListBrand.value = !showListBrand.value;
}

const onclickCollection = () =>{
  showListCollection.value = !showListCollection.value;
}

const onclickPrice = () =>{
showListPrice.value = !showListPrice.value;
}

const onclickColor = () =>{

}
</script>
