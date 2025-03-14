package org.example.shoesshopbe.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Integer id;
    private String productName;
    private String brandName;
    private String price;
    private String imageUrl;
}
