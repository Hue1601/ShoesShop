package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Response.ProductDetailResponse;
import org.example.shoesshopbe.Response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Products, Integer> , JpaSpecificationExecutor<Products> {
    List<Products> findTop5ByOrderByCreatedAtDesc();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                     p.id,  p.productName, b.brandName, Cast(p.price AS String), img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id AND img.isThumbnail = true
                ORDER BY p.createdAt DESC LIMIT 5
            """)
    List<ProductResponse> findTop5Product();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                 p.id,  p.productName, p.brand.brandName, Cast( p.price As string ), img.imageUrl) FROM Products p
                LEFT JOIN ProductCollections pc ON p.id = pc.product.id
                 LEFT JOIN ProductImages img ON p.id = img.product.id AND img.isThumbnail = true
                LEFT JOIN Collections c ON pc.collection.id = c.id
                WHERE c.id = (SELECT id FROM Collections ORDER BY createdAt DESC LIMIT 1)
                ORDER BY p.createdAt DESC LIMIT 5
            """)
    List<ProductResponse> findTop5ProductsFromLatestCollection();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                    p.id, p.productName, b.brandName, Cast(p.price AS String),img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id AND img.isThumbnail = true
                ORDER BY p.createdAt DESC
            """)
    Page<ProductResponse> findAllProducts(Pageable pageable);

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                    p.id , p.productName, b.brandName, Cast(p.price AS String), img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id AND img.isThumbnail = true
                ORDER BY p.createdAt Asc
            """)
    List<ProductResponse> findAllProductsOrderByAsc();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                    p.id ,p.productName, b.brandName, Cast(p.price AS String), img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id AND img.isThumbnail = true
                ORDER BY p.price Asc
            """)
    List<ProductResponse> findAllProductsOrderByPriceAsc();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                     p.id,  p.productName, b.brandName, Cast(p.price AS String), img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id AND img.isThumbnail = true
                ORDER BY p.price desc""")
    List<ProductResponse> findAllProductsOrderByPriceDesc();

    @Query("""
                SELECT new org.example.shoesshopbe.Response.ProductResponse(
                    p.id, p.productName, b.brandName, Cast(p.price AS String),img.imageUrl)  FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id AND img.isThumbnail = true
                ORDER BY p.createdAt DESC
            """)
    List<ProductResponse> getProductBySearch();

    @Query("""
             SELECT new org.example.shoesshopbe.Response.ProductDetailResponse(
            p.id, p.productName, b.brandName, Cast(p.price AS String),img.imageUrl,c.colorName,
            s.sizeValue,pd.stock,d.discountPercentage,p.description,img.isThumbnail) FROM Products p
                LEFT JOIN p.brand b
                LEFT JOIN ProductImages img ON p.id = img.product.id
                LEFT JOIN ProductDetail pd ON p.id = pd.product.id
                LEFT JOIN Colors c ON pd.color.id = c.id
                LEFT JOIN Sizes s ON pd.size.id = s.id
                LEFT JOIN ProductDiscounts pdsc ON pdsc.product.id = p.id
                LEFT JOIN Discounts d ON pdsc.discount.id = d.id
                WHERE p.id = :id
            """)
    List<ProductDetailResponse> getProductDetail(@Param("id") String id);



}

