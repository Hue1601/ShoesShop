<template>
  <Header />
  <v-container max-width="94%">
    <v-row>
      <v-col cols="7">
        <img
          src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
          class="img-product-detail"
          alt=""
          @click="viewLargeImg"
        />
        <v-sheet max-width="100%">
          <v-slide-group
            class="pa-4"
            selected-class="bg-success"
            show-arrows
            style="padding: 0 !important"
          >
            >
            <v-slide-group-item v-for="n in 10" :key="n">
              <v-card
                class="ma-4"
                height="100"
                width="100"
                style="box-shadow: none; margin: 6px !important"
              >
                <img
                  src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                  class="list-img-detail"
                  alt=""
                />
              </v-card>
            </v-slide-group-item>
          </v-slide-group>
        </v-sheet>
      </v-col>

      <v-col cols="5">
        <h2 class="bold-text">
          Giày Đá Bóng Dành Cho Mọi Loại Sân Nam Nike Zoom Vapor 16 Academy Fg/Mg - Đ
        </h2>
        <p class="brand-name">Nike</p>
        <div class="product-price-detail">
          <p class="discount-price">3.200.500 đ</p>
          <p class="origin-price">3.200.500 đ</p>
          <p class="product-discount-badge">23 %</p>
        </div>

        <div>
          <div class="display-quantity">
            <p class="bold-text">Số lượng</p>
            <v-text-field
              type="number"
              variant="outlined"
              density="compact"
              class="quantity"
            ></v-text-field>
          </div>
          <p class="bold-text">Màu sắc</p>

          <v-btn class="btn-color">
            <img
              src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
              class="list-color-product"
              alt=""
            />
          </v-btn>
          <v-btn class="btn-color">
            <img
              src="../../components/icons/listicon/HQ4199-1.webp"
              class="list-color-product"
              alt=""
            />
          </v-btn>
        </div>
        <div style="margin-top: 30px">
          <p class="bold-text">Kích thước</p>
          <v-btn class="btn-size">37</v-btn>
          <v-btn class="btn-size">38</v-btn>
          <v-btn class="btn-size" disabled>39</v-btn>
        </div>

        <div class="size">
          <img src="../../components/icons/listicon/ruler.png" class="icon-product-detail" alt="" />
          <span class="size-guide">Hướng dẫn chọn size</span>
        </div>

        <v-row class="btn-action-detail">
          <v-col cols="6">
            <v-btn class="btn">Mua ngay</v-btn>
          </v-col>
          <v-col cols="6">
            <v-btn style="border: 1px solid gray">Thêm vào giỏ hàng</v-btn>
          </v-col>
        </v-row>

        <v-sheet class="product-promotion-wrapper">
          <div class="size" style="margin-top: 0">
            <img
              src="../../components/icons/listicon/SHIPPING.png"
              class="icon-product-detail"
              alt=""
            />
            <span class="size-guide promotion">Miễn phí giao hàng đơn từ 699k</span>
          </div>

          <div class="size">
            <img
              src="../../components/icons/listicon/payment.png"
              class="icon-product-detail"
              alt=""
            />
            <span class="size-guide promotion">Thanh toán trực tuyến nhanh chóng và an toàn.</span>
          </div>

          <div class="size">
            <img
              src="../../components/icons/listicon/Retur.png"
              class="icon-product-detail"
              alt=""
            />
            <span class="size-guide promotion">Đổi trả miễn phí đến 30 ngày</span>
          </div>

          <div class="size">
            <img
              src="../../components/icons/listicon/Authentic.png"
              class="icon-product-detail"
              alt=""
            />
            <span class="size-guide promotion">Sản phẩm chính hãng 100%</span>
          </div>
        </v-sheet>
      </v-col>
    </v-row>
    <v-row>
      <p class="size-guide product-description">Mô tả sản phẩm</p>
      <v-divider />
      <div class="discription">
        <h2 class="bold-text">
          Giày Đá Bóng Dành Cho Mọi Loại Sân Nam Nike Zoom Vapor 16 Academy Fg/Mg - Đ
        </h2>
        <p >
          Lấy cảm hứng từ những đôi giày chạy hiệu suất cao, Giày Sneaker Unisex Adidas Mercedes
          Lightblaze được thiết kế dành riêng cho người hâm mộ đội đua Mercedes-AMG Formula One.
          Công nghệ Lightstrike mang đến lớp đệm siêu nhẹ, giúp bạn di chuyển nhanh nhẹn và thoải
          mái cả ngày. Upper ripstop kết hợp với các lớp phủ da lộn không chỉ gia tăng độ bền mà còn
          tạo điểm nhấn phong cách. Màu sắc đặc trưng cùng logo Mercedes-AMG F1 thể hiện tinh thần
          tốc độ, đẳng cấp và mạnh mẽ.
        </p>
        <h2 class="bold-text" style="margin-top: 20px">Thông số</h2>

        <ul style="padding: 20px">
          <li>Kiểu dáng vừa vặn (Regular Fit)</li>
          <li>Thiết kế thể thao năng động, lấy cảm hứng từ giày chạy hiệu suất cao</li>
          <li>Thiết kế thể thao năng động, lấy cảm hứng từ giày chạy hiệu suất cao</li>
        </ul>
      </div>
    </v-row>
    <h2 class="bold-text related-product" >Sản phẩm liên quan</h2>

    <v-sheet max-width="100%">
      <v-slide-group class="pa-4" selected-class="bg-success" show-arrows>
        <v-slide-group-item v-for="n in 10" :key="n">
          <v-card class="ma-4 box-shadow" height="380" width="230" >
            <div class=" ">
              <img
                src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
                class="img-product"
                alt=""
              />
              <v-btn class="mx-1" icon width="20" height="20"></v-btn>
              <p class="brand-product">Giày thể thao nam</p>
              <p class="brand-name">Nike</p>
              <p>1.200.000 đ</p>
            </div>
          </v-card>
        </v-slide-group-item>
      </v-slide-group>
    </v-sheet>

    <v-sheet v-if="showImgProduct" class="show-img-modal">
      <div>
        <img
          src="../../components/icons/listicon/close.png"
          class="icon-close"
          alt=""
          i-carbon-close=""
          @click="closeImageModal"
        />
        <img
          src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
          class="large-img"
          alt=""
        />
      </div>
    </v-sheet>
  </v-container>
  <Footer />
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'

import { ref } from 'vue'

const showImgProduct = ref(false)

const viewLargeImg = () => {
  showImgProduct.value = true
}

const closeImageModal = () => {
  showImgProduct.value = false
}
</script>
