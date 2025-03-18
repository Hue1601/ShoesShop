package org.example.shoesshopbe.Service;


import org.example.shoesshopbe.Repo.ProductRepo;
import org.example.shoesshopbe.Response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ProductResponse> findAllProduct() {
        return productRepo.findAllProducts();
    }

//public List<ListProductResponse> findAllProduct() {
//    List<Object[]> rawProducts = productRepo.findAllProducts();
//    System.out.println("hue " +rawProducts);
//    Map<String, ListProductResponse> productMap = new HashMap<>();
//
//    for (Object[] row : rawProducts) {
//        String productName = (String) row[0];
//        String brandName = (String) row[1];
//        String price = (String) row[2];
//        String colorName = (String) row[3];
//        String imageUrl = (String) row[4];
//        Integer id = (Integer) row[5];
//
//        productMap.computeIfAbsent(productName, key ->
//                new ListProductResponse(productName, brandName, price, new ArrayList<>(), imageUrl,id)
//        ).getColor().add(colorName);
//    }
//
//    return new ArrayList<>(productMap.values());
//}

    public List<ProductResponse> findAllProductOrderByAsc() {
        return productRepo.findAllProductsOrderByAsc();
    }
    public List<ProductResponse> findAllProductOrderByPriceDesc() {
        return productRepo.findAllProductsOrderByPriceDesc();
    }
    public List<ProductResponse> findAllProductOrderByPriceAsc() {
        return productRepo.findAllProductsOrderByPriceAsc();
    }

}
