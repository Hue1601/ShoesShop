package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.Products;

import org.example.shoesshopbe.Response.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Products, Integer> {
    List<Products> findTop5ByOrderByCreatedAtDesc();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                     p.id,  p.productName, b.brandName, Cast(p.price AS String), img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id
                ORDER BY p.createdAt DESC LIMIT 5
            """)
    List<ProductResponse> findTop5Product();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                 p.id,  p.productName, p.brand.brandName, Cast( p.price As string ), img.imageUrl) FROM Products p
                LEFT JOIN ProductCollections pc ON p.id = pc.product.id
                 LEFT JOIN ProductImages img ON p.id = img.product.id
                LEFT JOIN Collections c ON pc.collection.id = c.id
                WHERE c.id = (SELECT id FROM Collections ORDER BY createdAt DESC LIMIT 1)
                ORDER BY p.createdAt DESC LIMIT 5
            """)
    List<ProductResponse> findTop5ProductsFromLatestCollection();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                    p.id, p.productName, b.brandName, Cast(p.price AS String),img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id
                ORDER BY p.createdAt DESC
            """)
    List<ProductResponse> findAllProducts();

//    @Query("""
//    SELECT p.productName, b.brandName, Cast(p.price AS String), c.colorName, img.imageUrl,p.id
//    FROM Products p
//    LEFT JOIN p.brand b
//    LEFT JOIN ProductImages img ON p.id = img.product.id
//    LEFT JOIN ProductColors pc on p.id= pc.product.id
//    LEFT JOIN Colors c ON pc.color.id = c.id
//    ORDER BY p.createdAt DESC
//""")
//    List<Object[]> findAllProducts();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                    p.id , p.productName, b.brandName, Cast(p.price AS String), img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id
                ORDER BY p.createdAt Asc
            """)
    List<ProductResponse> findAllProductsOrderByAsc();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                    p.id ,p.productName, b.brandName, Cast(p.price AS String), img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id
                ORDER BY p.price Asc
            """)
    List<ProductResponse> findAllProductsOrderByPriceAsc();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                     p.id,  p.productName, b.brandName, Cast(p.price AS String), img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id
                ORDER BY p.price desc""")
    List<ProductResponse> findAllProductsOrderByPriceDesc();


}
