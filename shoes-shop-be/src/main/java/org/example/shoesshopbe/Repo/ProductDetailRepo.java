package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductDetailRepo extends JpaRepository< ProductDetail,Integer>, JpaSpecificationExecutor<ProductDetail> {
}
