package org.example.shoesshopbe.PrimaryKey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ProductCollectionsKey implements Serializable {
    private int product;
    private int collection;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() !=o.getClass()) return false;
        ProductCollectionsKey that = (ProductCollectionsKey) o;
        return Objects.equals(product,that.product) && Objects.equals(collection,that.collection);
    }

    @Override
    public int hashCode(){
        return Objects.hash(product,collection);
    }
}
