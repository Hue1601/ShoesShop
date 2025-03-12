import axios from 'axios'

class LoginService {
  private ROOT_API = import.meta.env.VITE_ROOT_API || "http://localhost:8080/user";

  async login(username: string, password: string) {
    try {
      const response = await axios.post(`${this.ROOT_API}/login`,
        { username, password }
      );
      return response;
    } catch (error) {
      throw error;
    }
  }

  async  register(username: string, password: string,phoneNumber: string, email: string){
    try {
      const response = await axios.post(`${this.ROOT_API}/register`,
        { username, password, phoneNumber, email })
      return response;
    } catch (error) {
      console.error("Lỗi đăng nhập:", error);
      throw error;
    }
  }
}

export const loginService = new LoginService();


