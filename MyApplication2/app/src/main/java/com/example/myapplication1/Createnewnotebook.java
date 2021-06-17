package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Createnewnotebook extends AppCompatActivity {

    EditText mcreatenotebooktitle,mcreatenotecontent;
    FloatingActionButton msavenotebook;
    //*connect database instance here to save particular notes to particular user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnewnotebook);

        //objects from layout
        mcreatenotebooktitle=findViewById(R.id.createnotebooktitle);
        mcreatenotecontent=findViewById(R.id.createnotecontent);
        msavenotebook=findViewById(R.id.savenotebook);
        //actions for toolbar
        Toolbar toolbar=findViewById(R.id.createnotetoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //*store instance of database here
        //*write data on how the notebook will be stored on the database


        //when the save button is clicked, the contents will be directed to the database
        msavenotebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //to make sure user made notes before saving
                String title=mcreatenotebooktitle.getText().toString();
                String content=mcreatenotecontent.getText().toString();
                //can be modified
                if(title.isEmpty() || content.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Both Fields are required",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //*write data on how the notebook will be stored on the database
                }
            }
        });
    }
}