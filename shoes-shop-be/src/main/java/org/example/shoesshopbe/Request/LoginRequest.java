package org.example.shoesshopbe.Request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
//    @NotBlank(message = "Tên đăng nhập không được để trống")
    public String username;
//    @NotBlank(message = "Password không được để trống")
    public String password;
}
