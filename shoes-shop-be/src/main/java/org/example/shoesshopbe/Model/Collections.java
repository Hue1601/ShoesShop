package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "Collections")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collections {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String collectionName;
    private LocalDateTime createdAt;
}
