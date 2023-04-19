package com.codecrafters.cookingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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


    }


}
