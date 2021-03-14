package com.example.lecture1batch3java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class TimelineActivity extends AppCompatActivity {

    // SharedPreferences sharedPreferenceAppInfo = getSharedPreferences("app_info", MODE_PRIVATE); // Read
    // SharedPreferences.Editor editorAppInfo =  sharedPreferenceAppInfo.edit(); // Edit (Write, Replace, Delete/Remove)

    // SharedPreferences sharedPreferenceUserInfo;
    // SharedPreferences.Editor editorUserInfo;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // sharedPreferenceUserInfo = getSharedPreferences("user_info", MODE_PRIVATE); // Read
        // editorUserInfo = sharedPreferenceUserInfo.edit();  // Edit (Write, Replace, Delete/Remove)


        mAuth = FirebaseAuth.getInstance();

        Intent receivedIntent = getIntent();
        Log.i("TAG", "onCreate: " + "Email: " + receivedIntent.getStringExtra("login_email") + " isLoggedIn: " + receivedIntent.getBooleanExtra("isLoggedIn", true));
        // Log.i("TAG", "onCreate: SHARED_PREFERENCES " + "Email: " + sharedPreferenceUserInfo.getString("login_email", "") + " isLoggedIn: " + sharedPreferenceUserInfo.getBoolean("isLoggedIn", false));


    }

    public void logout(View view) {
        mAuth.signOut();
        startActivity(new Intent(TimelineActivity.this, LoginActivity.class));
        finish();
    }
}


/*
SplashScreen >>
        Main (Login / SignUp) >>
            Login / SignUp Screens >>
                Timeline*/
