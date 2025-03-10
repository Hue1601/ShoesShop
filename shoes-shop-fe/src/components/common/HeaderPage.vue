<template>
  <v-toolbar class="header" density="comfortable">
    <span class="shop-name" >{{t('header.shop-name')}}</span>
    <router-link to="/home" class="header-item text"> {{t('header.home')}}</router-link>
    <router-link to="/list-product" class="header-item text"> {{t('header.product')}}</router-link>

    <v-menu open-on-hover>
      <template v-slot:activator="{ props }">
        <div class="brand-wrapper" v-bind="props" style="display: flex; align-items: center;">
          <router-link to="/list-brand" class="header-item text">
            {{t('header.brand')}}
          </router-link>
          <v-icon class="dropdown-icon">mdi-chevron-down</v-icon>
        </div>
      </template>
      <v-card class="brand-dropdown">
        <v-container>
          <v-row>
              <ul>
                <p v-for="brand in brandsA_G" :key="brand">{{ brand }}</p>
              </ul>
          </v-row>
        </v-container>
      </v-card>
    </v-menu>


    <router-link to="/register" class="header-item text">{{t('header.introduce')}}</router-link>

    <v-text-field
      :loading="loading"
      append-inner-icon="mdi-magnify"
      density="compact"
      label="Search "
      variant="solo"
      hide-details
      single-line
      style="margin-right: 5px"
      @click:append-inner="onClick"
    ></v-text-field>

    <img
      src="../../components/icons/listicon/Cart.png"
      class="icon-header"
      alt=""
      @click="() => router.push('/cart')"
    />
    <img src="../../components/icons/listicon/User.png" class="icon-header" alt="" />
  </v-toolbar>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useI18n } from 'vue-i18n';
const { t } = useI18n();

const router = useRouter();
const brandsA_G = ["2XU", "adidas", "Airwalk", "Bahe", "Balega", "Body Sculpture", "Brooks", "COLUMBIA", "Converse", "Crocs", "Ellesse", "Fila", "Fitflop", "Forcefield"];

</script>
