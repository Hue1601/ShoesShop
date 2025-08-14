// src/stores/cartStore.ts
import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import type { Cart } from '@/interface/interface'

type CartWithFinalPrice = Cart & { finalPrice: number }

export const useCartStore = defineStore('cart', () => {
  const selectedCartItems = ref<CartWithFinalPrice[]>([])

  const setSelectedItems = (items: Cart[]) => {
    selectedCartItems.value = items.map(item => {
      const discount = item.discountPercentage || 0
      const finalPrice = item.price * (1 - discount / 100)
      return {
        ...item,
        finalPrice,
      }
    })
  }

  const addItem = (item: Cart) => {
    const exists = selectedCartItems.value.find(i => i.productDetailId === item.productDetailId)
    if (!exists) {
      const discount = item.discountPercentage || 0
      selectedCartItems.value.push({
        ...item,
        finalPrice: item.price * (1 - discount / 100)
      })
    }
  }

  const clearItems = () => {
    selectedCartItems.value = []
  }

  const totalQuantity = computed(() => selectedCartItems.value.length)

  const totalPrice = computed(() =>
    selectedCartItems.value.reduce((acc, item) =>
      acc + item.finalPrice * item.quantity, 0)
  )

  return {
    selectedCartItems,
    setSelectedItems,
    addItem,
    clearItems,
    totalQuantity,
    totalPrice
  }
})
