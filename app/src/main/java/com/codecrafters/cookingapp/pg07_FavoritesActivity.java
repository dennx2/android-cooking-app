package com.codecrafters.cookingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class pg07_FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg07_favourites);
        // Call the search functions to get recipe data
        Context context = getApplicationContext();;
        ArrayList<Recipe> recipes = Search.countrySearch(context);
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getFav()) {
                filteredRecipes.add(recipe);
            }
        }
        displayAllFavRecipes(filteredRecipes);
    }

    public void displayAllFavRecipes(ArrayList<Recipe> recipes) {

        // Loop through the fav recipes ArrayList and update the ImageButton objects in the XML layout file
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);

            // finding the layout elements ids
            int imageButtonId = getResources().getIdentifier("item" + recipe.getId().toString() +"_image", "id", getPackageName());
            int titleTextViewId = getResources().getIdentifier("item" + recipe.getId().toString() + "_title_tv", "id", getPackageName());
            int viewRecipeButtonId = getResources().getIdentifier("item" + recipe.getId().toString() + "_button", "id", getPackageName());
            int descTextViewId = getResources().getIdentifier("item" + recipe.getId().toString() + "_desc_tv", "id", getPackageName());

            // find the elements
            ImageButton imageButton = findViewById(imageButtonId);
            TextView titleTextView = findViewById(titleTextViewId);
            Button viewRecipeButton = findViewById(viewRecipeButtonId);
            TextView descTextView = findViewById(descTextViewId);

            // Set the recipe name as the content description of the ImageButton for accessibility
            // Set title as recipe name and description as step 1 of recipe
            imageButton.setContentDescription("image_of_" + recipe.getName());
            titleTextView.setText(recipe.getName());
            descTextView.setText(recipe.getSteps().get(0));

            // Set an OnClickListener for the View Recipe button to handle clicks
            viewRecipeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //pass the clicked recipe object to the detail page via intent
                    Intent intent = new Intent(pg07_FavoritesActivity.this, pg15_RecipeDetailActivity.class);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);
                }
            });
        }
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
