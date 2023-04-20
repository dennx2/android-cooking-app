package com.codecrafters.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class pg15_RecipeDetailActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvIngredients;
    TextView tvSteps;
    TextView tvCategory;
    TextView tvCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg15_recipe_detail);

        // TODO fix name of the intent object
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        String url = recipe.getUrl();
        String name = recipe.getName();
        List<String> ingredients = recipe.getIngredients();
        List<String> steps = recipe.getSteps();
        Float rating = recipe.getRating();
        String prepTime = recipe.getPrepTime();
        String cookTime = recipe.getCookTime();
        String totalTime = recipe.getTotalTime();
        String nbServings = recipe.getNbServings();
        String category = recipe.getCategory();
        String country = recipe.getCountry();

        tvTitle = findViewById(R.id.tv_rname);
        tvIngredients = findViewById(R.id.tv_detail_ingredient);
        tvSteps = findViewById(R.id.tv_detail_direction);
        tvCategory = findViewById(R.id.tv_categories);
        tvCountry = findViewById(R.id.tv_countries);


        // Set the recipe content
        tvTitle.setText(name);
        tvCategory.setText(category);
        tvCountry.setText(country);

        String ingredientsText = "";
        for (String ingredient : ingredients) {
            ingredientsText += ingredient + "\n";
        }
        tvIngredients.setText(ingredientsText);

        String stepsText = "";
        for (String step : steps) {
            stepsText += step + "\n";
        }
        tvSteps.setText(stepsText);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(),activity_pg08_menu.class);
        startActivity(intent);
        return true;
    }



}