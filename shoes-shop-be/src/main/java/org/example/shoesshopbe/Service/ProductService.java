package org.example.shoesshopbe.Service;

import org.example.shoesshopbe.Repo.ColorRepo;
import org.example.shoesshopbe.Repo.ProductRepo;
import org.example.shoesshopbe.Response.ColorResponse;
import org.example.shoesshopbe.Response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<ProductResponse> getTop5Products() {
        return productRepo.findTop5Product();
    }

    public List<ProductResponse> getTopProduct5ByLastCollection() {
        return productRepo.findTop5ProductsFromLatestCollection();
    }

    public Page<ProductResponse> findAllProduct(Pageable pageable) {
        return productRepo.findAllProducts(pageable);
    }

    public List<ProductResponse> findAllProductOrderByAsc() {
        return productRepo.findAllProductsOrderByAsc();
    }

    public List<ProductResponse> findAllProductOrderByPriceDesc() {
        return productRepo.findAllProductsOrderByPriceDesc();
    }
    public List<ProductResponse> findAllProductOrderByPriceAsc() {
        return productRepo.findAllProductsOrderByPriceAsc();
    }
    
    public List<ProductResponse> getProductBySearch() {
        return productRepo.getProductBySearch();
    }
}
