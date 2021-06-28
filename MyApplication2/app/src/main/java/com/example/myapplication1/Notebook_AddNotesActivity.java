package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Notebook_AddNotesActivity extends AppCompatActivity {

    EditText title,content;
    Button saveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_add_notes);

        title=findViewById(R.id.notebooktitle);
        content=findViewById(R.id.notebookcontent);
        saveNote=findViewById(R.id.savenote);

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //will check if both title and content has text
                if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(content.getText().toString()))
                {
                    NotebookDatabaseClass db=new NotebookDatabaseClass(Notebook_AddNotesActivity.this);
                    db.addNotes(title.getText().toString(),content.getText().toString());

                    //will show data on the recycler view
                    Intent intent = new Intent(Notebook_AddNotesActivity.this,Notebook.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(Notebook_AddNotesActivity.this, "Both fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}