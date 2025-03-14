import axios from 'axios';

class SitebarService {
   private ROOT_API = "http://localhost:8080/sitebar";
   async getAll(){
     const response = await axios.get(`${this.ROOT_API}/brand`)
     return response;
   }

  async getCollection(){
    const response = await axios.get(`${this.ROOT_API}/collection`)
    return response;
  }
  async getColor(){
    const response = await axios.get(`${this.ROOT_API}/color`)
    return response;
  }
}
export const sitebarService = new SitebarService();
