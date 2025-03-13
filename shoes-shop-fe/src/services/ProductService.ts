import axios from "axios";

class ProductService {
  private ROOT_API = "http://localhost:8080/product";
  async getProduct(){
      const response = await axios.get(`${this.ROOT_API}/list`);
      return response
  }

  async  getProductByLatestCollection(){
    const response = await  axios.get(`${this.ROOT_API}/five-product-latest-collection`)
    return response;
  }
}
export const productService = new ProductService();
