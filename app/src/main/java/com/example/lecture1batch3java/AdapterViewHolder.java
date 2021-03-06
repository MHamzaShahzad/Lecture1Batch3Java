package com.example.lecture1batch3java;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public AdapterViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textView);
    }
}
