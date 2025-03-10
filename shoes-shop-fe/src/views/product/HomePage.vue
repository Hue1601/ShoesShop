<template>
  <Header />
  <v-container max-width="90%">

  <h2 class="bold-text">{{ t('home-page.new-product') }}</h2>
    <v-sheet max-width="100%">
      <v-slide-group v-model="model" class="pa-4" selected-class="bg-success" show-arrows >
        <v-slide-group-item v-for="n in 10" :key="n" >
          <v-card class="ma-4"  height="380" width="230" style="box-shadow: none">
                <img src="../../components/icons/listicon/FZ5486-003-1_360x.webp" class="img-product" alt=""/>
              <v-btn :key="i" :color="color" class="mx-1 " icon width="20" height="20" ></v-btn>
                <p class="brand-product">Giày thể thao nam</p>
                <p class=brand-name>Nike</p>
                <p >1.200.000 đ</p>
          </v-card>
        </v-slide-group-item>
      </v-slide-group>
    </v-sheet>
  <h2 class="bold-text">{{t('home-page.best-selling-product')}}</h2>

    <v-sheet max-width="100%">
      <v-slide-group  class="pa-4" selected-class="bg-success" show-arrows >
        <v-slide-group-item v-for="n in 10" :key="n" >
          <v-card class="ma-4"  height="380" width="230" style="box-shadow: none">
              <img src="../../components/icons/listicon/FZ5486-003-1_360x.webp" class="img-product" alt=""/>
              <v-btn  class="mx-1 " icon width="20" height="20" ></v-btn>
              <p class="brand-product">Giày thể thao nam</p>
              <p class=brand-name>Nike</p>
              <p >1.200.000 đ</p>

          </v-card>
        </v-slide-group-item>
      </v-slide-group>
    </v-sheet>
  </v-container>
  <Footer />
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
</script>

