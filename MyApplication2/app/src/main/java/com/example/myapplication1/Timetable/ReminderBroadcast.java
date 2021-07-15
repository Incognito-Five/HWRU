package com.example.myapplication1.Timetable;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication1.R;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

<<<<<<< HEAD
<<<<<<< HEAD
        Intent intent1 = new Intent(context, ScheduleReminder.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
=======
/*       Intent intent1 = new Intent(context, ScheduleReminder.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);*//*
>>>>>>> parent of 6f3bcfb (Revert "Merge branch 'main' of https://github.com/Incognito-Five/HWRU into main")
=======
/*       Intent intent1 = new Intent(context, ScheduleReminder.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);*//*
>>>>>>> parent of 6f3bcfb (Revert "Merge branch 'main' of https://github.com/Incognito-Five/HWRU into main")

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyuser")
                .setSmallIcon(R.id.alarm_icon)
                .setContentTitle("Schedule Reminder")
                .setContentText("Hey student, this is a soft reminder of your today's schedule...")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
<<<<<<< HEAD
<<<<<<< HEAD
        notificationManagerCompat.notify(200, builder.build());
=======
        notificationManagerCompat.notify(200, builder.build());*/
>>>>>>> parent of 6f3bcfb (Revert "Merge branch 'main' of https://github.com/Incognito-Five/HWRU into main")
=======
        notificationManagerCompat.notify(200, builder.build());*/
>>>>>>> parent of 6f3bcfb (Revert "Merge branch 'main' of https://github.com/Incognito-Five/HWRU into main")
    }
}
