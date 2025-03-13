package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="Users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Vui lòng nhập đầy đủ thông tin")
    private String username;

    @NotBlank(message = "Vui lòng nhập đầy đủ thông tin")
    @Size(min = 6, message = "Mật khẩu ít nhất là 6 ký tự")
    private String password;

    @Column(name="PhoneNumber")
    @NotBlank(message = "Vui lòng nhập đầy đủ thông tin")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại chỉ được chứa số và phải có đúng 10 chữ số")
    private String phoneNumber;

    @NotBlank(message = "Vui lòng nhập đầy đủ thông tin")
    @Email(message="Vui lòng nhập đúng định dạng email")
    private String email;

    @Column(name="GoogleLogin")
    private boolean googleLogin;
    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}



