package com.fer.or.server.controllers;

import com.fer.or.server.model.Recipe;
import com.fer.or.server.model.SearchParams;
import com.fer.or.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeService.findAll();
    }

    @PostMapping(path = "/recipes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Recipe> searchRecipes(@RequestBody SearchParams searchParams) throws IOException {
        return recipeService.findBySearchValue(searchParams);
    }

}
