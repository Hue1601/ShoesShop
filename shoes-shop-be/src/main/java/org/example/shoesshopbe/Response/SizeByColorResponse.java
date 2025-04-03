package org.example.shoesshopbe.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SizeByColorResponse {
    private Integer stock;
    private String colorName;
    private Float size;
    private BigDecimal price;
}
