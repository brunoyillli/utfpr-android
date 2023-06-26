package io.github.brunoyillli.organizadorreceitas.entity;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

@Entity
public class Recipe implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    private String name;
    @NotNull
    private String ingredients;

    @NotNull
    private String instructions;

    @NotNull
    private String preparationTime;

    @NotNull
    private int servingCount;
    private transient Bitmap photo;

    @NotNull
    private List<MealTypeEnum> mealType;

    @NotNull
    private boolean active;

    @NotNull
    private String categoria;

    public Recipe(String name, String ingredients, String instructions, String preparationTime,
                  int servingCount, Bitmap photo, List<MealTypeEnum> mealType,
                  boolean active, String categoria)  {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.preparationTime = preparationTime;
        this.servingCount = servingCount;
        this.photo = photo;
        this.mealType = mealType;
        this.active = active;
        this.categoria = categoria;
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

    public List<MealTypeEnum> getMealType() {
        return mealType;
    }

    public void setMealType(List<MealTypeEnum> mealType) {
        this.mealType = mealType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
