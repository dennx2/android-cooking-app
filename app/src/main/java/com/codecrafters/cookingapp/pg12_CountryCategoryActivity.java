package com.codecrafters.cookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pg12_CountryCategoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg12_country_category);
    }


    public void countryNameClicked(View view) {

        int viewID = view.getId();
        TextView textView = findViewById(viewID);
        String countryName = textView.getText().toString();

        // TODO Perform search. For now I'm using existing JSON file, so the String from TExtView will not be used.
        ArrayList<Recipe> recipeList = Search.countrySearch();

        // Build an explicit Intent
        // TODO change destination class to Search Result Activity class
        Intent i = new Intent(this, Search.class);

        // Send data along with the Intent to the destination
        i.putExtra("recipes", recipeList);

        // Start the activity with the explicit intent
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }

        // TODO to get result
//        MyObject myObject = (MyObject) getIntent().getSerializableExtra("myObject");



    }



}