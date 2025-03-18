package org.example.shoesshopbe.Controller;

import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Response.ProductResponse;
import org.example.shoesshopbe.Service.ProductService;
import org.example.shoesshopbe.Service.SitebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SitebarService sitebarService;

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
    public ResponseEntity<?> findAllProducts(@RequestParam(required = false) String discount,
                                             @RequestParam(required = false) String gender,
                                             @RequestParam(required = false) String brand,
                                             @RequestParam(required = false) String collection,
                                             @RequestParam(required = false) String color,
                                             @RequestParam(required = false) String price
    ) {
        if (discount!=null && !discount.isEmpty()|| gender != null && !gender.isEmpty() || brand != null && !brand.isEmpty() || collection != null && !collection.isEmpty() || color != null && !color.isEmpty() ) {
            List<Products> pro = sitebarService.findAllProducts(discount, gender, brand, collection, color, price);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        }

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
