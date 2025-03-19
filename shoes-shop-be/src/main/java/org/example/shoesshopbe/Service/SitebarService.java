package org.example.shoesshopbe.Service;

import jakarta.persistence.criteria.Predicate;
import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Repo.ProductRepo;
import org.example.shoesshopbe.Response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SitebarService {
    @Autowired
    private ProductRepo productRepo;

    public List<ProductResponse> findAllProducts(String discount, String gender,
                                                 List<String> brand, List<String> collection,
                                                 List<String> color, String price,
                                                 String keyword
    ) {
        Specification<Products> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (discount != null && !discount.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.join("productDiscounts").get("discount").get("id"), discount));
            }
            if (gender != null && !gender.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }
            if (brand != null && !brand.isEmpty()) {
//                predicates.add(criteriaBuilder.equal(root.join("brand").get("id"), brand));
                predicates.add(root.join("brand").get("id").in(brand));
            }
            if (collection != null && !collection.isEmpty()) {
             predicates.add(root.join("productCollection").get("collection").get("id").in(collection));
            }
            if (color != null && !color.isEmpty()) {
                predicates.add(root.join("productColor").get("color").get("id").in(color));
            }
            if (price != null && !price.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("price"), price));
            }
            if (keyword != null) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + keyword + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<Products> productList = productRepo.findAll(spec);

        return productList.stream().map(product ->new ProductResponse(
            product.getId(),
                product.getProductName(),
                product.getBrand().getBrandName(),
                product.getPrice().toString(),
                product.getProductImages().get(0).getImageUrl()
        )).collect(Collectors.toList());
    }
}
