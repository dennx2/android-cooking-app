package com.codecrafters.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg01_home);

        // Start pg03_SurveyActivity when the app launches
//        Intent intent = new Intent(MainActivity.this, pg03_SurveyActivity.class);
//        startActivity(intent);

////        for test purpose
        Intent intent = new Intent(MainActivity.this,pg10_SearchResultActivity.class);
        startActivity(intent);

        //        for test purpose
//        Intent intent = new Intent(MainActivity.this,pg13_HistoryActivity.class);
//        startActivity(intent);
    }
}