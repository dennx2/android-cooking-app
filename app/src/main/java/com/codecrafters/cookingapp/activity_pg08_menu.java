package com.codecrafters.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_pg08_menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg08_menu);
    }

    public void goProfile(View view) {
        Intent intent = new Intent(getApplicationContext(),activity_pg14_user_profile.class);
        startActivity(intent);
        finish();
    }

    public void goFavorites(View view) {
        Intent intent = new Intent(getApplicationContext(),pg09_favouriets.class);
        startActivity(intent);
        finish();
    }

    public void goRecommend(View view) {
        Intent intent = new Intent(getApplicationContext(),pg09_RecommendationActivity.class);
        startActivity(intent);
        finish();
    }

    public void goBrowseAll(View view) {
        Intent intent = new Intent(getApplicationContext(),activity_pg04_landing.class);
        startActivity(intent);
        finish();
    }

    public void viewHistory(View view) {
        Intent intent = new Intent(getApplicationContext(),pg13_history.class);
        startActivity(intent);
        finish();
    }

    public void aboutUs(View view) {
        Intent intent = new Intent(getApplicationContext(),apg16_AboutUsActivity.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        FirebaseAuth.getInstance().signOut();

        if(auth.getCurrentUser() == null){
            System.out.println("i am log out");
        }
        Intent intent = new Intent(getApplicationContext(),activity_pg06_login.class);
        startActivity(intent);
        finish();
    }

    public void goBack(View view) {
        // Check if there is a previous activity on the activity stack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            // If there is, pop the activity from the stack and navigate back to it
            getSupportFragmentManager().popBackStack();
        } else {
            // If there isn't, call the default back button behavior
            super.onBackPressed();
        }
    }


}