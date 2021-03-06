package com.example.lecture1batch3java;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<AdapterViewHolder> {

    private List<String> list;
    private List<UserModel> userlist;

    public RecyclerViewAdapter(List<String> list, List<UserModel> userlist) {
        this.list = list;
        this.userlist = userlist;

        Log.i("TAG", "RecyclerViewAdapter: " + list.size());
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
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
