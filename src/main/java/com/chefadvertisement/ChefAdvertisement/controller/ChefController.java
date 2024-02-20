package com.chefadvertisement.ChefAdvertisement.controller;


import com.chefadvertisement.ChefAdvertisement.entity.Chef;
import com.chefadvertisement.ChefAdvertisement.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chefs")
public class ChefController {

    // Inject ChefService
    private ChefService chefService;
    @Autowired
    public ChefController(ChefService chefService) {
        this.chefService = chefService;
    }

    // Add mapping for /list
    @GetMapping("/list")
    public String listChefs(Model model) {
        /* Get the chefs from DB
        List<Chef> chefs = chefService.findAll();   */

        // Get random chefs from DB
        List<Chef> chefs = chefService.findRandomChefs();

        // Add to the Spring Model
        model.addAttribute("chefs", chefs);

        // render HTML page
        return "chefs/list-chefs";
    }

    // Add mapping to show the form when 'Add New Chef' is clicked
    @GetMapping("/showFormToAddChef")
    public String showFormToAddChef(Model model) {
        // Create a model attribute to bind form data
        Chef chef = new Chef();

        // Add to the Spring Model
        model.addAttribute("chef", chef);

        // Render HTML page
        return "chefs/add-chef";
    }

    // Add mapping to save the chef, when 'save' is clicked
    @PostMapping("/save")
    public String saveChef(@ModelAttribute("chef") Chef chef) {
        // Save the chef
        chefService.save(chef);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/chefs/list";
    }

    // Add mapping to view chef details by id
    @GetMapping("/details/{id}")
    public String showChefDetails(@PathVariable("id") int id, Model model) {
        // Find the chef by id
        Chef chef = chefService.findChefById(id);

        // Add to the Spring Model
        model.addAttribute("chef", chef);

        // Render HTML page
        return "chefs/chef-details";
    }

    // Add mapping to edit chef details
    @GetMapping("/showFormToUpdateChef")
    public String showFormToUpdateChef(@RequestParam("chefId") int id, Model model) {
        // Get the chef to be updated
        Chef chef = chefService.findChefById(id);

        // Set chef in the model to pre-populate the form
        model.addAttribute("chef", chef);

        // Render HTML page (form)
        return"chefs/update-chef";
    }

    // Add mapping to delete chef
    @GetMapping("delete")
    public String delete(@RequestParam("chefId") int id) {
        // Delete the chef
        chefService.deleteChefById(id);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/chefs/list";
    }

}

