package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Products, Integer> {
    List<Products> findTop5ByOrderByCreatedAtDesc();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                       p.productName, b.brandName, Cast(p.price AS String), img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id
                ORDER BY p.createdAt DESC
            """)
    List<ProductResponse> findTop5Product();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                    p.productName, p.brand.brandName, Cast( p.price As string ), img.imageUrl) FROM Products p
                LEFT JOIN ProductCollections pc ON p.id = pc.product.id
                 LEFT JOIN ProductImages img ON p.id = img.product.id
                LEFT JOIN Collections c ON pc.collection.id = c.id
                WHERE c.id = (SELECT id FROM Collections ORDER BY createdAt DESC LIMIT 1)
                ORDER BY p.createdAt DESC
            """)
    List<ProductResponse> findTop5ProductsFromLatestCollection();

}
