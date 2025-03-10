package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.shoesshopbe.PrimaryKey.ProductCollectionsKey;

@Table(name = "ProductCollections")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCollections {
    @EmbeddedId
    private ProductCollectionsKey id;

    @ManyToOne
    @JoinColumn(name="ProductID")
    private Products product;

    @ManyToOne
    @JoinColumn(name="CollectionID")
    private Collections collection;

}
