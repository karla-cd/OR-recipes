package com.fer.or.server.model;

import lombok.Data;

import java.util.List;

@Data
public class RecipeRequest {

    private String name;

    private int rating;

    private String easeOfPreparation;

    private String type;

    private int preparationTime;

    private List<Ingredient> ingredients;

    private int calories;

    private String dailyValue;

    private int servings;

    private String webSource;
}
