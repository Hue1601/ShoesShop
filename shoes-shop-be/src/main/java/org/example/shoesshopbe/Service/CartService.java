package org.example.shoesshopbe.Service;

import org.example.shoesshopbe.Model.*;
import org.example.shoesshopbe.Repo.*;
import org.example.shoesshopbe.Response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartItemRepo cartItemRepo;


    @Autowired
    private ProductDetailRepo productDetailRepo;

    public List<CartResponse> getCart(Integer userId) {
        List<CartResponse> carts = cartRepo.getCart(userId);
        return carts;
    }

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
//        Products product = productRepo.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductDetail product = productDetailRepo.findById(productId)
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

    public void updateCartItem(Integer productDetailId,Integer quantity) {
        CartItems cartItems = cartItemRepo.findByProductId(productDetailId)
                .orElseThrow(() -> new RuntimeException("Product detail not found"));
        cartItems.setQuantity(quantity);
        cartItemRepo.save(cartItems);
    }

    public void deleteProductCart(Integer productDetailId) {
        CartItems productDetail = cartItemRepo.findByProductId(productDetailId)
                .orElseThrow(() -> new RuntimeException("Product detail not found"));
        cartItemRepo.deleteById(productDetail.getId());
    }
//    public void updateCartItem(Integer productDetailId, Integer quantity) {
//        CartItems cartItem = cartItemRepo.findByProductDetailId(productDetailId)
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm trong giỏ hàng"));
//
//        cartItem.setQuantity(quantity);
//        cartRepository.save(cartItem);
//    }

}
