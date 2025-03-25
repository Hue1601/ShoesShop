package org.example.shoesshopbe.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDetailResponse {
    private Integer id;
    private String productName;
    private String brandName;
    private String price;
    private String imageUrl;
    private String colorName;
    private Float sizeValue;
    private Integer stock;
    private Float discountPercentage;
    private String description;
    private Boolean isThumbnail;
}
