package org.example.shoesshopbe.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemRequest {
    private Integer userId;
    private Integer productId;
    private Integer quantity;

}
