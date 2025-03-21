package org.example.shoesshopbe.Controller;

import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Response.ColorResponse;
import org.example.shoesshopbe.Response.ProductResponse;
import org.example.shoesshopbe.Service.ProductService;
import org.example.shoesshopbe.Service.SitebarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> findAllProducts(@RequestParam(required = false) List<String> discount,
                                             @RequestParam(required = false) String gender,
                                             @RequestParam(required = false) List<String> brand,
                                             @RequestParam(required = false) List<String> collection,
                                             @RequestParam(required = false) List<String> color,
                                             @RequestParam(required = false) List<String> price,
                                             @RequestParam(required = false) String keyword,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "16") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductResponse> products;
        if (discount!=null && !discount.isEmpty() ||
                gender != null && !gender.isEmpty() ||
                brand != null && !brand.isEmpty() ||
                collection != null && !collection.isEmpty() ||
                color != null && !color.isEmpty() ||
                price != null && !price.isEmpty() ||
                keyword != null && !keyword.isEmpty()) {
            products = sitebarService.findAllProducts(discount, gender, brand, collection, color, price,keyword,pageable);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }

         products = productService.findAllProduct(pageable);
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
