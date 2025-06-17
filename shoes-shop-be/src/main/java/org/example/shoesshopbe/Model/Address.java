package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@Table(name = "Address")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String addressDetail;

    private String communeLabel;

    private Integer communeValue;

    private String districtLabel;

    private Integer districtValue;

    private String provinceLabel;

    private Integer provinceValue;
}
