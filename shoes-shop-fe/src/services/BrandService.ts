import axios from 'axios'

class BrandService {
  private ROOT_URL = 'http://localhost:8080'

  async getAllBrands() {
    const response = await axios.get(`${this.ROOT_URL}/getAllBrands`)
    return response;
  }
}
export const brandService= new BrandService();
