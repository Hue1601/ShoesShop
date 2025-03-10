<template>
  <Header />
  <v-container style="margin-bottom: 15px;">
    <v-row >
      <v-col cols="7">
        <v-sheet>
          <h2 class="bold-text">Thông tin liên lạc</h2>
          <v-text-field label="Email" variant="outlined" hide-details density="compact" />
          <v-checkbox label="Gửi email cho tôi tin tức và ưu đãi qua mail" hide-details />
        </v-sheet>

        <v-sheet>
          <h2 class="bold-text">Giao hàng</h2>

          <v-select
            label="Tỉnh/thành phố"
            variant="outlined"
            hide-details
            density="compact"
          ></v-select>
          <div class="input-location">
            <v-select
              label="Quận/huyện"
              variant="outlined"
              hide-details
              density="compact"
              style="margin-right: 10px"
            ></v-select>
            <v-select
              label="Phường/xã"
              variant="outlined"
              hide-details
              density="compact"
            ></v-select>
          </div>

          <div class="input-location">
            <v-text-field
              label="Tên"
              style="margin-right: 10px"
              variant="outlined"
              hide-details
              density="compact"
            ></v-text-field>
            <v-text-field
              label="Họ"
              variant="outlined"
              hide-details
              density="compact"
            ></v-text-field>
          </div>

          <v-text-field
            label="Địa chỉ"
            variant="outlined"
            hide-details
            density="compact"
            align-top="5"
          ></v-text-field>
          <v-text-field
            label="Số điện thoại"
            variant="outlined"
            hide-details
            density="compact"
            style="margin-top: 10px"
          ></v-text-field>
          <v-checkbox label="Lưu thông tin này" hide-details />
        </v-sheet>

        <v-sheet>
          <h2 class="bold-text">Thanh toán</h2>
          <v-radio label="Thanh toán khi nhận hàng (COD)" />
          <v-radio label="Thanh toán bằng chuyển khoản" />
          <v-checkbox label="Yêu cầu xuất hóa đơn" hide-details />
          <v-checkbox
            label="Tôi đồng ý với Điều khoản và Chính sách quy định bởi shop"
            hide-details
          />
        </v-sheet>
        <v-btn class="btn">THANH TOÁN NGAY</v-btn>
      </v-col>
      <v-col cols="5" class="show-info-order">

        <v-sheet class="summary-order-product">
           <div class="product-info-payment">
             <img
               src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
               class="img-product-payment"
               alt=""
             />
             <div  class="order-summary">
               <p class="bold-text">Giày thể thao nam</p>
               <p class="brand-name">Đỏ/27</p>
             </div>
             <div class="product-quantity">
               <p>2.200.00 đ</p>
               <p class="brand-name">x1</p>
             </div>
           </div>
          <div class="product-info-payment">
            <img
              src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
              class="img-product-payment"
              alt=""
            />
            <div  class="order-summary">
              <p class="bold-text">Giày thể thao nam</p>
              <p class="brand-name">Đỏ/27</p>
            </div>
            <div class="product-quantity">
              <p>2.200.00 đ</p>
              <p class="brand-name">x1</p>
            </div>
          </div>
          <div class="product-info-payment">
            <img
              src="../../components/icons/listicon/HQ4199-1.webp"
              class="img-product-payment"
              alt=""
            />
            <div  class="order-summary">
              <p class="bold-text">Giày thể thao nam</p>
              <p class="brand-name">Đỏ/27</p>
            </div>
            <div class="product-quantity">
              <p>2.200.00 đ</p>
              <p class="brand-name">x1</p>
            </div>
          </div>

          <div class="product-info-payment">
            <img
              src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
              class="img-product-payment"
              alt=""
            />
            <div  class="order-summary">
              <p class="bold-text">Giày thể thao nam</p>
              <p class="brand-name">Đỏ/27</p>
            </div>
            <div class="product-quantity">
              <p>2.200.00 đ</p>
              <p class="brand-name">x1</p>
            </div>
          </div>
          <div class="product-info-payment">
            <img
              src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
              class="img-product-payment"
              alt=""
            />
            <div  class="order-summary">
              <p class="bold-text">Giày thể thao nam</p>
              <p class="brand-name">Đỏ/27</p>
            </div>
            <div class="product-quantity">
              <p>2.200.00 đ</p>
              <p class="brand-name">x1</p>
            </div>
          </div>
          <div class="product-info-payment">
            <img
              src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
              class="img-product-payment"
              alt=""
            />
            <div  class="order-summary">
              <p class="bold-text">Giày thể thao nam</p>
              <p class="brand-name">Đỏ/27</p>
            </div>
            <div class="product-quantity">
              <p>2.200.00 đ</p>
              <p class="brand-name">x1</p>
            </div>
          </div>
          <div class="product-info-payment">
            <img
              src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
              class="img-product-payment"
              alt=""
            />
            <div  class="order-summary">
              <p class="bold-text">Giày thể thao nam</p>
              <p class="brand-name">Đỏ/27</p>
            </div>
            <div class="product-quantity">
              <p>2.200.00 đ</p>
              <p class="brand-name">x1</p>
            </div>
          </div>
          <div class="product-info-payment">
            <img
              src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
              class="img-product-payment"
              alt=""
            />
            <div  class="order-summary">
              <p class="bold-text">Giày thể thao nam</p>
              <p class="brand-name">Đỏ/27</p>
            </div>
            <div class="product-quantity">
              <p>2.200.00 đ</p>
              <p class="brand-name">x1</p>
            </div>
          </div>
          <div class="product-info-payment">
            <img
              src="../../components/icons/listicon/FZ5486-003-1_360x.webp"
              class="img-product-payment"
              alt=""
            />
            <div  class="order-summary">
              <p class="bold-text">Giày thể thao nam</p>
              <p class="brand-name">Đỏ/27</p>
            </div>
            <div class="product-quantity">
              <p>2.200.00 đ</p>
              <p class="brand-name">x1</p>
            </div>
          </div>
        </v-sheet>


        <div style="display: flex; align-items: center">
          <v-text-field
            label="Số điện thoại"
            variant="outlined"
            hide-details
            density="compact"
          ></v-text-field>
          <v-btn variant="outlined" style="margin-left: 10px">Áp dụng</v-btn>
        </div>

        <div class="wrap-div">
          <p>Tổng tiền hàng:</p>
          <p>2000 đ</p>
        </div>
        <div class="wrap-div">
          <p>Phí vận chuyển:</p>
          <p>2000 đ</p>
        </div>
        <div class="wrap-div">
          <h3 class="bold-text">Tổng:</h3>
          <h3 class="bold-text">2.000.888 đ</h3>
        </div>

      </v-col>
    </v-row>
  </v-container>
  <Footer />
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'
</script>
