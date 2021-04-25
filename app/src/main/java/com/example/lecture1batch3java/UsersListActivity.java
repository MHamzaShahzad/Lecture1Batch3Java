package com.example.lecture1batch3java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lecture1batch3java.adapters.UserRecyclerAdapter;
import com.example.lecture1batch3java.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UsersListActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersDatabaseReference;
    private RecyclerView userListRecycler;
    private UserRecyclerAdapter userRecyclerAdapter;
    private List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        userListRecycler = findViewById(R.id.userListRecycler);

        // userList = new ArrayList<>();
        userRecyclerAdapter = new UserRecyclerAdapter(userList);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        userListRecycler.setLayoutManager(linearLayout);
        userListRecycler.hasFixedSize();
        userListRecycler.setAdapter(userRecyclerAdapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        usersDatabaseReference = firebaseDatabase.getReference("users");


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("TAG", "onDataChange: " + snapshot);
                userList.clear();

                for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    Log.i("TAG", "onDataChange: name: " + user.getName() + " email: " + user.getEmail() );
                    userList.add(user);
                }

                userRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        // usersDatabaseReference.addListenerForSingleValueEvent(eventListener);
        usersDatabaseReference.addValueEventListener(eventListener);

    }
}