package com.example.lecture1batch3java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = findViewById(R.id.recyclerView);

        List<UserModel> userList = new ArrayList<>();
        userList.add(new UserModel(
                "Hamza Shahzad",
                "hamzashahzad604@gmail.com",
                "23"
        ));
        userList.add(new UserModel(
                "Hamza Shahzad",
                "hamzashahzad604@gmail.com",
                "23"
        ));
        userList.add(new UserModel(
                "Hamza Shahzad",
                "hamzashahzad604@gmail.com",
                "23"
        ));

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        list.add("F");
        adapter = new RecyclerViewAdapter(list, userList);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);

    }
}