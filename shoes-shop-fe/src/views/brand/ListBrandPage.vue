
<template>
  <Header/>
  <v-container>
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

import { sitebarService } from '@/services/SitebarService.ts'
import { onMounted,ref } from 'vue'

import {type Brand } from '@/interface/Brand.ts';

const listBrand = ref<Brand[]>([])
const getAll =async () =>{
   const res = await sitebarService.getAll();
   listBrand.value = res.data
}
onMounted(() =>{
  getAll();
})
</script>
