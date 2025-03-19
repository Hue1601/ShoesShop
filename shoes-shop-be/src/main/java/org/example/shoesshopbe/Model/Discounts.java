package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table (name = "Discounts")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discounts {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Float discountPercentage;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime endAt;
}
