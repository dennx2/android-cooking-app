package com.codecrafters.cookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg01_home);
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {
            }
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), activity_pg06_login.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}