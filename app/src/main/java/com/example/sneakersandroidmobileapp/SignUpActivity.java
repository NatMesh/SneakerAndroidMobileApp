package com.example.sneakersandroidmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    EditText fullName;
    EditText email;
    EditText password;
    Button signInButton;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.signInButton);
        dataBaseHelper = new DataBaseHelper(SignUpActivity.this);
    }

    public void createUser(View view){
        String username = String.valueOf(fullName.getText());
        String userEmail = String.valueOf(email.getText());
        String userPassword = String.valueOf(password.getText());
        Toast.makeText(this,username + "   " + userEmail + "    " + userPassword  , Toast.LENGTH_SHORT).show();
        if(username.length() == 0 || userEmail.length() == 0 || userPassword.length() == 0)
        {
            Toast.makeText(this, "No fields can be left empty.", Toast.LENGTH_LONG).show();
        }
        else{
            boolean isUserCreated = dataBaseHelper.addUser(username, userEmail, userPassword);
            if(isUserCreated){
                Toast.makeText(this, "Your account was successfully created!", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,username + "   " + userEmail + "    " + userPassword  , Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, "Something went wrong with creating your accont, please try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}