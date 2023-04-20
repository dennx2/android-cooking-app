package com.codecrafters.cookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class pg03_SurveyActivity extends AppCompatActivity {
    ImageButton countryJapan, countryIndia, countryTaiwan, countryChina, countryCanada, countryNepal, countryItaly, countryMexico, countryGreece;
    int noOfCountries = 9;
    int noOfCategories = 5;
    ArrayList<String> preferredCountries;
    ArrayList<String> preferredCategories;
    Button submitButton;
    Button skipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg03_survey);

        //submit button
        submitButton = findViewById(R.id.button_submit);
        //skip button
        skipButton = findViewById(R.id.button_skip);
        selectCountry();
        selectCategory();

        // Set a click listener for the Submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedCountries = preferredCountries.toString();
                String selectedCategories = preferredCategories.toString();
                String message = "Selected countries: " + selectedCountries + "\nSelected categories: " + selectedCategories;
                Toast.makeText(pg03_SurveyActivity.this, message, Toast.LENGTH_SHORT).show();
//            passing control to the landing page i.e. activity_pg04_landing
                moveToNextPage();
            }
        });

        // Set a click listener for the Skip button
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//            passing control to the landing page i.e. activity_pg04_landing
                moveToNextPage();
            }
        });
    }

    private void moveToNextPage() {
        //            passing control to the landing page i.e. activity_pg04_landing
        Intent intent = new Intent(pg03_SurveyActivity.this, pg04_LandingActivity.class);
        startActivity(intent);
    }

    //select country method
    private void selectCountry() {
        // Define an array of country button IDs
        String[] countryButtonIds = {"country_japan", "country_india", "country_taiwan", "country_china",
                "country_canada", "country_nepal", "country_italy", "country_mexico", "country_greece"};

        boolean[] selectedStates = new boolean[noOfCountries];
        //get colors from colors.xml
        int deselectedColor = ContextCompat.getColor(pg03_SurveyActivity.this, R.color.dgreen);
        int selectedColor = ContextCompat.getColor(pg03_SurveyActivity.this, R.color.dorange);

        //countries choice selected by the user
        preferredCountries = new ArrayList<>();

        // Define a single click listener for all country buttons
        View.OnClickListener countryButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the ID of the clicked country button as string
                String buttonIdString = getResources().getResourceEntryName(view.getId());

                // Get the index of the clicked country button in the array of country button IDs
                int buttonIndex = -1;
                for (int i = 0; i < countryButtonIds.length; i++) {
                    if (countryButtonIds[i].equals(buttonIdString)) {
                        buttonIndex = i;
                        break;
                    }
                }

                // If buttonIndex is still -1, it means the clicked button ID is not found in the array of country button IDs
                if (buttonIndex == -1) {
                    return;
                }

                // Toggle the selected state for the clicked country button
                selectedStates[buttonIndex] = !selectedStates[buttonIndex];

                // Update the UI based on the selected state
                if (selectedStates[buttonIndex]) {
                    // If selected, set background color to selected color (orange) and add country choice to preferred countries choice
                    view.setBackgroundColor(selectedColor);
                    preferredCountries.add(buttonIdString);
                } else {
                    // If deselected, set background color to deselected color (green) and remove country choice from preferred countries choice
                    view.setBackgroundColor(deselectedColor);
                    preferredCountries.remove(buttonIdString);
                }
            }
        };

        // Set the click listener for all country buttons
        for (String buttonId : countryButtonIds) {
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            ImageButton countryButton = findViewById(resId);
            countryButton.setOnClickListener(countryButtonClickListener);
        }

    }

    //select category method
    private void selectCategory() {
        // Define an array of category button IDs
        String[] categoryButtonIds = {"categories_vegan", "categories_veg", "categories_nonveg", "categories_gluten_free",
                "categories_fruit"};

        boolean[] selectedStates = new boolean[noOfCategories];
        //get colors from colors.xml
        int deselectedColor = ContextCompat.getColor(pg03_SurveyActivity.this, R.color.dgreen);
        int selectedColor = ContextCompat.getColor(pg03_SurveyActivity.this, R.color.dorange);

        //categories choice selected by the user
        preferredCategories = new ArrayList<>();

        // Define a single click listener for all category buttons
        View.OnClickListener categoryButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the ID of the clicked category button as string
                String buttonIdString = getResources().getResourceEntryName(view.getId());

                // Get the index of the clicked category button in the array of country button IDs
                int buttonIndex = -1;
                for (int i = 0; i < categoryButtonIds.length; i++) {
                    if (categoryButtonIds[i].equals(buttonIdString)) {
                        buttonIndex = i;
                        break;
                    }
                }

                // If buttonIndex is still -1, it means the clicked button ID is not found in the array of category button IDs
                if (buttonIndex == -1) {
                    return;
                }

                // Toggle the selected state for the clicked category button
                selectedStates[buttonIndex] = !selectedStates[buttonIndex];

                // Update the UI based on the selected state
                if (selectedStates[buttonIndex]) {
                    // If selected, set background color to selected color (orange) and add category choice to preferred categories choice
                    view.setBackgroundColor(selectedColor);
                    preferredCategories.add(buttonIdString);
                } else {
                    // If deselected, set background color to deselected color (green) and remove category choice from preferred categories choice
                    view.setBackgroundColor(deselectedColor);
                    preferredCategories.remove(buttonIdString);
                }
            }
        };

        // Set the click listener for all country buttons
        for (String buttonId : categoryButtonIds) {
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            ImageButton categoryButton = findViewById(resId);
            categoryButton.setOnClickListener(categoryButtonClickListener);
        }

    }

}
