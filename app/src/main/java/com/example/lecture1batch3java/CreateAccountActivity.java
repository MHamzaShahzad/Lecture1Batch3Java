package com.example.lecture1batch3java;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText text_name, text_email, text_password;
    private Button btn_sign_up;
    private ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        text_name = findViewById(R.id.text_name);
        text_email = findViewById(R.id.text_email);
        text_password = findViewById(R.id.text_password);
        userImage = findViewById(R.id.userImage);

        userImage.setImageResource(R.drawable.ic_launcher_background);

        Picasso
                .get()
                .load("https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MXx8aHVtYW58ZW58MHx8MHw%3D&ixlib=rb-1.2.1&w=1000&q=80")
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .centerInside()
                .into(userImage);

        btn_sign_up = findViewById(R.id.btn_sign_up);


        View.OnClickListener btnSignUpListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "onClick: btnSignUpListener");
                if (text_name.getText().toString().equals("")){
                   text_name.setError("Field is required");
                } else if (text_email.getText().toString().equals("")) {
                    text_email.setError("Field is required");
                } else if (text_password.getText().toString().equals("")) {
                    text_password.setError("Field is required");
                } else if (!TextUtils.isEmpty(text_email.getText()) && !Common.isEmailValid(text_email.getText())){
                    text_email.setError("Invalid Email");
                } else if (!TextUtils.isEmpty(text_password.getText()) && text_password.getText().toString().length() < 6) {
                    text_password.setError("Password must be at least six characters long");
                }
            }
        };
        btn_sign_up.setOnClickListener(btnSignUpListener);

    }


}