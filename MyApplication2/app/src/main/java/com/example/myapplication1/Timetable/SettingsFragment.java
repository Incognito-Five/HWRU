package com.example.myapplication1.Timetable;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication1.R;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SettingsFragment extends Fragment {

    SwitchCompat switch_reminder;
    TextView tv1, tv2, select_time;
    Button btn_save, btn_delete;
    TimetableDBHelper dB;
    AlarmManager alarmManager;
    Calendar calendar;
    MaterialTimePicker picker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        createNotificationChannel();
        setupUIviews(view);

        //disabled the uis when switch is off
        tv2.setEnabled(false);
        select_time.setEnabled(false);
        btn_save.setEnabled(false);

        //sets text view to current time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa");
        String strTime = sdf.format(cal.getTime());
        select_time.setText(strTime);

        // Saving state of our app using SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("remindPrefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final boolean isAlarmOn = sharedPreferences.getBoolean("isAlarmOn", false);

        // When user reopens the app
        switch_reminder.setChecked(isAlarmOn);
        switch_reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isAlarmOn) {
                    //enables when switch reminder is on
                    tv2.setEnabled(true);
                    select_time.setEnabled(true);
                    btn_save.setEnabled(true);
                    editor.putBoolean("isAlarmOn", true);
                    editor.apply();
                    switch_reminder.setChecked(true);
                } else {
                    //disabled the uis when switch is off
                    tv2.setEnabled(false);
                    select_time.setEnabled(false);
                    btn_save.setEnabled(false);
                    editor.putBoolean("isAlarmOn", false);
                    editor.apply();
                    switch_reminder.setChecked(false);
                    cancelAlarm();
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDeleteAll();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
                if (picker.getHour() > 12) {
                    String.format("%02d", (picker.getHour() - 12) + ":" + String.format("%02d", picker.getMinute()) + "PM");
                } else {
                    select_time.setText(picker.getHour() + ":" + picker.getMinute() + "AM");
                }
                Toast.makeText(getActivity(), "Working on it...", Toast.LENGTH_SHORT).show();

            }
        });

        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
        return view;
    }

    private void cancelAlarm() {
        Intent intent = new Intent(getActivity(), ReminderBroadcast.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        if (alarmManager == null) {
            alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        }

        alarmManager.cancel(pendingIntent);
        Toast.makeText(getActivity(), "Alarm Cancelled", Toast.LENGTH_SHORT).show();

    }

    private void setAlarm() {
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getActivity(), ReminderBroadcast.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(getActivity(), "Alarm Set Successful", Toast.LENGTH_SHORT).show();
    }

    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();
        picker.show(getActivity().getSupportFragmentManager(), "timetable");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picker.getHour() > 12) {
                    select_time.setText(String.format("%02d", (picker.getHour() - 12) + ":" + String.format("%02d", picker.getMinute()) + "PM"));
                } else {
                    select_time.setText(picker.getHour() + ":" + picker.getMinute() + "AM");
                }
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            }
        });
    }

    private void createNotificationChannel() {
        //channel ID
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "timetableReminderChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("timetable", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setupUIviews(View view) {
        //switch
        switch_reminder = view.findViewById(R.id.switch_reminder);

        //button
        btn_save = view.findViewById(R.id.save_reminder);
        btn_delete = view.findViewById(R.id.btn_deletecourse);

        //textview
        tv2 = view.findViewById(R.id.tv2);
        select_time = view.findViewById(R.id.select_time);
    }

    private void deleteAllSubjects() {
        TimetableDBHelper db = new TimetableDBHelper(getActivity());
        db.deleteAllCourseData();
        getActivity().recreate();
    }

    void confirmDeleteAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete All Subjects?");
        builder.setMessage("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteAllSubjects();
                Toast.makeText(getActivity(), "All Subjects have been deleted.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}