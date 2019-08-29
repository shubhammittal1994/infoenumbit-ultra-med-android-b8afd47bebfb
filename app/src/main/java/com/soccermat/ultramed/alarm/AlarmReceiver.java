/**************************************************************************
 *
 * Copyright (C) 2012-2015 Alex Taradov <alex@taradov.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *************************************************************************/

package com.soccermat.ultramed.alarm;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import android.util.Log;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.notification.AHNotificationHelper;
import com.soccermat.ultramed.MainActivity;
import com.soccermat.ultramed.R;
import com.soccermat.ultramed.activity.HomeActivity;
import com.soccermat.ultramed.calendar.utils.Constants;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver{
    private final String TAG = "AlarmMe";
    private int currentNotificationID = 0;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;
    public static final String CHANNEL_ID = "exampleServiceChannel";
    private NotificationManager notifManager;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Text", String.valueOf(intent.getData()));

        Toast.makeText(context,"Recevied",Toast.LENGTH_SHORT).show();

        Alarm alarm = new Alarm(context);
        alarm.fromIntent(intent);

        DateTime date = new DateTime(context);
        String alarmTime = date.formatTime(alarm);

        Long tsLong = System.currentTimeMillis() / 1000;
        String timeStamp = tsLong.toString();

        showNotificationQuoteMailstones(Constants.strTitle, Constants.strTime, context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void showNotificationQuoteMailstones(String title, String message, Context c) {
        final int NOTIFY_ID = 0; // ID of notification
        String id = "1"; // default_channel_id
        int color = c.getResources().getColor(R.color.yellow);
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (notifManager == null) {
            notifManager = (NotificationManager)c.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(c, id);
            intent = new Intent(c, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(c, 0, intent, 0);
            builder.setContentTitle(Constants.strTitle)                            // required
                    .setSmallIcon(R.drawable.app_icon_notificaton)   // required
                    .setContentText(message) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setColor(color)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker("test")
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        }
        else {
            builder = new NotificationCompat.Builder(c, id);
            intent = new Intent(c, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(c, 0, intent, 0);
            builder.setContentTitle(Constants.strTitle)                            // required
                    .setSmallIcon(R.drawable.app_icon_notificaton)   // required
                    .setContentText(message) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setColor(color)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }
}

