package com.example.lecture1batch3java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class TimelineActivity extends AppCompatActivity {

    // SharedPreferences sharedPreferenceAppInfo = getSharedPreferences("app_info", MODE_PRIVATE); // Read
    // SharedPreferences.Editor editorAppInfo =  sharedPreferenceAppInfo.edit(); // Edit (Write, Replace, Delete/Remove)

    // SharedPreferences sharedPreferenceUserInfo;
    // SharedPreferences.Editor editorUserInfo;

    private FirebaseAuth mAuth;
    private ImageView userImage;
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        // sharedPreferenceUserInfo = getSharedPreferences("user_info", MODE_PRIVATE); // Read
        // editorUserInfo = sharedPreferenceUserInfo.edit();  // Edit (Write, Replace, Delete/Remove)

        userImage = findViewById(R.id.userImage);
        userName = findViewById(R.id.userName);

        mAuth = FirebaseAuth.getInstance();

        Log.i("TAG", "onCreate: photo-url = " + mAuth.getCurrentUser().getPhotoUrl());
        Picasso
                .get()
                .load(mAuth.getCurrentUser().getPhotoUrl())
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .centerInside()
                .into(userImage);

        userName.setText(mAuth.getCurrentUser().getDisplayName());

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
