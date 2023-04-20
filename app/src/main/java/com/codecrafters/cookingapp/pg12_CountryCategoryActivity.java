package com.codecrafters.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class pg12_CountryCategoryActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg12_country_category);

        context = getApplicationContext();
    }


    public void countryNameClicked(View view) {

        int viewID = view.getId();
        TextView textView = findViewById(viewID);
        String countryName = textView.getText().toString();

        // TODO Perform search. For now I'm using existing JSON file, so the String from TExtView will not be used.
        ArrayList<Recipe> recipeList = Search.countrySearch(context);

        // Build an explicit Intent
        // TODO change destination class to Search Result Activity class
        Intent i = new Intent(this, pg10_SearchResultActivity.class);

        // Send data along with the Intent to the destination
        i.putExtra("searchKeywordFromLanding", countryName);

        // Start the activity with the explicit intent
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }

        // TODO to get result
//        MyObject myObject = (MyObject) getIntent().getSerializableExtra("myObject");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), activity_pg08_menu.class);
        startActivity(intent);
        return true;
    }


}