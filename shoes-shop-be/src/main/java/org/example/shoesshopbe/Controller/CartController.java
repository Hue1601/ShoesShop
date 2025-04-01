package org.example.shoesshopbe.Controller;

import org.example.shoesshopbe.Model.Carts;
import org.example.shoesshopbe.Repo.CartRepo;
import org.example.shoesshopbe.Request.CartItemRequest;
import org.example.shoesshopbe.Request.UpdateCartRequest;
import org.example.shoesshopbe.Response.CartResponse;
import org.example.shoesshopbe.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest req) {
        try {
            cartService.addToCart(req.getUserId(),req.getProductId(),req.getQuantity());
            return ResponseEntity.ok("Đã thêm vào giỏ hàng!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCart(@PathVariable Integer id) {
        List<CartResponse> cart = cartService.getCart(id);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update-quantity")
    public ResponseEntity<?> updateQuantity(@RequestBody UpdateCartRequest req) {
         cartService.updateCartItem(req.getProductDetailId(),req.getQuantity());
         return  ResponseEntity.ok("Cập nhật thành côg");
    }

    @DeleteMapping("/delete/{productDetailId}")
    public ResponseEntity<?> deleteCart(@PathVariable Integer productDetailId) {
        cartService.deleteProductCart(productDetailId);
        return ResponseEntity.ok("Delete thành công");
    }
}
