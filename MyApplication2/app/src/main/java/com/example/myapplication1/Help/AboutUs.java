package com.example.myapplication1.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication1.Help.HelpFragment;
import com.example.myapplication1.R;

public class AboutUs extends AppCompatActivity {

    Button btn_back_us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        btn_back_us = (Button) findViewById(R.id.btn_back_us);
        btn_back_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new HelpFragment()).addToBackStack(null).commit();
            }
        });
    }
}