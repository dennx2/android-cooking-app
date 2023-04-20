package com.codecrafters.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class activity_pg04_landing extends AppCompatActivity {

    //declare
    String url, name, prepTime, cookTime, totalTime, nbServings, category, country;
    List<String> ingredients, steps;
    Float rating;

    Recipe recipeObject;
    EditText searchKeyword;
    String searchKeywordValue;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg04_landing);

        submitButton = findViewById(R.id.btn1);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call the search() method when the submit button is clicked
                search(view);
            }
        });

    }

    public void openRecipe(View view) {

        DatabaseReference recipesRef = FirebaseDatabase.getInstance().getReference("recipes");

        recipesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get the JSONArray of recipes from the DataSnapshot
                ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) dataSnapshot.getValue();
                JSONArray recipes = new JSONArray(list);

                try {
                    // Get the first recipe object from the JSONArray
                    JSONObject firstRecipe = recipes.getJSONObject(0);

                    //get each parameter for recipeObject
                    url = firstRecipe.getString("url");
                    name = firstRecipe.getString("name");

                    JSONArray ingredientsArray = firstRecipe.getJSONArray("ingredients");
                    ingredients = new ArrayList<>();
                    for (int i = 0; i < ingredientsArray.length(); i++) {
                        ingredients.add(ingredientsArray.getString(i));
                    }

                    JSONArray stepsArray = firstRecipe.getJSONArray("steps");
                    steps = new ArrayList<>();
                    for (int i = 0; i < stepsArray.length(); i++) {
                        steps.add(stepsArray.getString(i));
                    }

                    rating = (float) 4.5;
                    prepTime = firstRecipe.getString("prep_time");
                    cookTime = firstRecipe.getString("cook_time");
                    totalTime = firstRecipe.getString("total_time");
                    nbServings = firstRecipe.getString("nb_servings");
                    category = "Chicken";
                    country = "India";

                    //create the recipeObject
                    recipeObject = new Recipe(0,false,url, name, ingredients, steps, rating, prepTime, cookTime, totalTime, nbServings, category, country);

                    //create intent
                    Intent intent = new Intent(activity_pg04_landing.this, pg15_RecipeDetailActivity.class);

                    // add the recipe object to the intent
                    intent.putExtra("recipe", recipeObject);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(activity_pg04_landing.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void search(View view) {
        searchKeyword = findViewById(R.id.et1);
        searchKeywordValue = searchKeyword.getText().toString();

        Intent intent = new Intent(this, pg10_SearchResultActivity.class);
        intent.putExtra("searchKeywordFromLanding", searchKeywordValue);
        startActivity(intent);
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