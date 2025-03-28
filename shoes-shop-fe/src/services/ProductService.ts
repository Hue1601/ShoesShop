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
  async getAllProduct(filters = {}){
    const response = await axios.get(`${this.ROOT_API}/products`,{
      params:filters
    });
    return response;
  }
  async findAllProductOrderByAsc(){
    const response = await axios.get(`${this.ROOT_API}/products-asc`);
    return response;
  }
  async findAllProductOrderByPriceDesc(){
    const response = await axios.get(`${this.ROOT_API}/price-desc`);
    return response;
  }
  async findAllProductOrderByPriceAsc(){
    const response = await axios.get(`${this.ROOT_API}/price-asc`);
    return response;
  }

  async findProductBySearch(){
    const response = await axios.get(`${this.ROOT_API}/search`);
    return response;
  }

  async getProductDetailById(id : number){
    const response = await axios.get(`${this.ROOT_API}/${id}`);
    return response;
  }
  async getSizeByColor(id : number,color:string){
    const response = await axios.get(`${this.ROOT_API}/${id}`, {
      params: { color },
    })
    return response;
  }
  async getRelatedProduct(productId:number){
    const response = await axios.get(`${this.ROOT_API}/related-product`,{
      params: { productId }
      }
    )
    return response;
  }
}
export const productService = new ProductService();
