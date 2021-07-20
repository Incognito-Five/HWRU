package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class BMICalculator extends AppCompatActivity {

    Button calculatebmi;

    TextView currentheight;
    TextView currentweight;

    /*BMI Components*/
    SeekBar heightseekbar;
    SeekBar weightseekbar;

    /*initialize BMI Component's Values*/
    int currentheightprogress;
    int currentweightprogress;
    String intheightprogress="0";
    String intweightprogress="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        /*defining mcalculatebmi*/
        calculatebmi=findViewById(R.id.calculatebmi);
        /*assign xml id to java*/
        currentheight=findViewById(R.id.currentheight);
        currentweight=findViewById(R.id.currentweight);
        /*set id for seekbar*/
        heightseekbar=findViewById(R.id.heightseekbar);
        weightseekbar=findViewById(R.id.weightseekbar);

        //height seekbar functions
        heightseekbar.setMax(400);
        heightseekbar.setProgress(0);
        heightseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentheightprogress=progress;
                intheightprogress=String.valueOf(currentheightprogress);
                currentheight.setText(intheightprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //weight seekbar
        weightseekbar.setMax(650);
        weightseekbar.setProgress(0);
        weightseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentweightprogress=progress;
                intweightprogress=String.valueOf(currentweightprogress);
                currentweight.setText(intweightprogress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //calculate BMI button function

        calculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*calculating BMI*/
                if(intheightprogress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select A Value For Your Height First",Toast.LENGTH_SHORT).show();
                }
                else if(intweightprogress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "Select A Value For Your Weight First",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    /*changing screen from main BMI screen to calculate BMI screen*/
                    Intent intent=new Intent(BMICalculator.this,BMIResult.class);
                    /*pass data to calculate BMI*/
                    intent.putExtra("height",intheightprogress);
                    intent.putExtra("weight",intweightprogress);

                    startActivity(intent);
                }
            }
        });
    }
}