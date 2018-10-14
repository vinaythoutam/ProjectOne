package com.pillreminder.pillreminder.helper;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.pillreminder.pillreminder.HomeActivity;
import com.pillreminder.pillreminder.R;
import com.pillreminder.pillreminder.ScheduleActivity;
import com.pillreminder.pillreminder.alarm.AlarmReceiver;
import com.pillreminder.pillreminder.database.DatabaseHandler;
import com.pillreminder.pillreminder.model.NewMedModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ReusableCode {

    //getMonth in Char method
    public static String theMonth(int month) {
        String[] monthNames = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        return monthNames[month];
    }

    //getDate in number method
    public static String theDate(int datee) {
        String[] dateNames = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        return dateNames[datee - 1];
    }

    //getDay method
    public static String theDay(int day) {
        String[] dayNames = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        return dayNames[day];
    }

    public static void showValidationDialog(String text, Context context) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_validations);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        DisplayMetrics mertics = context.getResources().getDisplayMetrics();
        int width = mertics.widthPixels;

        Window window = dialog.getWindow();
        window.setLayout((int) (width * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        TextView oops = (TextView) dialog.findViewById(R.id.oopsTV);
        TextView textSet = (TextView) dialog.findViewById(R.id.textTV);
        TextView ok = (TextView) dialog.findViewById(R.id.okTV);
        textSet.setText(text);

        //onClick
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void showItemPopup(final Context context, final NewMedModel nm) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_click_pop_up);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        DisplayMetrics mertics = context.getResources().getDisplayMetrics();
        int width = mertics.widthPixels;

        Window window = dialog.getWindow();
        window.setLayout((int) (width * 0.80), WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

//        Constants fonts = new Constants();

        TextView medName = (TextView) dialog.findViewById(R.id.medNameText);
        TextView time1 = (TextView) dialog.findViewById(R.id.time1TV);
        TextView time2 = (TextView) dialog.findViewById(R.id.time2TV);
        TextView time3 = (TextView) dialog.findViewById(R.id.time3TV);
        TextView take = (TextView) dialog.findViewById(R.id.takeTV);
        TextView skip = (TextView) dialog.findViewById(R.id.skipTV);
        TextView cancel = (TextView) dialog.findViewById(R.id.cancelTV);

        medName.setText(nm.getMedName());
        time1.setText(nm.getTime1());
        if(!nm.getTime2().equals("")){
            time2.setVisibility(View.VISIBLE);
            time2.setText(nm.getTime2());
        }
        if(!nm.getTime3().equals("")){
            time3.setVisibility(View.VISIBLE);
            time3.setText(nm.getTime3());
        }

        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler databaseHandler= new DatabaseHandler(context);
                nm.setTaken_time(ReusableCode.getFullDate(Calendar.getInstance().getTimeInMillis()));
                nm.setTaken_date(ReusableCode.getDate(Calendar.getInstance().getTimeInMillis()));

                long n =databaseHandler.insTakeValues(nm);
                if(n>0){
                    Toast.makeText(context, "Medicine taken.", Toast.LENGTH_SHORT).show();
                    long l = databaseHandler.updateStatus("Taken",nm.getcID());
                    Log.e("update result",""+l);
//                    if(l>0){
//                        Activity activity= (Activity) context;
//                        activity.recreate();
//                    }
                }else {
                    Toast.makeText(context, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHandler databaseHandler= new DatabaseHandler(context);
                nm.setSkip_time(ReusableCode.getFullDate(Calendar.getInstance().getTimeInMillis()));
                nm.setSkip_date(ReusableCode.getDate(Calendar.getInstance().getTimeInMillis()));

                long n =databaseHandler.insSkipValues(nm);
                if(n>0){
                    Toast.makeText(context, "Medicine Skipped.", Toast.LENGTH_SHORT).show();
                    long l = databaseHandler.updateStatus("Skipped",nm.getcID());
                    Log.e("update result",""+l);
//                    if(l>0){
//                        Activity activity= (Activity) context;
//                        activity.recreate();
//                    }
                }else {
                    Toast.makeText(context, "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }


    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static String convertDate(String datE) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String returnDate = null;
        try {
            Date strDate = dateFormat.parse(datE);
            returnDate = df.format(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnDate;
    }

    public static String convertDateFormat2(String datE) {
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        Date date = null;
        try {
            date = (Date)formatter.parse(datE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        System.out.println(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String formatedDate =  (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/"  + cal.get(Calendar.YEAR);
        System.out.println("formatedDate : " + formatedDate);
        return formatedDate;
    }

    public static void start(Activity activity, PendingIntent pendingIntent) {
        AlarmManager manager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        int interval = 2000;

        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Log.e("Alarm Set", "Alarm Set");
    }

    public static void cancel(Activity activity,PendingIntent pendingIntent) {
        AlarmManager manager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Log.e("Alarm cancel", "Alarm cancel");
    }

    public static String getDate(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return dateFormat.format(date);
    }

    public static String getDateString(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        return dateFormat.format(date);
    }

    public static String getTime(long time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(time);
    }

    public static String getFullDate(long date) {
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd.MM.yy  HH:mm");
        return fullDateFormat.format(date);
    }

    public static void showDelayPopup(final Context context,final NewMedModel task) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.delay_popup);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        final AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        DisplayMetrics mertics = context.getResources().getDisplayMetrics();
        int width = mertics.widthPixels;

        Window window = dialog.getWindow();
        window.setLayout((int) (width * 0.80), WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.BOTTOM;
        wlp.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

//        Constants fonts = new Constants();

        TextView p15min = (TextView) dialog.findViewById(R.id.p15min);
        TextView p30min = (TextView) dialog.findViewById(R.id.p30min);
        TextView p1hour = (TextView) dialog.findViewById(R.id.p1hour);
        final TextView cancel = (TextView) dialog.findViewById(R.id.cancelTV);

        p15min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long p15= TimeUnit.MINUTES.toMillis(15);
                long newTime=System.currentTimeMillis()+p15;

                Intent intent = new Intent(context, AlarmReceiver.class);
                intent.putExtra("title", task.getMedName());
                intent.putExtra("time_stamp", task.getcID());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, newTime, pendingIntent);

                dialog.dismiss();

            }
        });
        p30min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long p30= TimeUnit.MINUTES.toMillis(30);
                long newTime=System.currentTimeMillis()+p30;

                Intent intent = new Intent(context, AlarmReceiver.class);
                intent.putExtra("title", task.getMedName());
                intent.putExtra("time_stamp", task.getcID());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, newTime, pendingIntent);

                dialog.dismiss();
            }
        });
        p1hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long p1hour= TimeUnit.MINUTES.toMillis(60);
                long newTime=System.currentTimeMillis()+p1hour;

                Intent intent = new Intent(context, AlarmReceiver.class);
                intent.putExtra("title", task.getMedName());
                intent.putExtra("time_stamp", task.getcID());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, newTime, pendingIntent);

                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}

