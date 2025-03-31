package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.CartItems;
import org.example.shoesshopbe.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepo extends JpaRepository<CartItems,Integer> {
    Optional<CartItems> findByCartIdAndProductId(Integer cartId, Integer productId);

}
