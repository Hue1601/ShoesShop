import axios from 'axios';

class CartService {

  async addToCart (productId: number, quantity: number, userId: number) {
    const cart = await axios.post('http://localhost:8080/cart/add-to-cart', {
      productId: productId,
      quantity: quantity,
      userId: userId
    });
    return cart;
  }
}
export const cartService = new CartService();
