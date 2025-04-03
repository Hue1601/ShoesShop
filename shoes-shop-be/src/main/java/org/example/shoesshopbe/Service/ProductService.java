package org.example.shoesshopbe.Service;

import jakarta.persistence.criteria.Predicate;
import org.example.shoesshopbe.Model.ProductDetail;
import org.example.shoesshopbe.Repo.ProductDetailRepo;
import org.example.shoesshopbe.Repo.ProductRepo;
import org.example.shoesshopbe.Response.ProductDetailResponse;
import org.example.shoesshopbe.Response.ProductResponse;
import org.example.shoesshopbe.Response.SizeByColorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductDetailRepo productDetailRepo;

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

    public List<ProductDetailResponse> getProductDetailById(Integer id) {
        return productRepo.getProductDetail(id);
    }

    public List<ProductResponse> getProductRelated(Integer id) {
        List<ProductDetailResponse> detail = productRepo.getProductDetail(id);
        Integer categoryId = detail.get(0).getCategoryId();
        Pageable limit = PageRequest.of(0, 5);
        List<ProductResponse> relatedProducts = productRepo.getProductRelated(categoryId, id, limit);
        return relatedProducts;
    }

    public List<SizeByColorResponse> findSizeByColor(Integer productId, String color) {
        Specification<ProductDetail> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (productId != null) {
                predicates.add(criteriaBuilder.equal(root.get("product").get("id"), productId));
            }
            if (color != null) {
                predicates.add(criteriaBuilder.equal(root.get("color").get("colorName"), color));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<ProductDetail> productDetails = productDetailRepo.findAll(spec);
        return productDetails.stream().map(product -> new SizeByColorResponse(
                product.getStock(),
                product.getColor().getColorName(),
                product.getSize().getSizeValue(),
                product.getPrice()
        )).collect(Collectors.toList());

    }
}
