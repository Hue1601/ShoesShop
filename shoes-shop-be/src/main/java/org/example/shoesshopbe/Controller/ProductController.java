package org.example.shoesshopbe.Controller;

import org.example.shoesshopbe.Model.Brands;
import org.example.shoesshopbe.Repo.BrandRepo;
import org.example.shoesshopbe.Response.ProductResponse;
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

    @GetMapping("/list")
    public ResponseEntity<?> getAllProducts() {
        List<ProductResponse> products = productService.getTop5Products();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/five-product-latest-collection")
    public ResponseEntity<?> getTop5ProductByLatestCollection() {
        List<ProductResponse> products = productService.getTopProduct5ByLastCollection();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> findAllProducts() {
        List<ProductResponse> products = productService.findAllProduct();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products-asc")
    public ResponseEntity<?> findAllProductOrderByAsc() {
        List<ProductResponse> products = productService.findAllProductOrderByAsc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/price-desc")
    public ResponseEntity<?> findAllProductOrderByPriceDesc() {
        List<ProductResponse> products = productService.findAllProductOrderByPriceDesc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/price-asc")
    public ResponseEntity<?> findAllProductOrderByPriceAsc() {
        List<ProductResponse> products = productService.findAllProductOrderByPriceAsc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
