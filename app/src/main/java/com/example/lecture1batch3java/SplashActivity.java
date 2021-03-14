package com.example.lecture1batch3java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Activity to Activity
                FirebaseUser currentUser = mAuth.getCurrentUser();

                Intent intent;
                if (currentUser != null) {
                    intent = new Intent(SplashActivity.this, TimelineActivity.class);
                } else {
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();

            }
        };
        handler.postDelayed(runnable, 3000);

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
