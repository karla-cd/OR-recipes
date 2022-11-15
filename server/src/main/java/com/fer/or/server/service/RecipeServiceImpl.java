package com.fer.or.server.service;

import com.fer.or.server.model.Recipe;
import com.fer.or.server.model.SearchParams;
import com.fer.or.server.repo.RecipeRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public List<Recipe> findBySearchValue(SearchParams searchParams) throws IOException {

        System.out.println(searchParams.getValue());
        System.out.println(searchParams.getAttribute());

        List<Recipe> recipes = findAll();

        List<Recipe> filteredRecipes = new ArrayList<>();

        switch (searchParams.getAttribute()) {
            case "Sva polja (wild card)":
                // name
                for (Recipe recipe : recipes) {
                    if (recipe.getName().contains(searchParams.getValue())) {
                        filteredRecipes.add(recipe);
                    }
                }
                // ease of preparation
                if (filteredRecipes.isEmpty()) {
                    for (Recipe recipe : recipes) {
                        if (recipe.getEaseOfPreparation().contains(searchParams.getValue())) {
                            filteredRecipes.add(recipe);
                        }
                    }
                }
                // type
                if (filteredRecipes.isEmpty()) {
                    for (Recipe recipe : recipes) {
                        if (recipe.getType().contains(searchParams.getValue())) {
                            filteredRecipes.add(recipe);
                        }
                    }
                }
                // web source
                if (filteredRecipes.isEmpty()) {
                    for (Recipe recipe : recipes) {
                        if (recipe.getWebSource().contains(searchParams.getValue())) {
                            filteredRecipes.add(recipe);
                        }
                    }
                }
                break;
            case "Name":
                for (Recipe recipe : recipes) {
                    if (recipe.getName().contains(searchParams.getValue())) {
                        filteredRecipes.add(recipe);
                    }
                }
                break;
            case "Ease of preparation":
                for (Recipe recipe : recipes) {
                    if (recipe.getEaseOfPreparation().contains(searchParams.getValue())) {
                        filteredRecipes.add(recipe);
                    }
                }
                break;
            case "Type":
                for (Recipe recipe : recipes) {
                    if (recipe.getType().contains(searchParams.getValue())) {
                        filteredRecipes.add(recipe);
                    }
                }
                break;
            case "Web source":
                for (Recipe recipe : recipes) {
                    if (recipe.getWebSource().contains(searchParams.getValue())) {
                        filteredRecipes.add(recipe);
                    }
                }
                break;
        }

        return filteredRecipes;
    }

}
