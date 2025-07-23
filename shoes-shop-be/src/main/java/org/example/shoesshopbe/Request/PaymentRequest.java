package org.example.shoesshopbe.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentRequest {
    private int from_district_id;
    private int to_district_id;
    private int to_commune_code;
    private int height;
    private int length;
    private int weight;
    private int width;
    private int insurance_value;
}
