package com.example.lecture1batch3java;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lecture1batch3java.models.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class CreateAccountActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMG = 1000;
    private EditText text_name, text_email, text_password;
    private Button btn_sign_up;
    private ImageView userImage;
    private FirebaseAuth mAuth;
    private  Uri imageUri;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageRef;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    // Create a Cloud Storage reference from the app


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        text_name = findViewById(R.id.text_name);
        text_email = findViewById(R.id.text_email);
        text_password = findViewById(R.id.text_password);
        userImage = findViewById(R.id.userImage);
        btn_sign_up = findViewById(R.id.btn_sign_up);

        userImage.setImageResource(R.drawable.ic_launcher_background);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
            }
        });

        Picasso
                .get()
                .load("https://media.istockphoto.com/vectors/default-profile-picture-avatar-photo-placeholder-vector-illustration-vector-id1214428300?k=6&m=1214428300&s=170667a&w=0&h=hMQs-822xLWFz66z3Xfd8vPog333rNFHU6Q_kc9Sues=")
                .placeholder(R.drawable.ic_launcher_background)
                .fit()
                .centerInside()
                .into(userImage);



        mAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        storageRef = firebaseStorage.getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

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
                } else {
                    mAuth.createUserWithEmailAndPassword(text_email.getText().toString(), text_password.getText().toString())
                            .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("TAG", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {

                                            UserProfileChangeRequest.Builder userBuilder = new UserProfileChangeRequest.Builder();
                                            userBuilder.setDisplayName(text_name.getText().toString());
                                            // userBuilder.setPhotoUri(imageUri);

                                            UserProfileChangeRequest userProfileChangeRequest = userBuilder.build();

                                            user.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    startActivity(new Intent(CreateAccountActivity.this, TimelineActivity.class));
                                                    finish();
                                                }
                                            });
                                            StorageReference imageReference = storageRef.child("profile_images/"+imageUri.getLastPathSegment());
                                            UploadTask uploadTask = imageReference.putFile(imageUri);
                                            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                                @Override
                                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                                    if (!task.isSuccessful()) {
                                                        throw task.getException();
                                                    }

                                                    // Continue with the task to get the download URL
                                                    return task.getResult().getStorage().getDownloadUrl();
                                                }
                                            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    Log.i("TAG", "onSuccess: photo-url = " + uri);

                                                    imageUri = uri;

                                                    UserProfileChangeRequest.Builder userBuilder = new UserProfileChangeRequest.Builder();
                                                    userBuilder.setDisplayName(text_name.getText().toString());
                                                    userBuilder.setPhotoUri(imageUri);

                                                    UserProfileChangeRequest userProfileChangeRequest = userBuilder.build();

                                                    user.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {

                                                            User userObj = new User(user.getEmail(), user.getDisplayName(), user.getPhotoUrl().toString());
                                                            databaseReference.child(user.getUid()).setValue(userObj);
                                                            startActivity(new Intent(CreateAccountActivity.this, TimelineActivity.class));
                                                            finish();
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("TAG", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                    // ...
                                }
                            });
                }
            }
        };
        btn_sign_up.setOnClickListener(btnSignUpListener);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (reqCode == RESULT_LOAD_IMG && resultCode == RESULT_OK) {
            imageUri = data.getData();
            userImage.setImageURI(imageUri);
            Toast.makeText(CreateAccountActivity.this, "You have picked Image",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(CreateAccountActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }

    }
}