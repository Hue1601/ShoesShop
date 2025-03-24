package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ProductDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetail {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Products product;

    @ManyToOne
    @JoinColumn(name="ColorId")
    private Colors color;

    @ManyToOne
    @JoinColumn(name = "SizeId")
    private Sizes size;

    private Integer stock;
}

