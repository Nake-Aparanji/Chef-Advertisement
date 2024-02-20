package com.chefadvertisement.ChefAdvertisement.dao;

import com.chefadvertisement.ChefAdvertisement.entity.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // annotation not required but best practice as it helps in excepting translation from JPA to Spring
public interface ChefRepository extends JpaRepository<Chef, Integer> {

    // Custom methods

    // Method to find random chefs
    @Query(value = "SELECT * FROM chef ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Chef> findRandomChefs();
}
