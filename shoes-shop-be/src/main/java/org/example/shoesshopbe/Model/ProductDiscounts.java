package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.shoesshopbe.PrimaryKey.ProductDiscountsKey;

@Table(name = "ProductDiscounts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDiscounts {
     @EmbeddedId
    private ProductDiscountsKey id;

    @ManyToOne
    @MapsId("product")
    @JoinColumn(name = "ProductID")
    private Products product;

    @ManyToOne
    @MapsId("discount")
    @JoinColumn(name = "DiscountID")
    private Discounts discount;

}
