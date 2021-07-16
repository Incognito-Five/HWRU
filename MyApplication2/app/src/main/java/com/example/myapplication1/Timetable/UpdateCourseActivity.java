package com.example.myapplication1.Timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class UpdateCourseActivity extends AppCompatActivity {

    private TimePickerDialog timePickerDialog, timePickerDialog2;
    private Calendar calendar;
    private int currentHour, currentMin, currentHour2, currentMin2;
    private Toolbar tb;
    TextInputEditText start_time, end_time, course_name, location, course_code, professor, description;
    CheckBox mon,tues,wed,thurs,fri,sat,sun;
    Boolean getMon, getTues, getWed, getThu, getFri, getSat, getSun;
    String daysSel;
    String starttime, endtime, coursename, loc, coursecode, prof, des;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        setupUIviews();
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(coursename);

        }

        /*getAndSetIntentData();*/
        TimetableDBHelper DB = new TimetableDBHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_updatecourse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_course:
                confirmDialog();
                break;
            case R.id.save_ud:
                updateCourse();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateCourse() {
        StringBuilder sb = new StringBuilder();
        if (mon.isChecked()) {
            sb.append("," + "Monday");
        }
        if (tues.isChecked()) {
            sb.append("," + "Tuesday");
        }
        if (wed.isChecked()) {
            sb.append("," + "Wednesday");
        }
        if (thurs.isChecked()) {
            sb.append("," + "Thursday");
        }
        if (fri.isChecked()) {
            sb.append("," + "Friday");
        }
        if (sat.isChecked()) {
            sb.append("," + "Saturday");
        }
        if (sun.isChecked()) {
            sb.append("," + "Sunday");
        }
        //Get all the values
        String courseName = course_name.getText().toString();
        String courseCode = course_code.getText().toString();
        String startTime = start_time.getText().toString();
        String endTime = end_time.getText().toString();
        String prof = professor.getText().toString();
        String loc = location.getText().toString();
        String desc = description.getText().toString();

        TimetableDBHelper coursedb = new TimetableDBHelper(UpdateCourseActivity.this);
        if (sb.length() == 0 || courseName.equals("") || startTime.equals("") || endTime.equals("")) {
            Toast.makeText(UpdateCourseActivity.this, "Please enter required fields", Toast.LENGTH_SHORT).show();
        } else {
            String daysSel = sb.deleteCharAt(sb.indexOf(",")).toString();
            Boolean checkinsertdata = coursedb.UpdateCourseData(courseName, courseCode, daysSel, startTime, endTime, prof, loc, desc);
            if (checkinsertdata) {
                Toast.makeText(UpdateCourseActivity.this, "Subject updated.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(UpdateCourseActivity.this, "Try Again.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    
    private void setupUIviews() {

        //Toolbar
        tb = findViewById(R.id.tb_updatecourse);

        //timepickers
        start_time = findViewById(R.id.pick_start_time2);
        end_time = findViewById(R.id.pick_end_time2);

        //text input
        course_name = findViewById(R.id.input_course_name2);
        course_code = findViewById(R.id.input_course_code2);
        professor = findViewById(R.id.input_professor2);
        description = findViewById(R.id.input_description2);
        location = findViewById(R.id.input_location2);

        //checkboxes
        mon = findViewById(R.id.mon2);
        tues = findViewById(R.id.tues2);
        wed = findViewById(R.id.wed2);
        thurs = findViewById(R.id.thurs2);
        fri = findViewById(R.id.fri2);
        sat = findViewById(R.id.sat2);
        sun = findViewById(R.id.sun2);

        String daysSel = "";

    }

    void getAndSetIntentData(){

        if(getIntent().hasExtra("course_name") && getIntent().hasExtra("course_code") &&
                getIntent().hasExtra("mon") && getIntent().hasExtra("tues") &&
                getIntent().hasExtra("wed") && getIntent().hasExtra("thurs") &&
                getIntent().hasExtra("fri") &&  getIntent().hasExtra("sat") &&
                getIntent().hasExtra("sun") && getIntent().hasExtra("start_time") &&
                getIntent().hasExtra("end_time") && getIntent().hasExtra("professor") &&
                getIntent().hasExtra("location") && getIntent().hasExtra("description")){

            //getting data from intent
            coursename = getIntent().getStringExtra("course_name");
            coursecode = getIntent().getStringExtra("course_code");
            getMon = getIntent().getExtras().getBoolean("mon");
            getTues = getIntent().getExtras().getBoolean("tues");
            getWed = getIntent().getExtras().getBoolean("wed");
            getThu = getIntent().getExtras().getBoolean("thurs");
            getFri = getIntent().getExtras().getBoolean("fri");
            getSat = getIntent().getExtras().getBoolean("sat");
            getSun = getIntent().getExtras().getBoolean("sun");
            starttime = getIntent().getStringExtra("start_time");
            endtime = getIntent().getStringExtra("end_time");
            prof = getIntent().getStringExtra("professor");
            loc = getIntent().getStringExtra("location");
            des = getIntent().getStringExtra("description");

            //setting intent data
            course_name.setText(coursename);
            course_code.setText(coursecode);
            mon.setChecked(getMon);
            tues.setChecked(getTues);
            wed.setChecked(getWed);
            thurs.setChecked(getThu);
            fri.setChecked(getFri);
            sat.setChecked(getFri);
            sun.setChecked(getSun);
            start_time.setText(starttime);
            end_time.setText(endtime);
            professor.setText(prof);
            location.setText(loc);
            description.setText(des);

        } else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + coursename + "?");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TimetableDBHelper coursedb = new TimetableDBHelper(UpdateCourseActivity.this);
                coursedb.DeleteCourseData(coursename);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}