package com.example.lecture1batch3java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    // SharedPreferences sharedPreferenceUserInfo;
    // SharedPreferences.Editor editorUserInfo;

    UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // sharedPreferenceUserInfo = getSharedPreferences("user_info", MODE_PRIVATE); // Read
        // editorUserInfo = sharedPreferenceUserInfo.edit();  // Edit (Write, Replace, Delete/Remove)

        userPreferences = new UserPreferences(this);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Activity to Activity
                // boolean isAlreadyLoggedIn = sharedPreferenceUserInfo.getBoolean("isLoggedIn", false);

                boolean isAlreadyLoggedIn = userPreferences.getAlreadyLoggedIn();

                Intent intent;
                if (isAlreadyLoggedIn) {
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

}
