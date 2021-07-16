package com.example.myapplication1.Timetable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication1.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateCourseActivity extends AppCompatActivity {

    private TimePickerDialog timePickerDialog, timePickerDialog2;
    private Calendar calendar;
    private int currentHour, currentMin, currentHour2, currentMin2;
    private Toolbar tb;
    TextInputEditText start_time, end_time, course_name, location, course_code, professor, description;
    CheckBox mon,tues,wed,thurs,fri,sat,sun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        setupUIviews();

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(course_name.getText().toString());

        }

        getAndSetIntentData();

        TimetableDBHelper DB = new TimetableDBHelper(this);

        start_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMin = calendar.get(Calendar.MINUTE);

                timePickerDialog2 = new TimePickerDialog(UpdateCourseActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24Hours.parse(time);
                            SimpleDateFormat f12Hours;
                            f12Hours = new SimpleDateFormat("hh:mm aa");
                            start_time.setText(f12Hours.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, false);
                timePickerDialog2.show();
            }
        });

        end_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                calendar = Calendar.getInstance();
                currentHour2 = calendar.get(Calendar.HOUR_OF_DAY);
                currentMin2 = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(UpdateCourseActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        SimpleDateFormat f24Hours;
                        f24Hours = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24Hours.parse(time);
                            SimpleDateFormat f12Hours;
                            f12Hours = new SimpleDateFormat("hh:mm aa");
                            end_time.setText(f12Hours.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, 12, 0, false);
                timePickerDialog.show();
            }
        });
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
            sb.append("," + " Monday");
        }
        if (tues.isChecked()) {
            sb.append("," + " Tuesday");
        }
        if (wed.isChecked()) {
            sb.append("," + " Wednesday");
        }
        if (thurs.isChecked()) {
            sb.append("," + " Thursday");
        }
        if (fri.isChecked()) {
            sb.append("," + " Friday");
        }
        if (sat.isChecked()) {
            sb.append("," + " Saturday");
        }
        if (sun.isChecked()) {
            sb.append("," + " Sunday");
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
            String daysSel = sb.substring(2);
            Boolean checkinsertdata = coursedb.UpdateCourseData(courseName, courseCode, daysSel, startTime, endTime, prof, loc, desc);
            if (checkinsertdata) {
                Toast.makeText(UpdateCourseActivity.this, "Subject updated.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(UpdateCourseActivity.this, "Try Again.", Toast.LENGTH_SHORT).show();
            }
            finish();
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
                getIntent().hasExtra("end_time") && getIntent().hasExtra("prof") &&
                getIntent().hasExtra("loc") && getIntent().hasExtra("desc")){

            Intent i = getIntent();
            course_name.setText(i.getStringExtra("course_name"));
            course_code.setText(i.getStringExtra("course_code"));
            mon.setChecked(i.getBooleanExtra("mon",false));
            tues.setChecked(i.getBooleanExtra("tues",false));
            wed.setChecked(i.getBooleanExtra("wed",false));
            thurs.setChecked(i.getBooleanExtra("thurs",false));
            fri.setChecked(i.getBooleanExtra("fri",false));
            sat.setChecked(i.getBooleanExtra("sat",false));
            sun.setChecked(i.getBooleanExtra("sun",false));
            start_time.setText(i.getStringExtra("start_time"));
            end_time.setText(i.getStringExtra("end_time"));
            professor.setText(i.getStringExtra("prof"));
            location.setText(i.getStringExtra("loc"));
            description.setText(i.getStringExtra("desc"));

        } else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + course_name.getText().toString() + "?");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TimetableDBHelper coursedb = new TimetableDBHelper(UpdateCourseActivity.this);
                coursedb.DeleteCourseData(course_name.getText().toString());
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.create().show();
    }
}