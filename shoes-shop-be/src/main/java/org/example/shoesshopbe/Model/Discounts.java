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
    @Column(name = "DiscountPercentage")
    private Float discountPercentage;

    @Column(name = "CreateAt")
    private LocalDateTime createdAt;
    @Column(name = "StartDate")
    private LocalDateTime startedAt;
    @Column(name = "EndDate")
    private LocalDateTime endAt;
}
