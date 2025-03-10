package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Colors")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colors {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String colorName;
}
