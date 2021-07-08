package com.example.myapplication1;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DialogAddCourse extends DialogFragment {


    Button save_btn, cancel_btn;
    TextInputEditText start_time, end_time, course_name, course_code, professor, location, description;
    TimePickerDialog timePickerDialog, timePickerDialog2;
    Calendar calendar;
    int currentHour, currentMin, currentHour2, currentMin2;
    CheckBox mon, tues, wed, thurs, fri, sat, sun;
    String daysSel;
    TextView sched;
    TimetableDBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_addcourse, container, false);

        //buttons
        save_btn = view.findViewById(R.id.save_btn);
        cancel_btn = view.findViewById(R.id.cancel_btn);

        //timepickers
        start_time = view.findViewById(R.id.pick_start_time);
        end_time = view.findViewById(R.id.pick_end_time);

        //text input
        course_name = view.findViewById(R.id.input_course_name);
        course_code = view.findViewById(R.id.input_course_code);
        professor = view.findViewById(R.id.input_professor);
        description = view.findViewById(R.id.input_description);
        location = view.findViewById(R.id.input_location);

        //checkboxes
        mon = view.findViewById(R.id.mon);
        tues = view.findViewById(R.id.tues);
        wed = view.findViewById(R.id.wed);
        thurs = view.findViewById(R.id.thurs);
        fri = view.findViewById(R.id.fri);
        sat = view.findViewById(R.id.sat);
        sun = view.findViewById(R.id.sun);

        daysSel = "";
        sched = view.findViewById(R.id.sched);
        DB = new TimetableDBHelper(getActivity());

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
                    Toast.makeText(getActivity(), "Please enter required fields", Toast.LENGTH_SHORT).show();
                } else {
                    String daysSel = sb.deleteCharAt(sb.indexOf(",")).toString();
                    Boolean checkinsertdata = DB.AddCourseData(courseName, courseCode, daysSel, startTime, endTime, prof, loc, desc);
                    if (checkinsertdata) {
                        Toast.makeText(getActivity(), "New Course subject added.", Toast.LENGTH_LONG).show();
                        dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Course subject not added. Try Again.", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }
                }
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        start_time.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMin = calendar.get(Calendar.MINUTE);

                timePickerDialog2 = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
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

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
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

        return view;
    }

}

