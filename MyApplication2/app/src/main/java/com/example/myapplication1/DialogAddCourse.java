package com.example.myapplication1;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DialogAddCourse extends DialogFragment {

    Button save_btn, cancel_btn;
    TextInputEditText start_time, end_time, course_name, course_code, professor, description;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour, currentMin, currentHour2, currentMin2;
    TimetableDBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_addcourse, container, false);

        save_btn = view.findViewById(R.id.save_btn);
        cancel_btn = view.findViewById(R.id.cancel_btn);

        start_time = view.findViewById(R.id.pick_start_time);
        end_time = view.findViewById(R.id.pick_end_time);
        course_name = view.findViewById(R.id.input_course_name);
        course_code = view.findViewById(R.id.input_course_code);
        professor = view.findViewById(R.id.input_professor);
        description = view.findViewById(R.id.input_description);

        DB = new TimetableDBHelper(getActivity());

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_nameTXT = course_name.getText().toString();
                String course_codeTXT = course_code.getText().toString();
                String professorTXT = professor.getText().toString();
                String start_timeTXT = start_time.getText().toString();
                String end_timeTXT = end_time.getText().toString();
                String descriptionTXT = description.getText().toString();

                Boolean checkinsertdata = DB.AddCourseData(course_nameTXT, course_codeTXT,professorTXT, start_timeTXT,end_timeTXT,descriptionTXT);
                if(checkinsertdata){
                    Toast.makeText(getActivity(),"New Course Added", Toast.LENGTH_SHORT).show();
                    dismiss();
                } else {
                    Toast.makeText(getActivity(),"Course not added.Try Again.", Toast.LENGTH_SHORT).show();
                    dismiss();
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

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String time = hourOfDay + ":" + minute ;
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24Hours.parse(time);
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                            start_time.setText(f12Hours.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },12,0,false);
                timePickerDialog.show();
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
                        String time = hourOfDay + ":" + minute ;
                        SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                        try {
                            Date date = f24Hours.parse(time);
                            SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                            end_time.setText(f12Hours.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },12,0,false);
                timePickerDialog.show();
            }
        });
        return view;
    }
}

