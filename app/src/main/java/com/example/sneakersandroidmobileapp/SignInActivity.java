package com.example.sneakersandroidmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button signInButton;
    DataBaseHelper dataBaseHelper;
    UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.signInButton);
        dataBaseHelper = new DataBaseHelper(SignInActivity.this);
    }

    public void validateCredentials(View view){
        if(email.getText().length() == 0 || password.getText().length() == 0){
            Toast.makeText(this, "Email and password must be filled out.", Toast.LENGTH_LONG).show();
        }
        else
        {
            String emailText = String.valueOf(email.getText());
            String passwordText = String.valueOf(password.getText());

            userModel = dataBaseHelper.findUser(emailText, passwordText);

            if(userModel.getName() == null){
                Toast.makeText(this, "Email or password was incorrect.", Toast.LENGTH_LONG).show();
            }
            else
            {
                //if credentials match what is in the database we will create and save a session variable
                //afterwards transistion them to the main activity.
                SessionVariableManager sessionVariableManager = new SessionVariableManager(SignInActivity.this);
                sessionVariableManager.saveSession(userModel);

                //After the session is saved we move to main
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
            }

        }
    }
}