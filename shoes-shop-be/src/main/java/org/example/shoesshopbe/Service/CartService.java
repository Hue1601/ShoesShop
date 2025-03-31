package org.example.shoesshopbe.Service;

import org.example.shoesshopbe.Model.CartItems;
import org.example.shoesshopbe.Model.Carts;
import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Model.Users;
import org.example.shoesshopbe.Repo.CartItemRepo;
import org.example.shoesshopbe.Repo.CartRepo;
import org.example.shoesshopbe.Repo.ProductRepo;
import org.example.shoesshopbe.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private ProductRepo productRepo;


    public void addToCart(Integer userId, Integer productId, Integer quantity) {
        // Lấy thông tin user (nếu không có thì báo lỗi)
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra xem giỏ hàng có tồn tại không, nếu chưa có thì tạo mới
        Carts carts = cartRepo.findByUserId(userId)
                .orElseGet(() -> {
                    Carts newCarts = new Carts();
                    newCarts.setUser(user);
                    return cartRepo.save(newCarts);
                });

        // Lấy sản phẩm (nếu không có thì báo lỗi)
        Products product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        CartItems cartItems = cartItemRepo.findByCartIdAndProductId(carts.getId(), productId)
                .orElse(new CartItems());  // Nếu chưa có thì tạo mới

        // Cập nhật thông tin sản phẩm trong giỏ hàng
        cartItems.setCart(carts);
        cartItems.setProduct(product);
        cartItems.setQuantity((cartItems.getQuantity() != null ? cartItems.getQuantity() : 0) + quantity);


        // Lưu thông tin sản phẩm vào giỏ hàng
        cartItemRepo.save(cartItems);
    }
}
