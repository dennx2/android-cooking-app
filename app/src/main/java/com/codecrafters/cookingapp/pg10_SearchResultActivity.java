package com.codecrafters.cookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class pg10_SearchResultActivity extends AppCompatActivity {
    //todo- get this from intent from Jaydenn's recommendation page
    String ing1 = "pork";
    String ing2 = "curry";
    String ing3 = "vindaloo";
    TextView searchKeywords;
    ArrayList<Recipe> filteredRecipes = new ArrayList<>();
    ImageButton imageButton;
    String storedRecipeIds;
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

//        Toast.makeText(this, "Recipes found: " + recipeNames, Toast.LENGTH_LONG).show();

        //call the method to update the UI based on the filteredrecipes
        updateSearchResultPage(filteredRecipes);

    }

    private void updateSearchResultPage(ArrayList<Recipe> filteredRecipes) {

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("history-preferences", Context.MODE_PRIVATE);

        // Loop through the filteredRecipes ArrayList and update the ImageButton objects in the XML layout file
        for (int i = 0; i < filteredRecipes.size(); i++) {
            Recipe recipe = filteredRecipes.get(i);
            String recipeName = recipe.getName(); // Get the recipe name from the recipe object
            String recipeId = recipe.getId().toString();

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
            int imageResId = getResources().getIdentifier(String.valueOf("sr" + recipe.getId()), "drawable", getPackageName());
            imageButton.setImageResource(imageResId);

            // Set an OnClickListener for the ImageButton to handle clicks
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Store the clicked recipe name in shared preferences;
                    String storedRecipeIds = sharedPreferences.getString("recipeIDs", ""); // Retrieve the stored recipe ids (if it alrdy has something in it)

                    //if shared preference is null, just add the id to it
                    //else use helper method
                        //to check if a recipe id already exists in the sharedpreference
                        //if it exists, remove it and add the id
                        // i.e. 1,2,4,5 (with input id=1) becomes 2,4,5,1
                        //if it doesn't exist in the sharedpreference, just append the id to the end
                    storedRecipeIds = storedRecipeIds.isEmpty() ? recipeId : updateRecipeIds(storedRecipeIds, recipeId);
//                    storedRecipeIds = null;

                    //use a helper method to store only the latest 5 ids in storedRecipeIds
                    storedRecipeIds = getLastFiveRecipeIds(storedRecipeIds);

                    //saving the clicked recipe id into shared preferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("recipeIDs", storedRecipeIds);
//                    editor.clear();
                    editor.apply(); // Commit the changes to shared preferences

                    //pass the clicked recipe object to the detail page via intent
                    //to do - change destination to pg15_RecipeDetailActivity.class
//                    and add the following code in its oncreatemethod()
//                    Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
                    Intent intent = new Intent(pg10_SearchResultActivity.this, pg13_HistoryActivity.class);
                    intent.putExtra("recipe", recipe);
//                    Toast.makeText(pg10_SearchResultActivity.this, "Recipe selected: " + recipeName, Toast.LENGTH_LONG).show();
                    startActivity(intent);

                }
            });
        }
    }

    //method to update recipe ids if repeated id is received as input
    private String updateRecipeIds(String storedRecipeIds, String recipeId) {
        // Split storedRecipeIds by commas to get an array of recipe IDs
        String[] recipeIdArray = storedRecipeIds.split(",");

        // Check if recipeId already exists in the recipeIdArray
        boolean recipeIdExists = false;
        for (String id : recipeIdArray) {
            if (id.equals(recipeId)) {
                recipeIdExists = true;
                break;
            }
        }

        // If recipeId already exists, remove it from the recipeIdArray
        // and add it again at the end of the array
        if (recipeIdExists) {
            List<String> recipeIdList = new ArrayList<>(Arrays.asList(recipeIdArray));
            recipeIdList.remove(recipeId);
            recipeIdList.add(recipeId);
            storedRecipeIds = TextUtils.join(",", recipeIdList);
        }else{
            //if no duplicate, then just append it at the end
            storedRecipeIds = storedRecipeIds + "," + recipeId;
        }

        return storedRecipeIds;
    }

    //method to update the storedRecipeIds to have the latest 5 ids
    //i.e storedRecipeIds = 1,2,3,4,5,6,7,8 -> 4,5,6,7,8
    private String getLastFiveRecipeIds(String storedRecipeIds) {
        int limit = 5;
        String[] recipeIdArray = storedRecipeIds.split(",");

        if (recipeIdArray.length > limit) {
            // Remove the excess elements from the beginning of the array
            recipeIdArray = Arrays.copyOfRange(recipeIdArray, recipeIdArray.length - limit, recipeIdArray.length);
        }

        // Join the recipe IDs with commas to create a new string
        String lastFiveRecipeIds = TextUtils.join(",", recipeIdArray);

        return lastFiveRecipeIds;
    }

}
