package com.example.lecture1batch3java.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lecture1batch3java.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public ImageView userImage;
    public TextView userName, userEmail;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        userImage = itemView.findViewById(R.id.userImage);
        userName = itemView.findViewById(R.id.userName);
        userEmail = itemView.findViewById(R.id.userEmail);

    }
}
