package com.soccermat.ultramed.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import com.soccermat.ultramed.R;
import com.soccermat.ultramed.activity.SplashActivity;
import com.soccermat.ultramed.utils.NotificationUtils;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        if (remoteMessage.getNotification().getBody() != null) {

            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            Intent intent = new Intent(this, SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            showNotification(getApplicationContext(), "Dad", "addada", intent);
            //  notificationUtils.showNotificationMessage("working","dsd","",new Intent(getApplicationContext(), SplashActivity.class));
        }


    }


    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationCompat.Builder mBuilder = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);

        if (useWhiteIcon) {
            mBuilder = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("ultramed")
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setColor(getApplication().getResources().getColor(R.color.colorPrimary))
                    .setAutoCancel(true);
        } else {

            mBuilder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentTitle("ultramed")
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setColor(getApplication().getResources().getColor(R.color.colorPrimary))
                    .setAutoCancel(true);

        }

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }


}
