package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordPage extends AppCompatActivity {

    EditText username;
    Button reset;
    DatabaseHelper DBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_page);

        username = (EditText) findViewById(R.id.txt_username_reset);
        reset = (Button) findViewById(R.id.button_reset);
        DBase = new DatabaseHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();

                Boolean checkUser = DBase.checkUsername(user);
                if (checkUser == true){
                    Intent intent = new Intent(getApplicationContext(), ResetPage.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ForgotPasswordPage.this, "User does not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}