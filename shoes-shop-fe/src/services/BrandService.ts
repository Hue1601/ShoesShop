import axios from 'axios'

class BrandService {
  private ROOT_API = "http://localhost:8080/sitebar";
  async getAll(){
    const response = await axios.get(`${this.ROOT_API}/brand`)
    return response;
  }
}
export const brandService= new BrandService();
