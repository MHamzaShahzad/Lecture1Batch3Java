package com.example.lecture1batch3java.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lecture1batch3java.AdapterViewHolder;
import com.example.lecture1batch3java.R;
import com.example.lecture1batch3java.RecyclerViewAdapter;
import com.example.lecture1batch3java.models.User;
import com.example.lecture1batch3java.viewHolders.UserViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> userList;

    public UserRecyclerAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_adapter_layout, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.userName.setText(user.getName());
        holder.userEmail.setText(user.getEmail());
        Picasso.get().load(user.getImageUrl()).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
