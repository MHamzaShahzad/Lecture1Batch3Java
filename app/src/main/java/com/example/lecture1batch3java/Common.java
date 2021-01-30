package com.example.lecture1batch3java;

import android.util.Patterns;

public class Common {

    public static boolean isEmailValid(CharSequence email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
