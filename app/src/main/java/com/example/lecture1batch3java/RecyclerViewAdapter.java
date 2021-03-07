package com.example.lecture1batch3java;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<AdapterViewHolder> {

    private List<UserModel> userlist;

    public RecyclerViewAdapter(List<UserModel> userlist) {
        this.userlist = userlist;
    }


    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_adpter_layout, parent, false);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        UserModel userModel = userlist.get(position);

        holder.nameText.setText(userModel.getName());
        holder.ageText.setText(userModel.getAge());
        holder.emailText.setText(userModel.getEmail());
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

}
