package com.fer.or.server.service;

import com.fer.or.server.model.Recipe;
import com.fer.or.server.model.RecipeRequest;
import com.fer.or.server.model.SearchParams;
import com.fer.or.server.repo.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public Recipe findById(String id) {
        if (recipeRepository.findById(id).isPresent())
            return recipeRepository.findById(id).get();
        return null;
    }

    @Override
    public List<Recipe> findByWebSource(String webSource) {
        List<Recipe> allRecipes = recipeRepository.findAll();
        List<Recipe> returnRecipes = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (recipe.getWebSource().equals(webSource))
                returnRecipes.add(recipe);
        }
        return returnRecipes;
    }

    @Override
    public List<Recipe> findByEaseOfPreparation(String easeOfPreparation) {
        List<Recipe> allRecipes = recipeRepository.findAll();
        List<Recipe> returnRecipes = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (recipe.getEaseOfPreparation().equals(easeOfPreparation))
                returnRecipes.add(recipe);
        }
        return returnRecipes;
    }

    @Override
    public List<Recipe> findByNumberOfServings(int numberOfServings) {
        List<Recipe> allRecipes = recipeRepository.findAll();
        List<Recipe> returnRecipes = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            if (recipe.getServings() == numberOfServings)
                returnRecipes.add(recipe);
        }
        return returnRecipes;
    }

    @Override
    public Recipe saveRecipe(RecipeRequest recipeRequest) {
        Recipe recipe = new Recipe(
                recipeRequest.getName(),
                recipeRequest.getRating(),
                recipeRequest.getEaseOfPreparation(),
                recipeRequest.getType(),
                recipeRequest.getPreparationTime(),
                recipeRequest.getIngredients(),
                recipeRequest.getCalories(),
                recipeRequest.getDailyValue(),
                recipeRequest.getServings(),
                recipeRequest.getWebSource()
        );
        return recipeRepository.save(recipe);
    }

    @Override
    public void updateRecipe(String id, Recipe recipe) {
        if (recipeRepository.findById(id).isPresent()) {

            recipeRepository.deleteById(recipe.getId());

            System.out.println("Recipe: " + recipe.toString());

            recipeRepository.save(recipe);
        }
    }

    @Override
    public void deleteById(String id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> findBySearchValue(SearchParams searchParams) throws IOException {

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
