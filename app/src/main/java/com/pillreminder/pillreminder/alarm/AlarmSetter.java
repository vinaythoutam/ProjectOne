package com.pillreminder.pillreminder.alarm;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.pillreminder.pillreminder.database.DatabaseHandler;
import com.pillreminder.pillreminder.model.NewMedModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Class for restoring all notifications after device reboot.
 */
public class AlarmSetter extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DatabaseHandler helper = DatabaseHandler.getInstance(context);

        AlarmHelper.getInstance().init(context);
        AlarmHelper alarmHelper = AlarmHelper.getInstance();

        List<NewMedModel> tasks = new ArrayList<>();
        tasks.addAll(helper.getAllMedData());

        for (NewMedModel task : tasks) {
            if (task.getStartDate() != 0) {
                alarmHelper.setAlarm(task);
            }
        }
    }


}
