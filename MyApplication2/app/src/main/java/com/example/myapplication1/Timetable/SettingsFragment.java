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
    TextView tv1, tv2;
    TimePicker tp_reminder;
    CheckBox mon2, tues2, wed2, thurs2, fri2, sat2, sun2;
    Button btn_save, btn_delete;
    AlarmManager alarmManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setupUIviews(view);
        hidewhenoff();
        switch_reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//show settings when switch reminder is on
                    tv1.setEnabled(true);
                    tv2.setEnabled(true);
                    tp_reminder.setEnabled(true);
                    mon2.setEnabled(true);
                    tues2.setEnabled(true);
                    wed2.setEnabled(true);
                    thurs2.setEnabled(true);
                    fri2.setEnabled(true);
                    sat2.setEnabled(true);
                    sun2.setEnabled(true);
                    btn_save.setEnabled(true);
                } else {
                    hidewhenoff();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Working on it..", Toast.LENGTH_SHORT).show();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Reminder Set!", Toast.LENGTH_SHORT).show();

                alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(getActivity(), ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),0,intent,0);

                //Set the alarm
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, 8);
                calendar.set(Calendar.MINUTE, 9);
                /*                calendar.set(Calendar.AM_PM, amorpm);*/

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
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

    private void hidewhenoff(){
        //disabled the uis when switch is off

        tv1.setEnabled(false);
        tv2.setEnabled(false);
        tp_reminder.setEnabled(false);
        mon2.setEnabled(false);
        tues2.setEnabled(false);
        wed2.setEnabled(false);
        thurs2.setEnabled(false);
        fri2.setEnabled(false);
        sat2.setEnabled(false);
        sun2.setEnabled(false);
        btn_save.setEnabled(false);
    }

    private void setupUIviews(View view){
        switch_reminder = view.findViewById(R.id.switch_reminder);

        btn_save = view.findViewById(R.id.save_reminder);
        btn_delete = view.findViewById(R.id.btn_delete);
        //textview
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);

        //timepicker
        tp_reminder = view.findViewById(R.id.tp_reminder);

        //checkboxes
        mon2 = view.findViewById(R.id.mon2);
        tues2 = view.findViewById(R.id.tues2);
        wed2 = view.findViewById(R.id.wed2);
        thurs2 = view.findViewById(R.id.thurs2);
        fri2 = view.findViewById(R.id.fri2);
        sat2 = view.findViewById(R.id.sat2);
        sun2 = view.findViewById(R.id.sun2);
    }
}