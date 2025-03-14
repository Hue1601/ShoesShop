
<template>
  <Header/>
  <v-container>
     <h2 class="bold-text">THƯƠNG HIỆU NỔI BẬT</h2>

      <v-card class="list-brand">
        <v-card-item  v-for="(brand,index) in listBrand " :key="index">
<!--         <img src="../../components/icons/listicon/ALLBRANDS_DAS.jpg" class="logo-brand"  alt="" />-->
          <img :src="brand.logo" class="logo-brand"  alt="" />
        </v-card-item>
<!--        <v-card-item>-->
<!--          <img src="../../components/icons/listicon/ALLBRANDS_COLUM.jpg" class="logo-brand"  alt=""/>-->
<!--        </v-card-item>-->
<!--        <v-card-item>-->
<!--          <img src="../../components/icons/listicon/ALLBRANDS_CROCS.webp" class="logo-brand" alt="" />-->
<!--        </v-card-item>-->
<!--        <v-card-item>-->
<!--          <img src="../../components/icons/listicon/ALLBRANDS_FILA.webp" class="logo-brand" alt="" />-->
<!--        </v-card-item>-->
<!--        <v-card-item>-->
<!--          <img src="../../components/icons/listicon/ALLBRANDS_NIKE.webp" class="logo-brand" alt=""/>-->
<!--        </v-card-item>-->
<!--        <v-card-item>-->
<!--          <img src="../../components/icons/listicon/ALLBRANDS_SKE.webp" class="logo-brand" alt="" />-->
<!--        </v-card-item>-->
<!--        <v-card-item>-->
<!--          <img src="../../components/icons/listicon/ALLBRANDS_ON.webp" class="logo-brand"  alt=""/>-->
<!--        </v-card-item>-->
<!--        <v-card-item>-->
<!--          <img src="../../components/icons/listicon/ALLBRANDS_PUMA.webp" class="logo-brand" alt="" />-->
<!--        </v-card-item>-->
<!--        <v-card-item>-->
<!--          <img src="../../components/icons/listicon/ALLBRANDS_CONVERSE.webp" class="logo-brand"  alt=""/>-->
<!--        </v-card-item>-->
<!--        <v-card-item>-->
<!--          <img src="../../components/icons/listicon/ALLBRANDS_NB.webp" class="logo-brand"  alt=""/>-->
<!--        </v-card-item>-->
      </v-card>



  </v-container>
  <Footer/>
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'

import { brandService } from '@/services/SitebarService.ts'
import { onMounted,ref } from 'vue'

interface Brand {
 logo: string
}
const listBrand = ref<Brand[]>([])
const getAll =async () =>{
   const res = await brandService.getAll();
   listBrand.value = res.data
   console.log(res)
}
onMounted(() =>{
  getAll();
})
</script>/
