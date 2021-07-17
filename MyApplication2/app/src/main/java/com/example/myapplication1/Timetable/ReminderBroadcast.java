package com.example.myapplication1.Timetable;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication1.R;

import java.util.Calendar;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //new activity will open up if the user taps the notification
        Intent i = new Intent(context, Timetable.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,i,0);

        //will show the notification
       NotificationCompat.Builder builder=  new NotificationCompat.Builder(context, "timetable")
               .setSmallIcon(R.drawable.notification_icon)
               .setContentTitle("Schedule Reminder")
               .setContentText("This is a gentle reminder to view your today's class schedule.")
               .setAutoCancel(true)
               .setDefaults(NotificationCompat.DEFAULT_ALL)
               .setPriority(NotificationCompat.PRIORITY_HIGH)
               //will open new activity
               .setContentIntent(pendingIntent);

       NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
       notificationManagerCompat.notify(123,builder.build());
   }
}
