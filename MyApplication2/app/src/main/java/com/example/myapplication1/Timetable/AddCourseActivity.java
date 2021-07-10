package com.example.myapplication1.Timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication1.Notebook;
import com.example.myapplication1.NotebookDatabaseClass;
import com.example.myapplication1.Notebook_AddNotesActivity;
import com.example.myapplication1.R;
import com.example.myapplication1.databinding.FragmentD1MondayBinding;
import com.example.myapplication1.days.D2_Tuesday;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddCourseActivity extends AppCompatActivity {

    TextInputEditText start_time, end_time, course_name, location, course_code, professor, description;
    TimePickerDialog timePickerDialog, timePickerDialog2;
    Calendar calendar;
    int currentHour, currentMin, currentHour2, currentMin2;
    String daysSel;
    TextView tv_coursename, tv_startTime, tv_endTime, tv_location;
    ImageView img_back;
    Button save_btn;
    CheckBox mon, tues, wed, thurs, fri, sat, sun;
    TimetableDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        setupUIviews();

        DB = new TimetableDBHelper(this);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                if (sb.length() == 0 || courseName.equals("") || startTime.equals("") || endTime.equals("")) {
                    Toast.makeText(AddCourseActivity.this, "Please enter required fields", Toast.LENGTH_SHORT).show();
                } else {
                    String daysSel = sb.deleteCharAt(sb.indexOf(",")).toString();
                    Boolean checkinsertdata = DB.AddCourseData(courseName, courseCode, daysSel, startTime, endTime, prof, loc, desc);
                    if (checkinsertdata) {
                        Toast.makeText(AddCourseActivity.this, "New Course subject added.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddCourseActivity.this, "Course subject not added. Try Again.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        start_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMin = calendar.get(Calendar.MINUTE);

                timePickerDialog2 = new TimePickerDialog(AddCourseActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24Hours.parse(time);
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
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

                timePickerDialog = new TimePickerDialog(AddCourseActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute;
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24Hours.parse(time);
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
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

    private void setupUIviews() {
        //ImageView
        img_back = findViewById(R.id.img_back);

        //Buttons
        save_btn = findViewById(R.id.save_btn);

        //timepickers
        start_time = findViewById(R.id.pick_start_time);
        end_time = findViewById(R.id.pick_end_time);

        //text input
        course_name = findViewById(R.id.input_course_name);
        course_code = findViewById(R.id.input_course_code);
        professor = findViewById(R.id.input_professor);
        description = findViewById(R.id.input_description);
        location = findViewById(R.id.input_location);

        //checkboxes
        mon = findViewById(R.id.mon);
        tues = findViewById(R.id.tues);
        wed = findViewById(R.id.wed);
        thurs = findViewById(R.id.thurs);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);
        sun = findViewById(R.id.sun);

        //TextView
        tv_coursename = findViewById(R.id.tv_coursename);
        tv_startTime = findViewById(R.id.tv_startTime);
        tv_endTime = findViewById(R.id.tv_endTime);
        tv_location = findViewById(R.id.tv_location);

        daysSel = "";

    }

}