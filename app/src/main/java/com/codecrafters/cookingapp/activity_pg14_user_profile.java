package com.codecrafters.cookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class activity_pg14_user_profile extends AppCompatActivity {

    EditText uname_et, gender_et, birthday_et, country_et;
    TextView nickname_tv;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    String uid;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pg14_user_profile);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        uname_et = findViewById(R.id.username_edittext);
        gender_et = findViewById(R.id.gender_edittext);
        birthday_et = findViewById(R.id.birthday_edittext);
        country_et = findViewById(R.id.country_edittext);

        nickname_tv = findViewById(R.id.nickname_textview);

        mAuth = FirebaseAuth.getInstance();

        DatabaseReference usersRef = database.getReference("users");
        // Read from the database
        uid = mAuth.getCurrentUser().getUid();
        //System.out.println("this is uid "+uid);
        usersRef.child(uid)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String nickname = dataSnapshot.child("username").getValue(String.class);
                    String gender = dataSnapshot.child("gender").getValue(String.class);
                    String birthday = dataSnapshot.child("birthday").getValue(String.class);
                    String country = dataSnapshot.child("country").getValue(String.class);
                    //System.out.println("this is nickname "+nickname);
                    nickname_tv.setText(nickname);
                    uname_et.setText(nickname);
                    gender_et.setText(gender);
                    birthday_et.setText(birthday);
                    country_et.setText(country);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Toast.makeText(activity_pg14_user_profile.this,""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void saveUser(View view) {
        String uname = uname_et.getText().toString();
        String gender = gender_et.getText().toString();
        String birthday = birthday_et.getText().toString();
        String country = country_et.getText().toString();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username",uname);
        hashMap.put("gender",gender);
        hashMap.put("birthday",birthday);
        hashMap.put("country",country);


        myRef.child("users")
                .child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(activity_pg14_user_profile.this,"Saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity_pg14_user_profile.this,""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}