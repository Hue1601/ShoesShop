package org.example.shoesshopbe.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;


@NoArgsConstructor
@Data
public class ProductDetailResponse {
    private Integer id;
    private String productName;
    private String brandName;
    private BigDecimal price;
    private String imageUrl;
    private String colorName;
    private Float sizeValue;
    private Integer stock;
    private Float discountPercentage;
    private String description;
    private Boolean isThumbnail;
    private Integer categoryId;
//    private BigDecimal discountPrice;

    public ProductDetailResponse(
            Integer id,
            String productName,
            String brandName,
            BigDecimal price,
            String imageUrl,
            String colorName,
            Float sizeValue,
            Integer stock,
            Float discountPercentage,
            String description,
            Integer categoryId,
            Boolean isThumbnail
    ) {
        this.id = id;
        this.productName = productName;
        this.brandName = brandName;
        this.price = price;
        this.imageUrl = imageUrl;
        this.colorName = colorName;
        this.sizeValue = sizeValue;
        this.stock = stock;
        this.discountPercentage = discountPercentage;
        this.description = description;
        this.isThumbnail = isThumbnail;
         this.categoryId = categoryId;
//        if (discountPercentage != null && price != null) {
//            BigDecimal discount = BigDecimal.valueOf(100 - discountPercentage)
//                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
//            this.discountPrice = price.multiply(discount).setScale(0, RoundingMode.HALF_UP);
//        } else {
//            this.discountPrice = null;
//        }
    }
}
