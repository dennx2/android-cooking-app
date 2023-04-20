package com.codecrafters.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_pg06_login extends AppCompatActivity {

    EditText email_et;
    EditText pwd_et;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    Button signup_btn;

    SharedPreferences sharedPreferences;
    boolean isFirstLogin;

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            Intent intent = new Intent(getApplicationContext(), activity_pg04_landing.class);
//            startActivity(intent);
//            finish();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg06_login);
        mAuth = FirebaseAuth.getInstance();
        email_et = findViewById(R.id.username_edittext);
        pwd_et = findViewById(R.id.password_edittext);
        progressBar = findViewById(R.id.progressBar);
        signup_btn = findViewById(R.id.signup_button);

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Check if the user is first login
        isFirstLogin = sharedPreferences.getBoolean("is_first_login", true);
        if (isFirstLogin) {
            // Set the value of isFirstLogin to false in the shared preferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("is_first_login", false);
            editor.apply();
        }

    }

    public void login(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String email, pwd;
        email = email_et.getText().toString();
        pwd = pwd_et.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(activity_pg06_login.this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(activity_pg06_login.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                            //link to survey if first login
                            if (isFirstLogin == false) {
                                Intent intent = new Intent(getApplicationContext(), activity_pg04_landing.class);
                                startActivity(intent);
                                finish();
                            }else{
                                //change to go to survey later
                                Intent intent = new Intent(getApplicationContext(), pg03_SurveyActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            Toast.makeText(activity_pg06_login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void toRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_pg05_signup.class);
        startActivity(intent);
        finish();
    }

    public void forgotPassword(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_pg02_forgotpsw.class);
        startActivity(intent);
        finish();
    }
}