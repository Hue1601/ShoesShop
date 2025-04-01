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
  async getCart(id:number){
    const list = await axios.get(`http://localhost:8080/cart/${id}`);
    return list;
  }
  async updateQuantity(productDetailId:number, quantity:number){
    const update = await axios.put("http://localhost:8080/cart/update-quantity", {
      productDetailId:productDetailId,
      quantity:quantity
    });
    return update;
  }
  async deleteProduct(id:number){
    const deleteProduct = await axios.delete(`http://localhost:8080/cart/delete/${id}`);
    return deleteProduct;
  }
}
export const cartService = new CartService();
