package com.example.cookio.domain.entitites;

import androidx.annotation.Nullable;

public class FullReceiptEntity {

    @Nullable
    private final String id;
    @Nullable
    private final String name;
    @Nullable
    private final String category;
    @Nullable
    private final String country;
    @Nullable
    private final String instructions;

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getCategory() {
        return category;
    }

    @Nullable
    public String getCountry() {
        return country;
    }

    @Nullable
    public String getInstructions() {
        return instructions;
    }

    @Nullable
    public String getImage() {
        return image;
    }

    @Nullable
    public String[] getTags() {
        return tags;
    }

    @Nullable
    public String[] getIngredients() {
        return ingredients;
    }

    @Nullable
    public String[] getMeasures() {
        return measures;
    }

    @Nullable
    private final String image;

    @Nullable
    private final String[] tags;

    @Nullable
    private final String[] ingredients;

    @Nullable
    private final String[] measures;

    public FullReceiptEntity(
            @Nullable String id,
            @Nullable String name,
            @Nullable String category,
            @Nullable String country,
            @Nullable String instructions,
            @Nullable String image,
            @Nullable String[] tags,
            @Nullable String[] ingredients,
            @Nullable String[] measures) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.country = country;
        this.instructions = instructions;
        this.image = image;
        this.tags = tags;
        this.ingredients = ingredients;
        this.measures = measures;
    }
}
