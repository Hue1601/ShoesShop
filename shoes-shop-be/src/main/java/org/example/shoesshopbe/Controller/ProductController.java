package org.example.shoesshopbe.Controller;

import org.example.shoesshopbe.Response.ProductDetailResponse;
import org.example.shoesshopbe.Response.ProductResponse;
import org.example.shoesshopbe.Response.SizeByColorResponse;
import org.example.shoesshopbe.Service.ProductService;
import org.example.shoesshopbe.Service.SitebarService;
import org.example.shoesshopbe.common.DataTableRequest;
import org.example.shoesshopbe.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Response findAllProducts(DataTableRequest request,
                                    @RequestParam(required = false) List<String> discount,
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
            return new Response().data(products);
        }
//         products = productService.findAllProduct(pageable);
        products = productService.findAllProduct(request);
//        return new ResponseEntity<>(products, HttpStatus.OK);
        return Response.build().ok().data(products);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductBySearch(){
        List<ProductResponse> products =productService.getProductBySearch();
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


    @GetMapping(value = "/{id}", params = "!color" )
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        List<ProductDetailResponse> product = productService.getProductDetailById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping(value ="/{id}",params = "color")
    public ResponseEntity<?> getProductByColor(@PathVariable Integer id,@RequestParam String color) {
        List<SizeByColorResponse> product = productService.findSizeByColor(id,color);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/related-product")
    public ResponseEntity<?> getProductRelatedByProductId(@RequestParam Integer productId) {
        List<ProductResponse> products = productService.getProductRelated(productId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
