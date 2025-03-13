package org.example.shoesshopbe.Service;

import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Repo.ProductRepo;
import org.example.shoesshopbe.Response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<ProductResponse> getAllProducts() {

        List<ProductResponse> products = productRepo.findTop5Product();
        return products;
    }

    public List<ProductResponse> getTopProduct5ByLastCollection() {
        List<ProductResponse> products = productRepo.findTop5ProductsFromLatestCollection();
        return products;
    }
}
