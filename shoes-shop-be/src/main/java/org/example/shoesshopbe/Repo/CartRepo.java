package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.CartItems;
import org.example.shoesshopbe.Model.Carts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Carts,Integer> {
    Optional<Carts> findByUserId(Integer userId);


}
