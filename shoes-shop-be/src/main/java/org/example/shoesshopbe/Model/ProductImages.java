package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ProductImages")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImages {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="ProductID")
    private Products product;

    private String imageUrl;

    @Column(name="IsThumbnail")
    private boolean isThumbnail;
}
