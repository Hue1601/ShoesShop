package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ColorRepo extends JpaRepository<Colors, Integer> {
}
