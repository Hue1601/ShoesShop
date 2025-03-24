package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Size")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sizes {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float sizeValue;
}
