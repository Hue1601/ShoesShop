package org.example.shoesshopbe.PrimaryKey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorsKey implements Serializable {
    private Integer product;
    private Integer color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductColorsKey that = (ProductColorsKey) o;
        return Objects.equals(product, that.product) && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, color);
    }
}
