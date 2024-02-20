package com.chefadvertisement.ChefAdvertisement.service;

import com.chefadvertisement.ChefAdvertisement.entity.Chef;

import java.util.List;

public interface ChefService {
    List<Chef> findAll();   // method to find all chefs from the DB

    void save(Chef chef);

    Chef findChefById(int id);

    void deleteChefById(int id);

    List<Chef> findRandomChefs();
}
