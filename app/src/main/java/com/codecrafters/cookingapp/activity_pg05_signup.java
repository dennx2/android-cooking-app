package com.codecrafters.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_pg05_signup extends AppCompatActivity {

    EditText uname_et;
    EditText email_et;
    EditText pwd_et;
    EditText pwd2_et;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView login_et;
    FirebaseUser currentUser;
    String uname;

    @Override
    public void onStart() {
        super.onStart();
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(),activity_pg04_landing.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg05_signup);
        mAuth = FirebaseAuth.getInstance();
        uname_et = findViewById(R.id.uname_text);
        email_et = findViewById(R.id.email_text);
        pwd_et = findViewById(R.id.password_text);
        pwd2_et = findViewById(R.id.repassword_text);
        progressBar = findViewById(R.id.progressBar);
        login_et = findViewById(R.id.sign_in);
    }

    public void register(View view) {
        progressBar.setVisibility(View.VISIBLE);
        String email, pwd, pwd2;
        uname = uname_et.getText().toString();
        email = email_et.getText().toString();
        pwd = pwd_et.getText().toString();
        pwd2 = pwd2_et.getText().toString();

        if(TextUtils.isEmpty(uname)){
            Toast.makeText(activity_pg05_signup.this, "Please enter username", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(activity_pg05_signup.this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pwd)){
            Toast.makeText(activity_pg05_signup.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pwd2) ){
            Toast.makeText(activity_pg05_signup.this, "Please repeat password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pwd)){
            Toast.makeText(activity_pg05_signup.this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pwd.equals(pwd2)) {
            Toast.makeText(activity_pg05_signup.this, "Please check the password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            String uid = mAuth.getCurrentUser().getUid();

                            // Store the user's username along with their uid in the database
                            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
                            userRef.child("username").setValue(uname);

                            Toast.makeText(activity_pg05_signup.this, "Account created",
                                    Toast.LENGTH_SHORT).show();

                            //link to login page
                            Intent intent = new Intent(getApplicationContext(),activity_pg06_login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(activity_pg05_signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void toLogin(View view) {
        Intent intent = new Intent(getApplicationContext(),activity_pg06_login.class);
        startActivity(intent);
        finish();
    }

//    public void legal(View view) {
//        Intent intent = new Intent(getApplicationContext(),pg16_AboutUsActivity.class);
//        startActivity(intent);
//        finish();
//    }
}