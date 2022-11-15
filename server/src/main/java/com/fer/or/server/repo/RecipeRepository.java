package com.fer.or.server.repo;

import com.fer.or.server.model.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Recipe")
public interface RecipeRepository extends MongoRepository<Recipe, Long> { }
