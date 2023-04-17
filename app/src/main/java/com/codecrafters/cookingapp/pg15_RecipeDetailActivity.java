package com.codecrafters.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class pg15_RecipeDetailActivity extends AppCompatActivity {

    // TODO fix name of the intent object
    Recipe recipe = (Recipe) getIntent().getSerializableExtra("myObject");

    TextView tvTitle;
    TextView tvIngredients;
    TextView tvSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg15_recipe_detail);

        String url = recipe.getUrl();
        String name = recipe.getName();
        List<String> ingredients = recipe.getIngredients();
        List<String> steps = recipe.getSteps();
        Float rating = recipe.getRating();
        String prepTime = recipe.getPrepTime();
        String cookTime = recipe.getCookTime();
        String totalTime = recipe.getTotalTime();
        String nbServings = recipe.getNbServings();

        tvTitle = findViewById(R.id.tv_rname);
        tvIngredients = findViewById(R.id.tv_detail_ingredient);
        tvSteps = findViewById(R.id.tv_detail_direction);

        // Set the recipe content
        tvTitle.setText(name);

        String ingredientsText = "";
        for (String ingredient : ingredients) {
            ingredientsText += ingredient + "\n";
        }
        tvIngredients.setText(ingredientsText);

        String stepsText = "";
        for (String step : steps) {
            stepsText += step + "\n";
        }
        tvIngredients.setText(stepsText);


    }






}