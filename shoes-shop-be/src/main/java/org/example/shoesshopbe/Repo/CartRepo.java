package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.CartItems;
import org.example.shoesshopbe.Model.Carts;
import org.example.shoesshopbe.Response.CartResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Carts,Integer> {
    Optional<Carts> findByUserId(Integer userId);

    @Query("""
       SELECT new org.example.shoesshopbe.Response.CartResponse(
       pd.id,p.productName,b.brandName,pd.price,pimg.imageUrl,ci.quantity,col.colorName,s.sizeValue
       )
       FROM CartItems ci
       LEFT JOIN ProductDetail pd ON pd.id = ci.product.id  
       LEFT JOIN Products p ON pd.product.id = p.id
        LEFT JOIN Brands b ON p.brand.id = b.id
       LEFT JOIN Colors col ON pd.color.id = col.id
       LEFT JOIN Sizes s ON pd.size.id = s.id
       LEFT JOIN Carts c ON c.id = ci.cart.id
       LEFT JOIN Users u ON u.id = c.user.id
       LEFT JOIN ProductImages pimg ON p.id = pimg.product.id AND pimg.isThumbnail= true 
       WHERE u.id = :userId
""")
    List<CartResponse> getCart(@Param( "userId") Integer userId);

}
