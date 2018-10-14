package com.pillreminder.pillreminder.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.pillreminder.pillreminder.model.NewMedModel;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Class for initializing alarm service (uses the Singleton pattern).
 */
public class AlarmHelper {

    private static AlarmHelper mInstance;
    private Context mContext;
    private AlarmManager mAlarmManager;

    /**
     * Constructor is private to prevent direct instantiation.
     */
    private AlarmHelper() {

    }

    /**
     * This static method ensures that only one AlarmHelper will ever exist at any given time.
     */
    public static AlarmHelper getInstance() {
        if (mInstance == null) {
            mInstance = new AlarmHelper();
        }
        return mInstance;
    }

    /**
     * Alarm service initialization.
     */
    public void init(Context context) {
        this.mContext = context;
        mAlarmManager = (AlarmManager) context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
    }

    /**
     * Passes the required data to the AlarmReceiver to create a notification.
     */
    public void setAlarm(NewMedModel task) {
        Intent intent = new Intent(mContext, AlarmReceiver.class);
        intent.putExtra("title", task.getMedName());
        intent.putExtra("time_stamp", task.getIntakeTime1());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext.getApplicationContext(), (int) task.getIntakeTime1(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mAlarmManager.set(AlarmManager.RTC_WAKEUP, task.getStartDate(), pendingIntent);
    }

    /**
     * Removes a notification by id (timeStamp).
     */
    public void removeNotification(long taskTimeStamp, Context context) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel((int) taskTimeStamp);
    }

    /**
     * Removes a alarm by id (timeStamp).
     */
    public void removeAlarm(long taskTimeStamp) {
        Intent intent = new Intent(mContext, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, (int) taskTimeStamp,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mAlarmManager.cancel(pendingIntent);
    }
}
