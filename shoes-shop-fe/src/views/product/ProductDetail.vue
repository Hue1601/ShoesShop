<template>
  <Header />
  <v-container max-width="94%" v-if="productDetail.length">
    <v-row>
      <v-col cols="6" style="max-width: none">
        <img
          :src="thumbnailImage"
          class="img-product-detail"
          alt=""
          @click="viewLargeImg(thumbnailImage)"
        />
        <v-sheet max-width="100%">
          <v-slide-group
            class="pa-4"
            selected-class="bg-success"
            show-arrows
            style="padding: 0 !important"
          >
            <v-slide-group-item v-for="(img, index) in additionalImages" :key="index">
              <v-card
                class="ma-4"
                height="100"
                width="100"
                style="box-shadow: none; margin: 6px !important"
              >
                <img :src="img" class="list-img-detail" alt="" @click="viewLargeImg(img)" />
              </v-card>
            </v-slide-group-item>
          </v-slide-group>
        </v-sheet>
      </v-col>

      <v-col cols="6">
        <h2 class="bold-text">
          {{ product.productName }}
        </h2>
        <p class="brand-name">{{ product.brandName }}</p>
        <div class="product-price-detail">
          <p class="discount-price" v-if="product.discountPercentage">
            {{ formatPrice(product.discountPrice) }}
          </p>
          <p :class="{ 'origin-price': product.discountPercentage }">
            {{ formatPrice(product.price) }}
          </p>
          <p class="product-discount-badge" v-if="product.discountPercentage">
            {{ product.discountPercentage }} %
          </p>
        </div>

        <div class="display-quantity">
          <p class="bold-text">{{ t('product-detail.quantity') }}</p>
          <v-text-field
            type="number"
            variant="outlined"
            density="compact"
            class="quantity"
            model-value="1"
          ></v-text-field>
        </div>

        <p class="bold-text">{{ t('product-detail.color') }}</p>
        <v-btn
          :class="selectedColor === index ? 'is-click-btn' : 'btn-size'"
          v-for="(color, index) in colors"
          :key="index"
          @click="changeColor(index, color)"
        >
          {{ color }}
        </v-btn>

        <div style="margin-top: 30px">
          <p class="bold-text">{{ t('product-detail.size') }}</p>
          <v-btn
            v-for="(size, index) in filteredSizes"
            :key="index"
            :class="selectedSize === index ? 'is-click-btn' : 'btn-size'"
            @click="changeSize(index)"
            :disabled="size.stock === 0"
          >
            {{ size.size }}
          </v-btn>
        </div>

        <div class="size">
          <img src="../../components/icons/listicon/ruler.png" class="icon-product-detail" alt="" />
          <span class="size-guide">{{ t('product-detail.size-guide') }}</span>
        </div>

        <v-row class="btn-action-detail">
          <v-col cols="6">
            <v-btn class="btn">{{ t('product-detail.btn-buy') }}</v-btn>
          </v-col>
          <v-col cols="6">
            <v-btn style="border: 1px solid gray">{{ t('product-detail.add-to-cart') }}</v-btn>
          </v-col>
        </v-row>

        <v-sheet class="product-promotion-wrapper">
          <div class="size" style="margin-top: 0">
            <img
              src="../../components/icons/listicon/SHIPPING.png"
              class="icon-product-detail"
              alt=""
            />
            <span class="size-guide promotion">{{ t('product-detail.free-shipping') }}</span>
          </div>

          <div class="size">
            <img
              src="../../components/icons/listicon/payment.png"
              class="icon-product-detail"
              alt=""
            />
            <span class="size-guide promotion">{{ t('product-detail.payment-online') }}</span>
          </div>

          <div class="size">
            <img
              src="../../components/icons/listicon/Retur.png"
              class="icon-product-detail"
              alt=""
            />
            <span class="size-guide promotion">{{ t('product-detail.return') }}</span>
          </div>

          <div class="size">
            <img
              src="../../components/icons/listicon/Authentic.png"
              class="icon-product-detail"
              alt=""
            />
            <span class="size-guide promotion">{{ t('product-detail.auth-product') }}</span>
          </div>
        </v-sheet>
      </v-col>
    </v-row>
    <v-row>
      <p class="size-guide product-description">{{ t('product-detail.description') }}</p>
      <v-divider />
      <div class="discription">
        <h2 class="bold-text">
          {{ product.productName }}
        </h2>
        <p>
          {{ product.description }}
        </p>
        <h2 class="bold-text" style="margin-top: 20px">Thông số</h2>

        <ul style="padding: 20px">
          <li>Kiểu dáng vừa vặn (Regular Fit)</li>
          <li>Thiết kế thể thao năng động, lấy cảm hứng từ giày chạy hiệu suất cao</li>
          <li>Thiết kế thể thao năng động, lấy cảm hứng từ giày chạy hiệu suất cao</li>
        </ul>
      </div>
    </v-row>
    <!--    </div>-->

    <h2 class="bold-text related-product">{{ t('product-detail.product-related') }}</h2>

    <v-sheet max-width="100%">
      <v-slide-group class="pa-4" selected-class="bg-success" show-arrows>
        <v-slide-group-item v-for="n in 10" :key="n">
          <v-card class="ma-4 box-shadow" height="380" width="230">
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
        <img :src="imgLightBox" class="large-img" alt="" />
      </div>
    </v-sheet>
  </v-container>
  <Footer />
</template>
<script setup lang="ts">
import Header from '../../components/common/HeaderPage.vue'
import Footer from '../../components/common/FooterPage.vue'
import { computed, onMounted, ref } from 'vue'
import { productService } from '@/services/ProductService.ts'
import { useRoute } from 'vue-router'
import { type ProductDetail,type SizeByColor } from '@/interface/interface.ts'
import { useI18n } from 'vue-i18n'
import router from '@/router'

const { t } = useI18n()
const route = useRoute()

const productDetail = ref<ProductDetail[]>([])
const showImgProduct = ref(false)
const imgLightBox = ref('')
const product = computed(() => productDetail.value[0] || {})

const selectedColor = ref<number | null>(null)
const selectedSize = ref<number | null>(null)
// Lấy danh sách các màu, size không trùng lặp
const sizes = computed(() => [...new Set(productDetail.value.map((p) => p.sizeValue))])

const colors = computed(() => [...new Set(productDetail.value.map((p) => p.colorName))])

const thumbnailImage = computed(
  () => productDetail.value.find((p) => p.isThumbnail)?.imageUrl || '',
)

const additionalImages = computed(() => [
  ...new Set(productDetail.value.filter((p) => !p.isThumbnail).map((p) => p.imageUrl)),
])

const viewLargeImg = (img: string) => {
  imgLightBox.value = img
  showImgProduct.value = true
}

const closeImageModal = () => {
  showImgProduct.value = false
}
const colorParam = ref<{ color: string }>({
  color: '',
})
const getProductDetail = async () => {
  const response = await productService.getProductDetailById(Number(route.params.id))
  productDetail.value = response.data
}

const formatPrice = (price: number) => {
  return Intl.NumberFormat('vi-Vn', { maximumFractionDigits: 0 }).format(price) + ' đ'
}
const sizeByColor = ref<SizeByColor[]>([])
const changeColor = async (index: number, color: string) => {
  selectedColor.value = index
  colorParam.value.color = color
  updateParam()

  const res = await productService.getSizeByColor(Number(route.params.id), color)
  sizeByColor.value = res.data
}
const changeSize = (index: number) => {
  selectedSize.value = index
}

const updateParam = () => {
  const params: Record<string, string> = {}

  if (colorParam.value.color) {
    params.color =colorParam.value.color.toString()
  }

  router.push({
    path: `/product-detail/${Number(route.params.id)}`,
    query: params,
  })
}

const filteredSizes = computed(() => {
  if (sizeByColor.value.length === 0) {
    return sizes.value.map((s) => ({
      size: s,
      stock: 1
    }))
  }
  return sizeByColor.value
})
onMounted(() => {
  getProductDetail().then(() => {
    const queryColor = Array.isArray(route.query.color)
      ? route.query.color[0]
      : route.query.color

    if (queryColor) {
      colorParam.value.color = queryColor

      // Tìm index tương ứng với color trong danh sách colors
      const index = colors.value.findIndex((color) => color === queryColor)
      selectedColor.value = index !== -1 ? index : null
    }
  })
})


</script>
