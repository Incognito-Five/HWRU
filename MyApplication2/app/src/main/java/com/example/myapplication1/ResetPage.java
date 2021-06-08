package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetPage extends AppCompatActivity {

    TextView username;
    EditText password, repassword;
    Button confirm;
    DatabaseHelper DBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_page);

        username = (TextView) findViewById(R.id.txt_username_rst);
        password = (EditText) findViewById(R.id.txt_password_reset);
        repassword = (EditText) findViewById(R.id.txt_password_retype);
        confirm = (Button) findViewById(R.id.button_confirmpw);
        DBase = new DatabaseHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pw = password.getText().toString();
                String repw = repassword.getText().toString();
                if (pw.equals(repw)){
                    if(pw.equals("")||repw.equals("")){
                        Toast.makeText(ResetPage.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean checkUserPassUpdate = DBase.updatepass(user, pw);
                        if (checkUserPassUpdate == true){
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(ResetPage.this, "Password Updated Successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ResetPage.this, "Password Update Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {
                    Toast.makeText(ResetPage.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}