package com.example.myapplication1.Timetable;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication1.R;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;


public class SettingsFragment extends Fragment {

    SwitchCompat switch_reminder;
    TextView tv1, tv2, select_time;
    Button btn_save, btn_delete;
    TimetableDBHelper dB;
    AlarmManager alarmManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setupUIviews(view);

        //disabled the uis when switch is off
        tv2.setEnabled(false);
        select_time.setEnabled(false);
        btn_save.setEnabled(false);

        switch_reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //enables when switch reminder is on
                    tv2.setEnabled(true);
                    select_time.setEnabled(true);
                    btn_save.setEnabled(true);
                } else {
                    //disabled the uis when switch is off
                    tv2.setEnabled(false);
                    select_time.setEnabled(false);
                    btn_save.setEnabled(false);
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*dB.DeleteAllCourseData();*/
                Toast.makeText(getActivity(), "All Subjects have been deleted.", Toast.LENGTH_SHORT).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Working on it...", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(){
        CharSequence name = "How Well Are U?";
        String desc = "Channel for HWRU";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("notifyuser",name,importance);
        channel.setDescription(desc);

        NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }
    
    private void setupUIviews(View view){
        //switch
        switch_reminder = view.findViewById(R.id.switch_reminder);

        //button
        btn_save = view.findViewById(R.id.save_reminder);
        btn_delete = view.findViewById(R.id.btn_delete);

        //textview
        tv2 = view.findViewById(R.id.tv2);
        select_time = view.findViewById(R.id.select_time);

    }
}