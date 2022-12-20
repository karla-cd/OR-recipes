package com.fer.or.server.service;

import com.fer.or.server.model.Recipe;
import com.fer.or.server.model.RecipeRequest;
import com.fer.or.server.model.SearchParams;

import java.io.IOException;
import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    Recipe findById(String id);

    List<Recipe> findByWebSource(String webSource);

    List<Recipe> findByEaseOfPreparation(String easeOfPreparation);

    List<Recipe> findByNumberOfServings(int numberOfServings);

    Recipe saveRecipe(RecipeRequest recipeRequest);

    void updateRecipe(String id, Recipe recipe);

    void deleteById(String id);

    List<Recipe> findBySearchValue(SearchParams searchParams) throws IOException;
}
