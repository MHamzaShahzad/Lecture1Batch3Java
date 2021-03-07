package com.example.lecture1batch3java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lecture1batch3java.fragments.FragmentA;
import com.example.lecture1batch3java.fragments.FragmentB;
import com.example.lecture1batch3java.fragments.FragmentC;

public class FragmentContainerActivity extends AppCompatActivity {

    Button fragmentA, fragmentB, fragmentC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        fragmentA = findViewById(R.id.fragmentA);
        fragmentB = findViewById(R.id.fragmentB);
        fragmentC = findViewById(R.id.fragmentC);

        fragmentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentA fragmentA = FragmentA.newInstance("", "");
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragmentA);
                transaction.commit();
            }
        });

        fragmentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentB fragmentB = FragmentB.newInstance("", "");
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragmentB);
                transaction.commit();
            }
        });

        fragmentC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentC fragmentC = FragmentC.newInstance("", "");
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, fragmentC);
                transaction.commit();
            }
        });

    }
}