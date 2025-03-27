package org.example.shoesshopbe.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SizeByColorResponse {
    private Integer stock;
    private String colorName;
    private Float size;
}
