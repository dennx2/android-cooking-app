package com.codecrafters.cookingapp;

import android.content.Context;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class Search {

    public static ArrayList<Recipe> countrySearch(Context context) {
        // Create a list to hold the Recipe objects
        ArrayList<Recipe> recipeList = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            InputStream inputStream  = context.getResources().openRawResource(R.raw.data_indian_pork);
            String jsonString = new Scanner(inputStream).useDelimiter("\\A").next();
            JSONArray recipeData = (JSONArray) parser.parse(jsonString);

            for (Object obj : recipeData) {
                JSONObject recipeJson = (JSONObject) obj;
                Recipe recipe = new Recipe();

                setDataRecipeObject(recipe, recipeJson);

                recipeList.add(recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recipeList;
    }

    public static ArrayList<Recipe> ingredientSearch(Context context) {
        // Create a list to hold the Recipe objects
        ArrayList<Recipe> recipeList = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            InputStream inputStream  = context.getResources().openRawResource(R.raw.data_indian_pork);
            String jsonString = new Scanner(inputStream).useDelimiter("\\A").next();
            JSONArray recipeData = (JSONArray) parser.parse(jsonString);

            for (Object obj : recipeData) {
                JSONObject recipeJson = (JSONObject) obj;
                Recipe recipe = new Recipe();

                setDataRecipeObject(recipe, recipeJson);

                recipeList.add(recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        recipe.setCategory(recipeJson.get("category").toString());
        recipe.setCountry(recipeJson.get("country").toString());
    }

}
