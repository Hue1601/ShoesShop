package org.example.shoesshopbe.Service;

import jakarta.persistence.criteria.Predicate;
import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SitebarService {
    @Autowired
    private ProductRepo productRepo;
    public List<Products> findAllProducts(String gender) {
        Specification<Products> spec = (root,query,criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(!gender.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("gender"),gender));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return productRepo.findAll();
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
