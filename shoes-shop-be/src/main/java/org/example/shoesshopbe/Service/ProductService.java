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


//    public List<Products> searchProducts(String gender, String brand, String color,
//                                         Double minPrice, Double maxPrice,
//                                         Double discountPercentage, String collection) {
//        Specification<Products> spec = (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (gender != null && !gender.isEmpty()) {
//                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
//            }
//            if (brand != null && !brand.isEmpty()) {
//                predicates.add(criteriaBuilder.equal(root.get("brand").get("brandName"), brand));
//            }
//            if (color != null && !color.isEmpty()) {
//                predicates.add(criteriaBuilder.equal(root.join("productColors").get("color").get("colorName"), color));
//            }
//            if (minPrice != null) {
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
//            }
//            if (maxPrice != null) {
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
//            }
//            if (discountPercentage != null) {
//                predicates.add(criteriaBuilder.equal(root.join("productDiscounts").get("discount").get("discountPercentage"), discountPercentage));
//            }
//            if (collection != null && !collection.isEmpty()) {
//                predicates.add(criteriaBuilder.equal(root.join("productCollections").get("collection").get("collectionName"), collection));
//            }
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//
//        return productRepo.findAll(spec);
//    }
}
