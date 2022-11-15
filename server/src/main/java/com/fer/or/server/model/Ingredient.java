package com.fer.or.server.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Ingredient {

    @Field("Amount")
    private String amount;

    @Field("Ingredient")
    private String ingredient;

}
