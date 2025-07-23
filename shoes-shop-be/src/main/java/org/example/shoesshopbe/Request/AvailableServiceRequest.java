package org.example.shoesshopbe.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableServiceRequest {
    private int shop_id;
    private int from_district;
    private int to_district;
}
