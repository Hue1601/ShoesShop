package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.shoesshopbe.PrimaryKey.ProductCategoryKey;

@Table(name = "ProductCategory")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductCategory {
    @EmbeddedId
    private ProductCategoryKey id;

    @ManyToOne
    @JoinColumn(name="ProductID")
    private Products product;

    @ManyToOne
    @JoinColumn(name="CategoryID")
    private Category category;

}
