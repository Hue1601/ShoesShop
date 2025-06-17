import axios from 'axios'

class PaymentService{
  private baseURL = "http://localhost:8080/api/address";
  async getProvince(){
    const response = await axios.get(`${this.baseURL}/provinces`)
    return response.data;
  }
  async getDistrict(provinceId: number) {
    const response = await axios.post(`${this.baseURL}/districts`, null, {
      params: { province_id: provinceId }
    });
    return response.data;
  }

  async getCommune(districtId: number) {
    const response = await axios.post(`${this.baseURL}/commune`, null, {
      params: { district_id: districtId }
    });
    return response.data;
  }
}
export const paymentService = new PaymentService();
