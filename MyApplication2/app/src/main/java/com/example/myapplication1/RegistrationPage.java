package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegistrationPage extends AppCompatActivity {

    EditText name, username, password, repassword;
    FloatingActionButton register;
    TextView login;
/*    Button register, login;*/
    DatabaseHelper DBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page2);

        name = (EditText) findViewById(R.id.txt_name);
        username = (EditText) findViewById(R.id.txt_username1);
        password = (EditText) findViewById(R.id.txt_pw1);
        repassword = (EditText) findViewById(R.id.txt_repw);
        register = (FloatingActionButton) findViewById(R.id.fab_signup);
        login = (TextView) findViewById(R.id.txt_signin);
/*        register = (Button) findViewById(R.id.button_reg1);
        login = (Button) findViewById(R.id.button_log1);*/
        DBase = new DatabaseHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name = name.getText().toString();
                String user = username.getText().toString();
                String pw = password.getText().toString();
                String repw = repassword.getText().toString();

                if (user.equals("")||pw.equals("")||repw.equals("")){
                    Toast.makeText(RegistrationPage.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pw.equals(repw)){
                        Boolean checkUser = DBase.checkUsername(user);
                        if (checkUser == false){
                            Boolean insertUser = DBase.insertData(user,pw);
                            if (insertUser == true){
                                Toast.makeText(RegistrationPage.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Homepage.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(RegistrationPage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegistrationPage.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegistrationPage.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}