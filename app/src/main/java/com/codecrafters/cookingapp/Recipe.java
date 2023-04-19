package com.codecrafters.cookingapp;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {

    private String url;
    private String name;
    private List<String> ingredients;
    private List<String> steps;
    private Float rating;
    private String prepTime;
    private String cookTime;
    private String totalTime;
    private String nbServings;
    private String category;
    private String country;

    public Recipe() {

    }

    public Recipe(String url, String name, List<String> ingredients, List<String> steps, Float rating, String prepTime, String cookTime, String totalTime, String nbServings, String category, String country) {
        this.url = url;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.rating = rating;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.totalTime = totalTime;
        this.nbServings = nbServings;
        this.category = category;
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getNbServings() {
        return nbServings;
    }

    public void setNbServings(String nbServings) {
        this.nbServings = nbServings;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
