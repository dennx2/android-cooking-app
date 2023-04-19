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

        sharedPreferences = getSharedPreferences("historypreferences", MODE_PRIVATE);
        String storedRecipeNames = sharedPreferences.getString("recipeNames", "");
        Toast.makeText(this, "Recipe history: " + storedRecipeNames, Toast.LENGTH_LONG).show();
    }


}
