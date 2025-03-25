package org.example.shoesshopbe.Service;

import jakarta.persistence.criteria.Predicate;
import org.example.shoesshopbe.Model.Products;
import org.example.shoesshopbe.Repo.ProductRepo;
import org.example.shoesshopbe.Response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SitebarService {
    @Autowired
    private ProductRepo productRepo;

    public Page<ProductResponse> findAllProducts(List<String> discount, String gender,
                                                 List<String> brand, List<String> collection,
                                                 List<String> color, List<String> priceRanges,
                                                 String keyword,Pageable pageable
    ) {
        Specification<Products> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (discount != null && !discount.isEmpty()) {
                List<Predicate> discountPredicate = new ArrayList<>();
                for (String range : discount) {
                    String[] discountArray = range.split("-");
                    if (discountArray.length == 2) {
                        float min = Float.parseFloat(discountArray[0]);
                        float max = Float.parseFloat(discountArray[1]);
                        discountPredicate.add(criteriaBuilder.between(root.join("productDiscounts").get("discount").get("discountPercentage"), min, max));
                    } else if (discountArray.length == 1) {
                        float min = Float.parseFloat(discountArray[0]);
                        discountPredicate.add(criteriaBuilder.greaterThanOrEqualTo(root.join("productDiscounts").get("discount").get("discountPercentage"), min));
                    }
                }
                if (!discountPredicate.isEmpty()) {
                    predicates.add(criteriaBuilder.or(discountPredicate.toArray(new Predicate[0])));
                }
            }
            if (gender != null && !gender.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }
            if (brand != null && !brand.isEmpty()) {
                predicates.add(root.join("brand").get("id").in(brand));
            }
            if (collection != null && !collection.isEmpty()) {
                predicates.add(root.join("productCollection").get("collection").get("id").in(collection));
            }
            if (color != null && !color.isEmpty()) {
                predicates.add(root.join("productColor").get("color").get("id").in(color));
            }
            if (priceRanges != null && !priceRanges.isEmpty()) {
                List<Predicate> pricePredicates = new ArrayList<>();
                for (String range : priceRanges) {
                    String[] values = range.split("-");
                    if (values.length == 2) {
                        int minPrice = Integer.parseInt(values[0]);
                        int maxPrice = Integer.parseInt(values[1]);
                        pricePredicates.add(criteriaBuilder.between(root.get("price"), minPrice, maxPrice));
                    } else if (values.length == 1) {

                        int minPrice = Integer.parseInt(values[0]);
                        pricePredicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));

                    }
                }
                if (!pricePredicates.isEmpty()) {
                    predicates.add(criteriaBuilder.or(pricePredicates.toArray(new Predicate[0])));
                }
            }
            if (keyword != null) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + keyword + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<Products> productList = productRepo.findAll(spec,pageable );

        return productList.map(product -> new ProductResponse(
                product.getId(),
                product.getProductName(),
                product.getBrand().getBrandName(),
                product.getPrice(),
                product.getProductImages().get(0).getImageUrl()
        ));
    }
}
