package com.example.lecture1batch3java;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText text_login_email, text_password;
    Button btn_login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        text_login_email = findViewById(R.id.text_login_email);
        text_password = findViewById(R.id.text_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    private void formValidation() {
        if (TextUtils.isEmpty(text_login_email.getText())){
            text_login_email.setError("Field is required!");
        } else if (TextUtils.isEmpty(text_password.getText())){
            text_password.setError("Field is required!");
        } else if (!TextUtils.isEmpty(text_login_email.getText()) && !Common.isEmailValid(text_login_email.getText())) {
            text_login_email.setError("Invalid Email");
        } else if (!TextUtils.isEmpty(text_password.getText()) && text_password.getText().length() < 0) {
            text_password.setError("Password should be at least six characters long.");
        } else {

            Intent intent = new Intent(LoginActivity.this, TimelineActivity.class);

            intent.putExtra("login_email", text_login_email.getText().toString());
            intent.putExtra("isLoggedIn", true);

            mAuth.signInWithEmailAndPassword(text_login_email.getText().toString(), text_password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                if (user != null) {
                                    startActivity(intent);
                                    finish();
                                }


                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });



            /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setMessage("Form validation Completed");
            builder.setIcon(R.drawable.ic_launcher_background);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(LoginActivity.this, TimelineActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog =  builder.create();
            dialog.show();*/
        }
    }


    @Override
    public void onClick(View v) {
       int id =  v.getId();

       switch (id) {
           case R.id.btn_login:
               formValidation();
           break;
           default:
       }

    }


}