package com.example.myapplication1.Timetable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication1.R;
import com.example.myapplication1.databinding.ActivityMainBinding;

import java.util.Calendar;

public class TimetableSettings extends AppCompatActivity {

    Toolbar tb_tbsettings;
    SwitchCompat switch_reminder;
    TextView tv1, tv2;
    TimePicker tp_reminder;
    CheckBox mon2, tues2, wed2, thurs2, fri2, sat2, sun2;
    Button btn_save;
    int selHour, selMin;
    String selDays;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_settings);

        setupUIviews();
        setSupportActionBar(tb_tbsettings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hidewhenoff();

        switch_reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//show settings when switch reminder is on
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
                    tp_reminder.setVisibility(View.VISIBLE);
                    mon2.setVisibility(View.VISIBLE);
                    tues2.setVisibility(View.VISIBLE);
                    wed2.setVisibility(View.VISIBLE);
                    thurs2.setVisibility(View.VISIBLE);
                    fri2.setVisibility(View.VISIBLE);
                    sat2.setVisibility(View.VISIBLE);
                    sun2.setVisibility(View.VISIBLE);
                    btn_save.setVisibility(View.VISIBLE);
                } else {
                    hidewhenoff();
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TimetableSettings.this, "Reminder Set!", Toast.LENGTH_SHORT).show();

                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent intent = new Intent(TimetableSettings.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(TimetableSettings.this,0,intent,0);

                //Set the alarm
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, selHour);
                calendar.set(Calendar.MINUTE, selMin);
/*                calendar.set(Calendar.AM_PM, amorpm);*/

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        });

        tp_reminder.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selHour = hourOfDay;
                selMin = minute;
            }
        });
    }

    private void getSelDays(){
        StringBuilder sb = new StringBuilder();
        if (mon2.isChecked()) {
            sb.append("," + "Monday");
        }
        if (tues2.isChecked()) {
            sb.append("," + "Tuesday");
        }
        if (wed2.isChecked()) {
            sb.append("," + "Wednesday");
        }
        if (thurs2.isChecked()) {
            sb.append("," + "Thursday");
        }
        if (fri2.isChecked()) {
            sb.append("," + "Friday");
        }
        if (sat2.isChecked()) {
            sb.append("," + "Saturday");
        }
        if (sun2.isChecked()) {
            sb.append("," + "Sunday");
        }

        selDays = sb.deleteCharAt(sb.indexOf(",")).toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(){
        CharSequence name = "How Well Are U?";
        String desc = "Channel for HWRU";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("notifyuser",name,importance);
        channel.setDescription(desc);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }

    private void hidewhenoff(){
        //hide when the switch reminder is off
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tp_reminder.setVisibility(View.INVISIBLE);
        mon2.setVisibility(View.INVISIBLE);
        tues2.setVisibility(View.INVISIBLE);
        wed2.setVisibility(View.INVISIBLE);
        thurs2.setVisibility(View.INVISIBLE);
        fri2.setVisibility(View.INVISIBLE);
        sat2.setVisibility(View.INVISIBLE);
        sun2.setVisibility(View.INVISIBLE);
        btn_save.setVisibility(View.INVISIBLE);
    }

    private void setupUIviews(){
        tb_tbsettings = findViewById(R.id.tb_tbsettings);
        switch_reminder = findViewById(R.id.switch_reminder);

        btn_save = findViewById(R.id.save_reminder);
        //textview
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        //timepicker
        tp_reminder = findViewById(R.id.tp_reminder);

        //checkboxes
        mon2 = findViewById(R.id.mon2);
        tues2 = findViewById(R.id.tues2);
        wed2 = findViewById(R.id.wed2);
        thurs2 = findViewById(R.id.thurs2);
        fri2 = findViewById(R.id.fri2);
        sat2 = findViewById(R.id.sat2);
        sun2 = findViewById(R.id.sun2);
    }
}