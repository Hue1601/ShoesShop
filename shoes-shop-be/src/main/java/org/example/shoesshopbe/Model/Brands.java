package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Brands")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brands {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brandName;
}
