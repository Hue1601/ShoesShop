package org.example.shoesshopbe.Repo;

import org.example.shoesshopbe.Model.Emails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends JpaRepository<Emails, Integer> {
}
