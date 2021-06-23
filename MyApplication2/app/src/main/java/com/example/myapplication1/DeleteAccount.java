package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteAccount extends AppCompatActivity {

    EditText username, password;
    Button delete;
    DatabaseHelper DBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account2);

        username = (EditText) findViewById(R.id.txt_username_delete);
        password = (EditText) findViewById(R.id.txt_pass_delete);
        delete = (Button) findViewById(R.id.button_delete);
        DBase = new DatabaseHelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")){
                    Toast.makeText(DeleteAccount.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpw = DBase.checkUsernamePassword(user, pass);
                    if (checkuserpw){
                        Boolean checkUser = DBase.checkUsername(user);
                        if (checkUser == true){
                            Boolean del_user = DBase.deleteData(user);
                            if (del_user == true){
                                Toast.makeText(DeleteAccount.this, "User deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(DeleteAccount.this, "User not deleted", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(DeleteAccount.this, "User does not exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(DeleteAccount.this, "User does not exists", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}