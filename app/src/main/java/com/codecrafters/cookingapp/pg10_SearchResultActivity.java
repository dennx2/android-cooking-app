package com.codecrafters.cookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class pg10_SearchResultActivity extends AppCompatActivity {
    //todo- get this from intent from Jaydenn's recommendation page
    String ing1 = "pork";
    String ing2 = "curry";
    String ing3 = "vindaloo";
    TextView searchKeywords;
    ArrayList<Recipe> filteredRecipes = new ArrayList<>();
    ImageButton imageButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg10_search_result);

        searchKeywords = findViewById(R.id.s1_search_keywords);
        searchKeywords.setText("'" + ing1 + "', '" + ing2 + "', '" + ing3 + "'");

        // Call the search functions to get recipe data
        ArrayList<Recipe> recipes = Search.countrySearch(this);
//        int recipeCount = recipes.size();
        StringBuilder recipeNames = new StringBuilder();

        //loop through all the recipes and check if the search keywords are contained in each recipe names
        // if yes - store those recipe objects in an arraylist to display
        for (Recipe recipe : recipes) {
            String recipeName = recipe.getName().toLowerCase();
            if (recipeName.contains(ing1.toLowerCase()) || recipeName.contains(ing2.toLowerCase())
                    || recipeName.contains(ing3.toLowerCase())) {
                filteredRecipes.add(recipe);
                recipeNames.append(recipeName + ", ");
            }
        }

        Toast.makeText(this, "Recipes found: " + recipeNames, Toast.LENGTH_LONG).show();

        //call the method to update the UI based on the filteredrecipes
        updateSearchResultPage(filteredRecipes);

    }

    private void updateSearchResultPage(ArrayList<Recipe> filteredRecipes) {

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("historypreferences", Context.MODE_PRIVATE);

        // Loop through the filteredRecipes ArrayList and update the ImageButton objects in the XML layout file
        for (int i = 0; i < filteredRecipes.size(); i++) {
            Recipe recipe = filteredRecipes.get(i);
            String recipeName = recipe.getName(); // Get the recipe name from the recipe object

            // Assuming you have an ImageButton object with id "imageButtonX" where X is the index of the image button in the layout file
            int imageButtonId = getResources().getIdentifier("imageButton" + i, "id", getPackageName());

            // Update the ImageButton object with the recipe name as its id
            ImageButton imageButton = findViewById(imageButtonId);

            String newButtonId = "imageButton_" + recipeName;
            int newButtonIdResId = getResources().getIdentifier(newButtonId, "id", getPackageName());
            imageButton.setId(newButtonIdResId); // Set the new id for the ImageButton to avoid conflicts

            // Set the recipe name as the content description of the ImageButton for accessibility
            imageButton.setContentDescription("image_of_" + recipeName);

            // Update the image source based on the loop index i
            int imageResId = getResources().getIdentifier(String.valueOf("sr" + i), "drawable", getPackageName());
            imageButton.setImageResource(imageResId);

            // Set an OnClickListener for the ImageButton to handle clicks
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //pass the clicked recipe object to the detail page via intent
                    //to do - change destination to pg15_RecipeDetailActivity.class
                    Intent intent = new Intent(pg10_SearchResultActivity.this, pg03_SurveyActivity.class);
                    intent.putExtra("recipe", recipe);
                    Toast.makeText(pg10_SearchResultActivity.this, "Recipe selected: " + recipeName, Toast.LENGTH_LONG).show();
                    startActivity(intent);

                    // Store the clicked recipe name in shared preferences;
                    String storedRecipeNames = sharedPreferences.getString("recipeNames", ""); // Retrieve the stored recipe names

                    // Append the clicked recipe name to the stored recipe names with comma separation
                    storedRecipeNames = storedRecipeNames.isEmpty() ? recipeName : storedRecipeNames + "," + recipeName;

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("recipeNames", storedRecipeNames);
                    editor.apply(); // Commit the changes to shared preferences
                }
            });
        }
    }


}
