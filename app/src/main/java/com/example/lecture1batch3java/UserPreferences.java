package com.example.lecture1batch3java;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class UserPreferences {

    SharedPreferences sharedPreferenceUserInfo;
    SharedPreferences.Editor editorUserInfo;

    public UserPreferences(Context context) {
        sharedPreferenceUserInfo = context.getSharedPreferences("user_info", MODE_PRIVATE); // Read
        editorUserInfo = sharedPreferenceUserInfo.edit();  // Edit (Write, Replace, Delete/Remove)
    }

    // Write User Info
    public void setIsLoggedIn(boolean isLoggedIn) {
        editorUserInfo.putBoolean("isLoggedIn", isLoggedIn).apply();
    }

    public void setEmail(String email) {
        editorUserInfo.putString("login_email", email).apply();
    }

    // GET User Info
    public boolean getAlreadyLoggedIn() {
        return sharedPreferenceUserInfo.getBoolean("isLoggedIn", false);
    }

    public String getEmail() {
        return sharedPreferenceUserInfo.getString("login_email", "");
    }

    // Clear All
    public void clearAllUserPreferences() {
        editorUserInfo.clear().apply();
    }

}
