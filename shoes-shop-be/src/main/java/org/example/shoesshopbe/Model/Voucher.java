package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name="Voucher")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private LocalDateTime createdAt;

    private LocalDateTime startAt;

    private LocalDateTime endDate;

    private LocalDateTime updatedAt;

    private String name;

    private Integer quantity;

    private BigDecimal maxValue;

    private Integer type;

    private BigDecimal value;
}
