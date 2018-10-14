package com.pillreminder.pillreminder.alarm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.pillreminder.pillreminder.HomeActivity;
import com.pillreminder.pillreminder.IntakeActivity;
import com.pillreminder.pillreminder.IntakeNotificationActivity;
import com.pillreminder.pillreminder.R;
import com.pillreminder.pillreminder.helper.MyApplication;

/**
 * Class for setting notifications.
 */
public class AlarmReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "1";

    @Override
    public void onReceive(Context context, Intent intent) {

        String title = intent.getStringExtra("title");
        int timeStamp = intent.getIntExtra("time_stamp", 0);

        Log.e("timestamp",""+timeStamp);

        // Intent to launch the application when you click on notification
        Intent resultIntent = new Intent(context, IntakeNotificationActivity.class);
        resultIntent.putExtra("cid",timeStamp);

        if (MyApplication.isActivityVisible()) {
            resultIntent = intent;
        }

        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, timeStamp, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Set NotificationChannel for Android Oreo
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "PillTrack Notifications",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }

        // Customize and create notifications
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle(context.getString(R.string.app_name));
        builder.setContentText(title);
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(title));
        builder.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
//        Intent intent1= new Intent(context, IntakeNotificationActivity.class);
//        intent1.putExtra("cid",timeStamp);
//        context.startActivity(intent1);
        notificationManager.notify(timeStamp, notification);


    }
}
