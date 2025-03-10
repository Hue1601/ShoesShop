package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.shoesshopbe.PrimaryKey.ProductColorsKey;

@Table(name = "ProductColors")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductColors {
    @EmbeddedId
    private ProductColorsKey id;

    @ManyToOne
    @JoinColumn(name="ProductID")
    private Products product;

    @ManyToOne
    @JoinColumn(name="ColorID")
    private Colors color;

}
