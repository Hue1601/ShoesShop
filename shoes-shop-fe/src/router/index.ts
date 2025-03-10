import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      // component: HomeView,
      component: () => import('../views/login/LoginPage.vue')
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/login/RegisterPage.vue'),
    },
    {
      path: '/home',
      name: 'home',
      component: () => import('../views/product/HomePage.vue'),
    },
    {
      path: '/list-brand',
      name: 'listBrand',
      component: () => import('../views/brand/ListBrandPage.vue'),
    },
    {
      path: '/cart',
      name: 'cart',
      component: () => import('../views/product/CartPage.vue'),
    },
    {
      path: '/list-product',
      name: 'listProduct',
      component: () => import('../views/product/ListProduct.vue'),
    },
    {
      path: '/product-detail',
      name: 'productDetail',
      component: () => import('../views/product/ProductDetail.vue'),
    },
    {
      path: '/payment',
      name: 'payment',
      component: () => import('../views/payment/PaymentPage.vue'),
    },
  ],
})

export default router
