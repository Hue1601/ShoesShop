package org.example.shoesshopbe.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCartRequest {
    private Integer productDetailId;
    private Integer quantity;
}
