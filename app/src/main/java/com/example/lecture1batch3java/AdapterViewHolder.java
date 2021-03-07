package com.example.lecture1batch3java;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterViewHolder extends RecyclerView.ViewHolder {

    TextView nameText, ageText, emailText;

    public AdapterViewHolder(@NonNull View itemView) {
        super(itemView);

        nameText = itemView.findViewById(R.id.nameText);
        ageText = itemView.findViewById(R.id.ageText);
        emailText = itemView.findViewById(R.id.emailText);

    }

}
