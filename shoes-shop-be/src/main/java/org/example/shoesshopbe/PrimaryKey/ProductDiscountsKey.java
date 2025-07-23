package org.example.shoesshopbe.PrimaryKey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProductDiscountsKey implements Serializable {
    @Column(name = "ProductID")
    private Integer product;
    @Column(name = "DiscountID")
    private Integer discount;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDiscountsKey that = (ProductDiscountsKey) o;
        return Objects.equals(product, that.product) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, discount);
    }
}
