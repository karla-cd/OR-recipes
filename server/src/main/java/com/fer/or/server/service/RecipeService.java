package com.fer.or.server.service;

import com.fer.or.server.model.Recipe;
import com.fer.or.server.model.SearchParams;

import java.io.IOException;
import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    List<Recipe> findBySearchValue(SearchParams searchParams) throws IOException;
}
