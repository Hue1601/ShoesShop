package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.example.shoesshopbe.Exception.MessageError;

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
    private String password;
    @Column(name="PhoneNumber")
    @NotBlank(message = "Vui lòng nhập đầy đủ thông tin")
    private String phoneNumber;
    @NotBlank(message = "Vui lòng nhập đầy đủ thông tin")
    private String email;
    @Column(name="GoogleLogin")
    private boolean googleLogin;
    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}



