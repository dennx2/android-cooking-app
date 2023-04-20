package com.codecrafters.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class activity_pg02_forgotpsw extends AppCompatActivity {

    EditText email_et;
    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg02_forgotpsw);

        email_et = findViewById(R.id.et);
        progressBar = findViewById(R.id.progressBar_reset);

    }

    public void reset(View view) {
        String email = email_et.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(activity_pg02_forgotpsw.this,"Please enter your registered email",Toast.LENGTH_SHORT).show();
        }else{
            progressBar.setVisibility(View.VISIBLE);

            auth = FirebaseAuth.getInstance();
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(activity_pg02_forgotpsw.this,"Reset link was sent to your email",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),activity_pg06_login.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(activity_pg02_forgotpsw.this,"Reset Failed",Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                }
            });

        }
    }
}