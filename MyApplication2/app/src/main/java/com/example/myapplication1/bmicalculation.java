package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class bmicalculation extends AppCompatActivity {

    /*make sure that buttons are working*/
    android.widget.Button mrecalculatebmi;
    //savebutton
    android.widget.Button msavebmi;
    /*to get BMI result*/
    TextView mbmiresult,mbmicategory;
    Intent intent;
    String mbmi;
    float intbmi;

    /*store height, weight of user*/
    String height;
    String weight;
    float intheight,intweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculation);

        /*getSupportActionBar().hide();*/
        /*get data to calculate BMI*/
        intent=getIntent();

        mbmiresult=findViewById(R.id.bmiresult);
        mbmicategory=findViewById(R.id.bmicategory);

        height=intent.getStringExtra("height");
        weight=intent.getStringExtra("weight");

        /*convert height to float*/
        intheight=Float.parseFloat(height);
        intweight=Float.parseFloat(weight);

        /*convert height to meter*/
        intheight=intheight/100;

        /*store BMI*/
        intbmi=intweight/(intheight*intheight);

        /* pass BMI result to screen string*/
        mbmi=Float.toString(intbmi);

        if(intbmi<18.5)
        {
            mbmicategory.setText("Underweight");
        }
        else if(intbmi<25 && intbmi>18.4)
        {
            mbmicategory.setText("Normal Range");
        }
        else if(intbmi<30 && intbmi>24.9)
        {
            mbmicategory.setText("Overweight");
        }
        else if(intbmi<35 && intbmi>29.9)
        {
            mbmicategory.setText("Obese Class I");
        }
        else if(intbmi<40 && intbmi>34.9)
        {
            mbmicategory.setText("Obese Class II");
        }
        else
        {
            mbmicategory.setText("Obese Class III");
        }


        /*define mrecalculatebmi*/
        mrecalculatebmi=findViewById(R.id.recalculatebmi);

        mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*changing screen from calculated BMI to the main BMI screen*/
                Intent intent=new Intent(bmicalculation.this,BMI.class);
                startActivity(intent);
                finish();
            }
        });

        msavebmi=findViewById(R.id.savebmi);
        msavebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //will check if both title and content has text
                if(!TextUtils.isEmpty(height.getBytes().toString()) && !TextUtils.isEmpty(weight.getBytes().toString()))
                {
                    BMIDatabaseClass db=new BMIDatabaseClass(bmicalculation.this);
                    db.addBMI(mbmiresult.getText().toString(),mbmicategory.getText().toString());

                    //will show data on the recycler view
                    Intent intent = new Intent(bmicalculation.this,BMI.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(bmicalculation.this, "Both fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}