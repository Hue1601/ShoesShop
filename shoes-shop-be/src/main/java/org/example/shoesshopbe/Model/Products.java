package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "Products")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;

    @ManyToOne
    @JoinColumn(name = "BrandID")

    private Brands brand;
    private String description;
    private BigDecimal price;
    private String gender;
    private LocalDateTime createdAt;
}
