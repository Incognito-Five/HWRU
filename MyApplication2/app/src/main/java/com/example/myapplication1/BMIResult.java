package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BMIResult extends AppCompatActivity {

    Button recalculateBMI;
    Button saveBMI;

    /*to get BMI result*/
    TextView bmiresult,bmicategory;
    Intent intent;
    String bmi;
    float intbmi;

    /*store height, weight of user*/
    String height;
    String weight;
    float intheight,intweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);

        getSupportActionBar().hide();

        /*get data to calculate BMI*/
        intent=getIntent();

        //inclu--
        bmiresult=findViewById(R.id.weightedbmi);
        bmicategory=findViewById(R.id.bmicategory);
        recalculateBMI=findViewById(R.id.recalculatebmi);
        /*define mrecalculatebmi*/
        recalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*changing screen from calculated BMI to the main BMI screen*/
                Intent intent=new Intent(BMIResult.this,BMICalculator.class);
                startActivity(intent);
                finish();
            }
        });

        saveBMI=findViewById(R.id.savebmi);
        saveBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BMIDatabase db=new BMIDatabase(BMIResult.this);
                db.addresult(Float.valueOf(bmiresult.getText().toString().trim()),
                        bmicategory.getText().toString().trim());

                //will show data on the recycler view
                Intent intent = new Intent(BMIResult.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });

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
        bmi=Float.toString(intbmi);
        bmiresult.setText(bmi);

        if(intbmi<18.5)
        {
            bmicategory.setText("Underweight");
        }
        else if(intbmi<25 && intbmi>18.4)
        {
            bmicategory.setText("Normal Range");
        }
        else if(intbmi<30 && intbmi>24.9)
        {
            bmicategory.setText("Overweight");
        }
        else if(intbmi<35 && intbmi>29.9)
        {
            bmicategory.setText("Obese Class I");
        }
        else if(intbmi<40 && intbmi>34.9)
        {
            bmicategory.setText("Obese Class II");
        }
        else
        {
            bmicategory.setText("Obese Class III");
        }
    }
}