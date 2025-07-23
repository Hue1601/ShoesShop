
<template>
  <Header/>
  <v-container style="height:550px; ">
     <h2 class="bold-text">THƯƠNG HIỆU NỔI BẬT</h2>
    <v-card class="list-brand">
    <v-card-item v-for="(brand, index) in listBrand" :key="index" style="padding: 0.625rem 0.95rem">
      <router-link :to="`/list-product?brand=${brand.id}`">
        <img :src="brand.logo" class="logo-brand" alt="" />
      </router-link>
    </v-card-item>
    </v-card>
  </v-container>
  <Footer/>
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'

import { brandService } from '@/services/BrandService.ts'
import { onMounted,ref } from 'vue'

import {type Interface } from '@/interface/interface.ts';

const listBrand = ref<Interface[]>([])
const getAll =async () =>{
   const res = await brandService.getAll();
   listBrand.value = res.data
}
onMounted(() =>{
  getAll();
})
</script>
