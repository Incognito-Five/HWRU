package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class HeaderActivity extends AppCompatActivity {

    ImageView imageProfile;
    TextView profileName;
    TextView profileUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        imageProfile = (ImageView) findViewById(R.id.imageProfile);
        profileName = (TextView) findViewById(R.id.profile_name);
        profileUsername = (TextView) findViewById(R.id.profile_username);

        Intent intent = i

        String name = profileName.getText().toString();



    }
}