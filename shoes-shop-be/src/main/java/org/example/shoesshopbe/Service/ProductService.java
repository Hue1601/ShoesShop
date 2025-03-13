package org.example.shoesshopbe.Service;

import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Repo.ProductRepo;
import org.example.shoesshopbe.Reponse.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<ProductResponse> getAllProducts() {

        List<Products> products = productRepo.findTop5ByOrderByCreatedAtDesc();
        return products.stream().map(product ->
                new ProductResponse(
                        product.getProductName(),
                        product.getBrand().getBrandName(),
                        String.valueOf(product.getPrice())
                )
        ).collect(Collectors.toList());
    }

    public List<ProductResponse> getTopProduct5ByLastCollection() {
      List<Products> products = productRepo.findTop5ProductsFromLatestCollection();
      return products.stream().map(product ->
              new ProductResponse(
                      product.getProductName(),
                      product.getBrand().getBrandName(),
                      String.valueOf(product.getPrice())
              )
              ).collect(Collectors.toList());
    }
}
