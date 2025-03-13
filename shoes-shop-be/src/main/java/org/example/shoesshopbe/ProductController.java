package org.example.shoesshopbe;

import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Repo.ProductRepo;
import org.example.shoesshopbe.Reponse.ProductResponse;
import org.example.shoesshopbe.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
   @Autowired
   private ProductService productService;

   @Autowired
   private ProductRepo productRepo;
   @GetMapping("/h")
   public ResponseEntity<?> getProducts() {
       List<Products> products = productRepo.findAll();
       return new ResponseEntity<>(products, HttpStatus.OK);
   }
    @GetMapping("/list")
    public ResponseEntity<?> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/five-product-latest-collection")
    public ResponseEntity<?> getTop5ProductByLatestCollection() {
       List<ProductResponse> products = productService.getTopProduct5ByLastCollection();
       return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
