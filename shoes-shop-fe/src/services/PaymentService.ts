import axios from 'axios'

class PaymentService{
  private baseURL = "http://localhost:8080/api/payment";
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
  async calculateShippingFee(body:any) {
    const response = await axios.post(`${this.baseURL}/shipping-fee`, body);
    return response.data;
  }
  async saveEmail(body:any){
    const saveEmail = await axios.post(`${this.baseURL}/email`,body)
    return saveEmail;
  }
}
export const paymentService = new PaymentService();
