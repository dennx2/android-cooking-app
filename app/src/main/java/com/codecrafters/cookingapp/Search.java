package com.codecrafters.cookingapp;

import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Search {

    public static ArrayList<Recipe> countrySearch() {
        // Create a list to hold the Recipe objects
        ArrayList<Recipe> recipeList = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            JSONArray recipeData = (JSONArray) parser.parse(new FileReader("data_indian_pork.json"));

            for (Object obj : recipeData) {
                JSONObject recipeJson = (JSONObject) obj;
                Recipe recipe = new Recipe();

                setDataRecipeObject(recipe, recipeJson);

                recipeList.add(recipe);
            }

            System.out.println(recipeList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipeList;


    }

    public static ArrayList<Recipe> ingredientSearch() {
        //TODO add code here
        ArrayList<Recipe> recipeList = new ArrayList<>();
        return recipeList;
    }


    private static void setDataRecipeObject(Recipe recipe, JSONObject recipeJson) {
        recipe.setUrl(recipeJson.get("url").toString());
        recipe.setName(recipeJson.get("name").toString());
        recipe.setIngredients((JSONArray) recipeJson.get("ingredients"));
        recipe.setSteps((JSONArray) recipeJson.get("steps"));
        recipe.setRating(recipeJson.get("rating") == null ? null : Float.parseFloat(recipeJson.get("rating").toString()));
        recipe.setPrepTime(recipeJson.get("prep_time").toString());
        recipe.setCookTime(recipeJson.get("cook_time").toString());
        recipe.setTotalTime(recipeJson.get("total_time").toString());
        recipe.setNbServings(recipeJson.get("nb_servings").toString());
    }

}
