package org.example.shoesshopbe.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "CartItems")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="CartID")
    private Carts cart;

    @ManyToOne
    @JoinColumn(name="ProductID")
    private Products product;

    private Integer quantity;


}
