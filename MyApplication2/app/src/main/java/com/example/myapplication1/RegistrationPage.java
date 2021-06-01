package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationPage extends AppCompatActivity {
    EditText username, password, repassword;
    Button register1, login1;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        username = (EditText) findViewById(R.id.txt_username);
        password = (EditText) findViewById(R.id.txt_pw);
        repassword = (EditText) findViewById(R.id.txt_repw);
        register1 = (Button) findViewById(R.id.button_reg1);
        login1 = (Button) findViewById(R.id.button_log1);
        DB = new DatabaseHelper(this);

        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user1 = username.getText().toString();
                String pass1 = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user1.equals("")||pass1.equals("")||repass.equals(""))
                    Toast.makeText(RegistrationPage.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass1.equals(repass)){
                        Boolean checkuser = DB.checkusername(user1);
                        if (checkuser == false){
                            Boolean insert = DB.insertData(user1,pass1);
                            if (insert == true){
                                Toast.makeText(RegistrationPage.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Homepage.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegistrationPage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(RegistrationPage.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegistrationPage.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}