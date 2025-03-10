<template>
  <Header />
  <v-container max-width="94%">
    <div style="display: flex">
      <Sitebar />
      <div class="list-product">
        <v-text-field
          :loading="loading"
          append-inner-icon="mdi-magnify"
          density="compact"
          label="Search templates"
          variant="outlined"
          hide-details
          @click:append-inner="onClick"
        ></v-text-field>

        <p class="text-arrange" @click="onclickArrange">
          Sắp xếp
          <v-icon>mdi-chevron-down</v-icon>
        </p>

        <v-list class="list-arrange" v-if="showListArrange">
          <v-list-item>hhh</v-list-item>
          <v-list-item>hhh</v-list-item>
        </v-list>
        <v-list style="display: flex; flex-wrap: wrap">
          <v-card
            class="ma-4"
            height="380"
            width="230"
            style="box-shadow: none"
            to="/product-detail"
          >
            <div class=" ">
              <img
                src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                class="img-product"
                alt=""
              />
              <p class="brand-product">Giày thể thao nam</p>
              <p class="brand-name">Nike</p>
              <p>1.200.000 đ</p>
            </div>
          </v-card>

          <v-card class="ma-4" height="380" width="230" style="box-shadow: none">
            <div class=" ">
              <img
                src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                class="img-product"
                alt=""
              />
              <p class="brand-product">Giày thể thao nam</p>
              <p class="brand-name">Nike</p>
              <p>1.200.000 đ</p>
            </div>
          </v-card>

          <v-card class="ma-4" height="380" width="230" style="box-shadow: none">
            <div class=" ">
              <img
                src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                class="img-product"
                alt=""
              />
              <p class="brand-product">Giày thể thao nam</p>
              <p class="brand-name">Nike</p>
              <p>1.200.000 đ</p>
            </div>
          </v-card>

          <v-card class="ma-4" height="380" width="230" style="box-shadow: none">
            <div class=" ">
              <img
                src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                class="img-product"
                alt=""
              />
              <p class="brand-product">Giày thể thao nam</p>
              <p class="brand-name">Nike</p>
              <p>1.200.000 đ</p>
            </div>
          </v-card>
          <v-card class="ma-4" height="380" width="230" style="box-shadow: none">
            <div class=" ">
              <img
                src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                class="img-product"
                alt=""
              />
              <p class="brand-product">Giày thể thao nam</p>
              <p class="brand-name">Nike</p>
              <p>1.200.000 đ</p>
            </div>
          </v-card>
          <v-card class="ma-4" height="380" width="230" style="box-shadow: none">
            <div class=" ">
              <img
                src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                class="img-product"
                alt=""
              />
              <p class="brand-product">Giày thể thao nam</p>
              <p class="brand-name">Nike</p>
              <p>1.200.000 đ</p>
            </div>
          </v-card>
          <v-card class="ma-4" height="380" width="230" style="box-shadow: none">
            <div class=" ">
              <img
                src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                class="img-product"
                alt=""
              />
              <p class="brand-product">Giày thể thao nam</p>
              <p class="brand-name">Nike</p>
              <p>1.200.000 đ</p>
            </div>
          </v-card>
          <v-card class="ma-4" height="380" width="230" style="box-shadow: none">
            <div class=" ">
              <img
                src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                class="img-product"
                alt=""
              />
              <p class="brand-product">Giày thể thao nam</p>
              <p class="brand-name">Nike</p>
              <p>1.200.000 đ</p>
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

import router from '@/router'
import { ref } from 'vue'

const showListArrange = ref(false)

const onclickArrange = () => {
  showListArrange.value = !showListArrange.value
}

const goToDetail = () => {
  router.push('/product-detail')
  console.log('hhhhh')
}
</script>
