package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.Collections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepo extends JpaRepository<Collections, Integer> {

}
