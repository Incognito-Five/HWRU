package com.example.myapplication1.Help;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication1.Help.HelpFragment;
import com.example.myapplication1.R;

public class AboutApp extends AppCompatActivity {

    Button btn_back_app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        btn_back_app = (Button) findViewById(R.id.btn_back_app);
        btn_back_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new HelpFragment()).addToBackStack(null).commit();
            }
        });
    }
}