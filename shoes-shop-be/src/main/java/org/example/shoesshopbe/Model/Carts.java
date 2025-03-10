package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "Carts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="UserID")
    private Users user;
    private LocalDateTime createdAt;
}
