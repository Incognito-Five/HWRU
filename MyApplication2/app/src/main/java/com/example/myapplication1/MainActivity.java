package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    EditText username, password;
    TextView forget_pass, register;
    FloatingActionButton fab_signin;
/*    Button login, forget_pass, register;*/
    DatabaseHelper DBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_pw);
        fab_signin = (FloatingActionButton) findViewById(R.id.fab_signin);
        forget_pass = (TextView) findViewById(R.id.txt_forgotpass);
        register = (TextView) findViewById(R.id.txt_signup);

/*        login = (Button) findViewById(R.id.button_login);
        forget_pass = (Button) findViewById(R.id.button_forg);
        register = (Button) findViewById(R.id.button_reg);*/
        DBase = new DatabaseHelper(this);


        fab_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pw = password.getText().toString();

                if (user.equals("")||pw.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpw = DBase.checkUsernamePassword(user, pw);
                    if (checkuserpw){
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Homepage.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Invalid username and password", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationPage.class);
                startActivity(intent);
            }
        });

        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordPage.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final boolean isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false);
        // When user reopens the app after applying dark/light mode
        if (isDarkModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }
}