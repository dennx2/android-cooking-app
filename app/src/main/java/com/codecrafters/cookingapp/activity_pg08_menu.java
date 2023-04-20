package com.codecrafters.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;

public class activity_pg08_menu extends AppCompatActivity {
//
//    FirebaseAuth auth;
//    FirebaseUser user;

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
        Context context = getApplicationContext();
        ArrayList<Recipe> recipeList = Search.countrySearch(context);
        Intent intent = new Intent(getApplicationContext(),pg07_FavoritesActivity.class);
        intent.putExtra("recipes", recipeList);
        startActivity(intent);
        finish();
    }

    public void goRecommend(View view) {
    }

    public void goBrowseAll(View view) {
        Intent intent = new Intent(getApplicationContext(),pg11_FoodCategoryActivity.class);
        startActivity(intent);
        finish();
    }

    public void viewHistory(View view) {
    }

    public void aboutUs(View view) {
    }

    public void logout(View view) {
//        auth = FirebaseAuth.getInstance();
//        user = auth.getCurrentUser();
//
//        FirebaseAuth.getInstance().signOut();
//
//        if(auth.getCurrentUser() == null){
//            System.out.println("i am log out");
//        }
        Intent intent = new Intent(getApplicationContext(),activity_pg06_login.class);
        startActivity(intent);
        finish();
    }


}