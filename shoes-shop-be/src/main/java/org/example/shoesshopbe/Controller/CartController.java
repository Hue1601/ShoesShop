package org.example.shoesshopbe.Controller;

import org.example.shoesshopbe.Model.Carts;
import org.example.shoesshopbe.Repo.CartRepo;
import org.example.shoesshopbe.Request.CartItemRequest;
import org.example.shoesshopbe.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody CartItemRequest req) {
        System.out.println("add-to-cart");
        try {
            cartService.addToCart(req.getUserId(),req.getProductId(),req.getQuantity());
            return ResponseEntity.ok("Đã thêm vào giỏ hàng!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
