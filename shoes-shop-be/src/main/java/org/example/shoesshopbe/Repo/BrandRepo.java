package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepo extends JpaRepository<Brands,Integer> {
}
