package com.chefadvertisement.ChefAdvertisement.service;

import com.chefadvertisement.ChefAdvertisement.dao.ChefRepository;
import com.chefadvertisement.ChefAdvertisement.entity.Chef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefServiceImpl implements ChefService {

    // Inject ChefRepository
    private final ChefRepository chefRepository;
    @Autowired  // Constructor Injection
    public ChefServiceImpl(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Chef> findAll() {
        return chefRepository.findAll();    // Implement the method to find all chefs from DB
    }

    @Override
    public void save(Chef chef) {
        chefRepository.save(chef);
    }

    @Override
    public Chef findChefById(int id) {
        // Use findById method from the JpaRepository, which returns an Optional
        Optional<Chef> chefOptional = chefRepository.findById(id); // Cast int id to long if necessary

        // Check if the Optional contains a value, and return it if so
        if (chefOptional.isPresent()) {
            return chefOptional.get();
        } else {
            // Handling the case where the chef is not found, throw an exception
            throw new RuntimeException("Chef not found for id: " + id);
        }
    }

    @Override
    public void deleteChefById(int id) {
        chefRepository.deleteById(id);
    }

    @Override
    public List<Chef> findRandomChefs() {
        return chefRepository.findRandomChefs();
    }
}

