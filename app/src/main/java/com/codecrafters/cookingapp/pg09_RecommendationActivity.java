package com.codecrafters.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class pg09_RecommendationActivity extends AppCompatActivity {

    Context context;

    EditText ingredient1;
    EditText ingredient2;
    EditText ingredient3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg09_recommendation);

        context = getApplicationContext();
    }

    public void recommendMeClicked(View view) {
        ingredient1 = findViewById(R.id.tv1_2);
        ingredient2 = findViewById(R.id.tv2_2);
        ingredient3 = findViewById(R.id.tv3_2);

        String ing1 = ingredient1.getText().toString();
        String ing2 = ingredient2.getText().toString();
        String ing3 = ingredient3.getText().toString();

        // TODO Perform search. For now I'm using existing JSON file, so the String from TExtView will not be used.
        ArrayList<Recipe> recipeList = Search.ingredientSearch(context);

        // Build an explicit Intent
        // TODO change destination class to Search Result Activity class
        Intent i = new Intent(this, Search.class);

        // Send data along with the Intent to the destination
        i.putExtra("recipes", recipeList);
        i.putExtra("ingredient1", ing1);
        i.putExtra("ingredient2", ing2);
        i.putExtra("ingredient3", ing3);

        // Start the activity with the explicit intent
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }

        // TODO to get result
//        MyObject myObject = (MyObject) getIntent().getSerializableExtra("myObject");

    }





}
