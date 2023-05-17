package io.github.brunoyillli.organizadorreceitas.entity;

import android.graphics.Bitmap;

public class Recipe {
    private String name;
    private String ingredients;
    private String instructions;
    private String preparationTime;
    private int servingCount;
    private Bitmap photo;

    public Recipe(String name, String ingredients, String instructions, String preparationTime,
                  int servingCount, Bitmap photo) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.preparationTime = preparationTime;
        this.servingCount = servingCount;
        this.photo = photo;
    }

    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public int getServingCount() {
        return servingCount;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setServingCount(int servingCount) {
        this.servingCount = servingCount;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
