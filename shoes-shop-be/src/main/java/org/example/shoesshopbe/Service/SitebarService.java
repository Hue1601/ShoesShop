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

    public List<Products> findAllProducts(String discount, String gender, String brand, String collection, String color, String price) {
        Specification<Products> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (discount != null && !discount.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.join("productDiscounts").get("discount").get("countPercentage"), discount));
            }
            if (gender != null && !gender.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }
            if (brand != null && !brand.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.join("brand").get("brandName"), brand));
            }
            if (collection != null && !collection.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.join("productCollection").get("collection").get("collectionName"), collection));
            }
            if (color != null && !color.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.join("productColor").get("color").get("colorName"), color));
            }
            if (price != null && !price.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("price"), price));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return productRepo.findAll(spec);
    }

}
