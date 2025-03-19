<template>
  <Header />
  <v-container style="min-height: 400px; max-height: fit-content">
    <h2 class="bold-text">{{t('cart.cart')}}</h2>

    <div class="cart-container">
      <div class="product-in-cart">
        <div class="product-cart">
          <img
            src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
            class="img-product-cart"
            alt=""
          />
          <div class="product-info">
            <p>Nike</p>
            <p class="name-product-cart">Giày thể thao nike</p>
          </div>

          <p class="product-price">1.197.000 đ</p>
          <v-text-field
            type="number"
            variant="outlined"
            density="compact"
            class="quantity"
            hide-details
          ></v-text-field>
          <div>
            <p class="product-price">1,197.000 đ</p>
            <p class="text-trash">{{t('cart.delete')}}</p>
          </div>
        </div>

        <div class="product-cart">
          <img
            src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
            class="img-product-cart"
            alt=""
          />
          <div class="product-info">
            <p>Nike</p>
            <a class="name-product-cart">Giày thể thao nike</a>
          </div>

          <p class="product-price">1.197.000 đ</p>
          <v-text-field
            type="number"
            variant="outlined"
            density="compact"
            class="quantity"
            hide-details
          ></v-text-field>
          <div>
            <p class="product-price">1,197.000 đ</p>
            <p class="text-trash">Xóa</p>
          </div>
        </div>
      </div>

      <div class="total">
        <v-row class="row">
          <v-col>
            <p class="right-text">(1) sản phẩm</p>
          </v-col>
          <v-col>
            <p class="total-price">1.195.00 đ</p>
          </v-col>
        </v-row>

        <v-divider />

        <v-row class="row">
          <v-col>
            <p class="right-text">Giảm giá</p>
          </v-col>
          <v-col>
            <p class="left-text">Áp dụng ở trang thanh toán</p>
          </v-col>
        </v-row>

        <v-divider />

        <v-row class="row">
          <v-col>
            <p class="right-text">{{t('cart.shipping-fee')}}</p>
          </v-col>
          <v-col>
            <p class="left-text">{{t('cart.shipping-note')}}</p>
          </v-col>
        </v-row>
        <v-divider />
        <v-row class="row">
          <v-col>
            <p class="right-text">{{t('cart.into-money')}}</p>
          </v-col>
          <v-col>
            <p class="total-price">1.195.000 đ</p>
          </v-col>
        </v-row>

        <v-btn class="btn" to="/payment"></v-btn>
        <p class="notice">{{t('cart.note')}}</p>
      </div>
    </div>
  </v-container>
  <Footer />
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'
import { useI18n } from 'vue-i18n';

const { t } = useI18n();
</script>
