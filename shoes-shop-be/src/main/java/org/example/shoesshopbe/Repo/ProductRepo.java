package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Reponse.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Products,Integer> {
    List<Products> findTop5ByOrderByCreatedAtDesc();

    @Query(value = """
        SELECT TOP 5 p.* 
        FROM Products p
        JOIN ProductCollections pc ON p.ID = pc.ProductID
        JOIN Collections c ON pc.CollectionID = c.ID
        WHERE c.ID = (SELECT TOP 1 ID FROM Collections ORDER BY CreatedAt DESC)
        ORDER BY p.CreatedAt DESC
    """, nativeQuery = true)
    List<Products> findTop5ProductsFromLatestCollection();
}
