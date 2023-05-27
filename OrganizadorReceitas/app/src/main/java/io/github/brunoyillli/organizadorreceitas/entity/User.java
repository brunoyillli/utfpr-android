package io.github.brunoyillli.organizadorreceitas.entity;

public class User {
    private String name;
    private String gender;

    private String favoriteFood;

    private String favoriteCategory;

    public User() {
    }

    public User(String name, String gender, String favoriteFood, String favoriteCategory) {
        this.name = name;
        this.gender = gender;
        this.favoriteFood = favoriteFood;
        this.favoriteCategory = favoriteCategory;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    public String getFavoriteCategory() {
        return favoriteCategory;
    }

    public void setFavoriteCategory(String favoriteCategory) {
        this.favoriteCategory = favoriteCategory;
    }
}
