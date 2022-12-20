package com.fer.or.server.controllers;

import com.fer.or.server.model.Recipe;
import com.fer.or.server.model.RecipeRequest;
import com.fer.or.server.model.SearchParams;
import com.fer.or.server.service.RecipeService;
import com.google.gson.Gson;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RecipeController extends ResponseEntityExceptionHandler {

    private final String BASE_URL = "http://localhost:8080";

    @Autowired
    RecipeService recipeService;

    // a. GET krajnja tocka za dohvacanje cjelokupne kolekcije podataka

    @GetMapping("/recipes")
    public ResponseEntity<String> getRecipes() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new Gson().toJson(recipeService.findAll()));
    }

    // b. GET krajnja tocka za dohvacanje pojedinacnog resursa temeljem identifikatora

    @GetMapping("/recipes/{id}")
    public ResponseEntity<String> getRecipeById(@PathVariable("id") String recipeId) {
        Recipe recipe = recipeService.findById(recipeId);
        if (recipe != null)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new Gson().toJson(recipeService.findById(recipeId)));
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // c. tri GET krajnje tocke po vlastitom izboru

    @GetMapping("/recipes/webSource/{webSource}")
    public ResponseEntity<String> getRecipesbyWebSource(@PathVariable("webSource") String webSource) {
        List<Recipe> recipes = recipeService.findByWebSource(webSource);
        if (recipes != null)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Gson().toJson(recipeService.findByWebSource(webSource)));
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("recipes/easeOfPreparation/{easeOfPreparation}")
    public ResponseEntity<String> getRecipesByEaseOfPreparation(@PathVariable("easeOfPreparation") String easeOfPreparation) {
        List<Recipe> recipes = recipeService.findByEaseOfPreparation(easeOfPreparation);
        if (recipes != null)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Gson().toJson(recipeService.findByEaseOfPreparation(easeOfPreparation)));
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("recipes/servings/{numberOfServings}")
    public ResponseEntity<String> getRecipesByNumberOfServings(@PathVariable("numberOfServings") int numberOfServings) {
        List<Recipe> recipes = recipeService.findByNumberOfServings(numberOfServings);
        if (recipes != null)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Gson().toJson(recipeService.findByNumberOfServings(numberOfServings)));
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // d. POST krajnja tocka za ubacivanje pojedinacnog resursa u kolekciju

    @PostMapping(path = "/recipes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addRecipe(@RequestBody RecipeRequest recipeRequest) throws URISyntaxException {
        Recipe recipe = recipeService.saveRecipe(recipeRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .location(new URI(BASE_URL + "/recipes/" + recipe.getId()))
                .body(new Gson().toJson(recipe));
    }

    // e. PUT krajnja tocka

    @PutMapping(path = "/recipes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRecipe(@PathVariable("id") String recipeId, @RequestBody Recipe recipe) {
        recipeService.updateRecipe(recipeId, recipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // f. DELETE krajnja tocka

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") String recipeId) {
        recipeService.deleteById(recipeId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    // metoda potrebna za frontend (lab2)

    @PostMapping(path = "/recipes/search", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Recipe> searchRecipes(@RequestBody SearchParams searchParams) throws IOException {
        return recipeService.findBySearchValue(searchParams);
    }

    @Override
    public ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
