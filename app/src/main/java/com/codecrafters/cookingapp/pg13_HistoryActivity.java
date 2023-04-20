package com.codecrafters.cookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class pg13_HistoryActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg13_history);

        //receive the recipe id of the latest 5 visited recipes
        sharedPreferences = getSharedPreferences("history-preferences", MODE_PRIVATE);
        String storedRecipeIds = sharedPreferences.getString("recipeIDs", "");
        Toast.makeText(this, "Recipe history: " + storedRecipeIds, Toast.LENGTH_LONG).show();



        // Call the search function to get recipe data
        Context context = getApplicationContext();;
        ArrayList<Recipe> recipes = Search.countrySearch(context);
        //call a helper method to convert storedRecipeIds (comma separate 5 latest visited recipe ids) to array of recipe ids (and reverse it)
        int[] latestFiveRecipeIdsArray =  getRecipeIdsArray(storedRecipeIds);

        // call the method to iterate and load recipes from the json file
        displayAllFavRecipes(recipes,latestFiveRecipeIdsArray);

    }

    public void displayAllFavRecipes(ArrayList<Recipe> recipes, int[] latestFiveRecipeIdsArray) {

        // Loop through the fav recipes ArrayList and update the ImageButton objects in the XML layout file
        for (int i = 0; i < 5; i++) {
            int recipeId = latestFiveRecipeIdsArray[i];

            // since json recipe records loops through 0,... but recipe id starts from 1 and so on
            //so we subtract
            Recipe recipe = recipes.get(recipeId-1);


            // finding the layout elements ids
            int imageButtonId = getResources().getIdentifier("item" + (i+1) +"_image", "id", getPackageName());
            int titleTextViewId = getResources().getIdentifier("item" + (i+1) + "_title_tv", "id", getPackageName());
            int viewRecipeButtonId = getResources().getIdentifier("item" + (i+1) + "_button", "id", getPackageName());
            int descTextViewId = getResources().getIdentifier("item" + (i+1) + "_desc_tv", "id", getPackageName());

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

            int imageResId = getResources().getIdentifier(String.valueOf("sr" + recipeId), "drawable", getPackageName());
            imageButton.setImageResource(imageResId);

            // Set an OnClickListener for the View Recipe button to handle clicks
            viewRecipeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //pass the clicked recipe object to the detail page via intent
                    Intent intent = new Intent(pg13_HistoryActivity.this, pg15_RecipeDetailActivity.class);
                    intent.putExtra("recipe", recipe);
                    startActivity(intent);
                }
            });
        }
    }

    public int[] getRecipeIdsArray(String storedRecipeIds) {
        // Split the storedRecipeIds string by commas to obtain an array of recipe ids as strings
        String[] recipeIdsArrayString = storedRecipeIds.split(",");

        // Create an int array to store the recipe ids
        int[] recipeIdsArray = new int[recipeIdsArrayString.length];

        // Loop through the recipe ids array as strings and parse them to integers, storing them in reverse order
        for (int i = recipeIdsArrayString.length - 1; i >= 0; i--) {
            recipeIdsArray[recipeIdsArrayString.length - 1 - i] = Integer.parseInt(recipeIdsArrayString[i].trim());
        }

        return recipeIdsArray;
    }


}
