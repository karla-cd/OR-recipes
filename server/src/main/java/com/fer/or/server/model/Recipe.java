package com.fer.or.server.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document("Recipe")
public class Recipe {

    @Id
    private String id;

    @Field("Name")
    private String name;

    @Field("Rating")
    private int rating;

    @Field("Ease of preparation")
    private String easeOfPreparation;

    @Field("Type")
    private String type;

    @Field("Preparation time")
    private int preparationTime;

    @Field("Ingredients")
    private List<Ingredient> ingredients;

    @Field("Calories")
    private int calories;

    @Field("Daily value")
    private String dailyValue;

    @Field("Servings")
    private int servings;

    @Field("Web Source")
    private String webSource;

    public Recipe(
            String name,
            int rating,
            String easeOfPreparation,
            String type,
            int preparationTime,
            List<Ingredient> ingredients,
            int calories,
            String dailyValue,
            int servings,
            String webSource)
    {
        this.name = name;
        this.rating = rating;
        this.easeOfPreparation = easeOfPreparation;
        this.type = type;
        this.preparationTime = preparationTime;
        this.ingredients = ingredients;
        this.calories = calories;
        this.dailyValue = dailyValue;
        this.servings = servings;
        this.webSource = webSource;
    }
}
