package org.example.shoesshopbe.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private Integer productDetailId;
    private String productName;
    private String brandName;
    private BigDecimal price;
    private String imageUrl;
    private Integer quantity;
    private String colorName ;
    private Float sizeValue;
}
