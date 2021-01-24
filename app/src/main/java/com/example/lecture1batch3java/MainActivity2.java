package com.example.lecture1batch3java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void moveToLoginActivity(View view) {
        Intent intent = new Intent(MainActivity2.this, LoginActivity.class);
        startActivity(intent);
    }

    public void moveToCreateAccountActivity(View view) {
        Intent intent = new Intent(MainActivity2.this, CreateAccountActivity.class);
        startActivity(intent);
    }

}