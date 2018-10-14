package com.pillreminder.pillreminder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.pillreminder.pillreminder.alarm.AlarmReceiver;
import com.pillreminder.pillreminder.database.DatabaseHandler;
import com.pillreminder.pillreminder.helper.ReusableCode;
import com.pillreminder.pillreminder.helper.Singleton;
import com.pillreminder.pillreminder.model.NewMedModel;
import com.suke.widget.SwitchButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ScheduleActivity extends AppCompatActivity {


    LinearLayout startDate, endDate, frequencyLL, timesdDay, intakeForm, refillRemindTimeLL;
    SwitchButton refillReminderSB, intakeTimeSB;
    EditText notificationText;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener dateStart, dateEnd;
    TextView startDateText, endDateText, intakeTimeText1, intakeTimeText2, intakeTimeText3, intakeFormText, scheduleNext, runOutText, refiRemTimeText,
            timesDayText, freqTextView;
    ImageView intakeImage;

    DatabaseHandler dbHandler;

    long start_date=0, end_date =0,curr_date;
    String frequency = "";
    long time1=0, time2=0, time3=0;
    boolean isRefillReminderOn = false, isIntakeReminderOn = false;
    String totalPills = "";
    String remind_count = "";
    int timesInDay = 0;
    long refillRemindTime=0 ;
    String notifText;
    String selectedFrequency = "";
    AlarmManager alarmManager;
    String timeText1="",timeText2="",timeText3="",refillTimeText="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        dbHandler = new DatabaseHandler(this);
        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView toolImageView = (ImageView) findViewById(R.id.toolImage);

        toolImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        startDate = (LinearLayout) findViewById(R.id.startDateLL);
        endDate = (LinearLayout) findViewById(R.id.endDateLL);
        frequencyLL = (LinearLayout) findViewById(R.id.frequencyLL);
        timesdDay = (LinearLayout) findViewById(R.id.timesDayLL);
        intakeForm = (LinearLayout) findViewById(R.id.intakeLL);
        refillRemindTimeLL = (LinearLayout) findViewById(R.id.remindTimeLL);

        startDateText = (TextView) findViewById(R.id.startDateText);
        endDateText = (TextView) findViewById(R.id.endDateText);
        intakeTimeText1 = (TextView) findViewById(R.id.intakeFormTimeText1);
        intakeTimeText2 = (TextView) findViewById(R.id.intakeFormTimeText2);
        intakeTimeText3 = (TextView) findViewById(R.id.intakeFormTimeText3);
        intakeFormText = (TextView) findViewById(R.id.intakeFormText);
        intakeImage = (ImageView) findViewById(R.id.intakeFormImage);
        scheduleNext = (TextView) findViewById(R.id.nextScheduleBtn);
        runOutText = (TextView) findViewById(R.id.runOutText);
        refiRemTimeText = (TextView) findViewById(R.id.remindTimeText);
        timesDayText = (TextView) findViewById(R.id.timesDayText);
        freqTextView = (TextView) findViewById(R.id.freqText);

        refillReminderSB = (SwitchButton) findViewById(R.id.switch_button_refill);
        intakeTimeSB = (SwitchButton) findViewById(R.id.switch_button_intake);

        notificationText = (EditText) findViewById(R.id.notificationET);

        final Intent intent = getIntent();
        String tag = intent.getStringExtra("FormID"); //  this is image file name
        String PACKAGE_NAME = getApplicationContext().getPackageName();
        int imgId = getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + tag, null, null);

        intakeImage.setImageBitmap(BitmapFactory.decodeResource(getResources(), imgId));
        intakeFormText.setText(intent.getStringExtra("DosageCount") + " " + intent.getStringExtra("FormName"));

        myCalendar = Calendar.getInstance();
        dateStart = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                calculateDays(1539085860212,1539385860212);
                Log.e("startdatemillis",myCalendar.getTimeInMillis()+"");
                updateLabelStartDate();
            }

        };

        dateEnd = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Log.e("enddatemillis",myCalendar.getTimeInMillis()+"");
                updateLabelEndDate();

                Log.e("Days Count",calculateDays(start_date,end_date)+"");
            }

        };

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ScheduleActivity.this, R.style.DialogTheme, dateStart, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(ScheduleActivity.this, R.style.DialogTheme, dateEnd, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        intakeTimeText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcurrentTime1 = Calendar.getInstance();
                int hour = mcurrentTime1.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime1.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String AM_PM;
                        int mHour = selectedHour;
                        int mMin = selectedMinute;
                        if (mHour < 12) {
                            AM_PM = "AM";

                        } else {
                            AM_PM = "PM";
                            mHour = mHour - 12;
                        }
                        String hour, min;
                        if (mHour < 10) {
                            if(mHour==0){
                                hour="12";
                            }else {
                                hour = "0" + mHour;
                            }
                        } else {
                            hour = mHour + "";
                        }
                        if (mMin < 10) {
                            min = "0" + mMin;
                        } else {
                            min = mMin + "";
                        }
                        intakeTimeText1.setText(hour + ":" + min + " " + AM_PM);
                        timeText1=hour + ":" + min + " " + AM_PM;
                        Calendar mCalendar=Calendar.getInstance();
                        if(start_date>0) {
                            mCalendar.setTimeInMillis(start_date);
                        }

                        mCalendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        mCalendar.set(Calendar.MINUTE, selectedMinute);
                        mCalendar.set(Calendar.SECOND, 0);
                        time1 = mCalendar.getTimeInMillis();
                        Log.e("time1",mCalendar.getTimeInMillis()+"");


                        Log.e("freqList",findFrequency(selectedFrequency)+"");
                        Log.e("getDay",getDayFromDateString(System.currentTimeMillis()));

                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.show();
            }
        });
        intakeTimeText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcurrentTime2 = Calendar.getInstance();
                int hour = mcurrentTime2.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime2.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String AM_PM;
                        int mHour = selectedHour;
                        int mMin = selectedMinute;
                        if (mHour < 12) {
                            AM_PM = "AM";

                        } else {
                            AM_PM = "PM";
                            mHour = mHour - 12;
                        }
                        String hour, min;
                        if (mHour < 10) {
                            if(mHour==0){
                                hour="12";
                            }else {
                                hour = "0" + mHour;
                            }
                        } else {
                            hour = mHour + "";
                        }
                        if (mMin < 10) {
                            min = "0" + mMin;
                        } else {
                            min = mMin + "";
                        }
                        intakeTimeText2.setText(hour + ":" + min + " " + AM_PM);
                        timeText2=hour + ":" + min + " " + AM_PM;
                        Calendar mCalendar=Calendar.getInstance();
                        if(start_date>0) {
                            mCalendar.setTimeInMillis(start_date);
                        }
                        mCalendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        mCalendar.set(Calendar.MINUTE, selectedMinute);
                        mCalendar.set(Calendar.SECOND, 0);
                        time2 = mCalendar.getTimeInMillis();

                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.show();
            }
        });
        intakeTimeText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcurrentTime3 = Calendar.getInstance();
                int hour = mcurrentTime3.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime3.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String AM_PM;
                        int mHour = selectedHour;
                        int mMin = selectedMinute;
                        if (mHour < 12) {
                            AM_PM = "AM";

                        } else {
                            AM_PM = "PM";
                            mHour = mHour - 12;
                        }
                        String hour, min;
                        if (mHour < 10) {
                            if(mHour==0){
                                hour="12";
                            }else {
                                hour = "0" + mHour;
                            }
                        } else {
                            hour = mHour + "";
                        }
                        if (mMin < 10) {
                            min = "0" + mMin;
                        } else {
                            min = mMin + "";
                        }
                        intakeTimeText3.setText(hour + ":" + min + " " + AM_PM);
                        timeText3=hour + ":" + min + " " + AM_PM;
                        Calendar mCalendar=Calendar.getInstance();
                        if(start_date>0) {
                            mCalendar.setTimeInMillis(start_date);
                        }
                        mCalendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        mCalendar.set(Calendar.MINUTE, selectedMinute);
                        mCalendar.set(Calendar.SECOND, 0);
                        time3 = mCalendar.getTimeInMillis();
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.show();
            }
        });

        scheduleNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (start_date == 0) {
                    ReusableCode.showValidationDialog("Please select start date", ScheduleActivity.this);
                } else if (end_date == 0) {
                    ReusableCode.showValidationDialog("Please select end date", ScheduleActivity.this);
                }
//                else if(frequency==""){
//                    ReusableCode.showValidationDialog("Please select frequency",ScheduleActivity.this);
//                }
                else if (timesInDay == 0) {
                    ReusableCode.showValidationDialog("Please select time(s) in a day", ScheduleActivity.this);
                } else if (intakeTimeText1.getVisibility() == View.VISIBLE && intakeTimeText1.getText().toString().equalsIgnoreCase("Time1")) {
                    ReusableCode.showValidationDialog("Please select remind time", ScheduleActivity.this);
                } else if (intakeTimeText2.getVisibility() == View.VISIBLE && intakeTimeText2.getText().toString().equalsIgnoreCase("Time2")) {
                    ReusableCode.showValidationDialog("Please select remind time", ScheduleActivity.this);
                } else if (intakeTimeText3.getVisibility() == View.VISIBLE && intakeTimeText3.getText().toString().equalsIgnoreCase("Time3")) {
                    ReusableCode.showValidationDialog("Please select remind time", ScheduleActivity.this);
                } else if (isRefillReminderOn && totalPills == "") {
                    ReusableCode.showValidationDialog("Please enter total pills", ScheduleActivity.this);
                } else if (isRefillReminderOn && remind_count == "") {
                    ReusableCode.showValidationDialog("Please enter total pills", ScheduleActivity.this);
                } else if (isRefillReminderOn && refillRemindTime==0) {
                    ReusableCode.showValidationDialog("Please select refill remind time", ScheduleActivity.this);
                } else {

                    Log.e("else", "else");
                    NewMedModel nm = new NewMedModel();
                    nm.setMedName(intent.getStringExtra("MedName"));
                    nm.setMedFormName(intent.getStringExtra("FormName"));
                    nm.setMedImageID(intent.getStringExtra("FormID"));
                    nm.setDosageCount(intent.getStringExtra("DosageCount"));
                    nm.setDosageType(intent.getStringExtra("DosageForm"));
                    nm.setIsWithRegardOrNot(intent.getStringExtra("IsWithMeal"));
                    nm.setStartDate(start_date);
                    nm.setEndDate(end_date);
                    nm.setFrequency(selectedFrequency);
                    nm.setTimesInDay(String.valueOf(timesInDay));
                    nm.setIntakeTime1(time1);
                    nm.setIntakeTime2(time2);
                    nm.setIntakeTime3(time3);
                    nm.setIsRefillReminderOn(String.valueOf(isRefillReminderOn));
                    nm.setTotalPills(totalPills);
                    nm.setRemindBeforeCount(remind_count);
                    nm.setRefillReminderTime(refillRemindTime);
                    nm.setNotifText(notificationText.getText().toString());
                    nm.setCurrDate(ReusableCode.getDate(System.currentTimeMillis()));
                    nm.setTime1(timeText1);
                    nm.setTime2(timeText2);
                    nm.setTime3(timeText3);
                    nm.setRefillTimeText(refillTimeText);
                    nm.setcID((int)System.currentTimeMillis()/100000);
                    nm.setStatus("");

                    long n = dbHandler.insNewMeaValues(nm);

//                    ModelTask task = new ModelTask();
//                    task.setTitle(intent.getStringExtra("FormName")+" | " +intent.getStringExtra("DosageCount")+" "+intent.getStringExtra("DosageForm"));
//                    task.setDate(start_date);

                    alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);

                    // Set notification to the current task
                    if (nm.getStartDate() != 0) {
//                        setAlarm(nm);
                        setAlarms(nm);
                    }

                    if (n > 0) {
                        Toast.makeText(ScheduleActivity.this, "New Medicine added", Toast.LENGTH_SHORT).show();
                        if(Singleton.Instance().getFlow().equals("")) {
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getApplicationContext(), MedContainerActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(ScheduleActivity.this, "Something went wrong. try again", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            }

//            }
        });


        refillReminderSB.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    isRefillReminderOn = true;
                    showRefillReminderPopup(ScheduleActivity.this);
                } else {
                    isRefillReminderOn = false;
                }
            }
        });

        refillRemindTimeLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ScheduleActivity.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String AM_PM;
                        int mHour = selectedHour;
                        int mMin = selectedMinute;
                        if (mHour < 12) {
                            AM_PM = "AM";

                        } else {
                            AM_PM = "PM";
                            mHour = mHour - 12;
                        }
                        String hour, min;
                        if (mHour < 10) {
                            if(mHour==0){
                                hour="12";
                            }else {
                                hour = "0" + mHour;
                            }
                        } else {
                            hour = mHour + "";
                        }
                        if (mMin < 10) {
                            min = "0" + mMin;
                        } else {
                            min = mMin + "";
                        }
                        refiRemTimeText.setText(hour + ":" + min + " " + AM_PM);
                        refillTimeText=hour + ":" + min + " " + AM_PM;
                        Calendar mCalendar=Calendar.getInstance();
                        mCalendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        mCalendar.set(Calendar.MINUTE, selectedMinute);
                        mCalendar.set(Calendar.SECOND, 0);
                        refillRemindTime = mCalendar.getTimeInMillis();
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.show();
            }
        });

        intakeTimeSB.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    isIntakeReminderOn = true;
                } else {
                    isIntakeReminderOn = false;
                }
            }
        });

        frequencyLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(ScheduleActivity.this, FrequencyActivity.class), 7);
            }
        });
        timesdDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimes();
            }
        });


    }

    private void updateLabelStartDate() {
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        startDateText.setText(sdf.format(myCalendar.getTime()));
        Log.e("DateString",dateFormat.format(myCalendar.getTime()));
        start_date = myCalendar.getTimeInMillis();
    }

    private void updateLabelEndDate() {
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        endDateText.setText(sdf.format(myCalendar.getTime()));
        end_date = myCalendar.getTimeInMillis();
    }

    public void showRefillReminderPopup(Context context) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.refill_reminder_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        DisplayMetrics mertics = context.getResources().getDisplayMetrics();
        int width = mertics.widthPixels;

        Window window = dialog.getWindow();
        window.setLayout((int) (width * 0.75), WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);


        final EditText totalCount = (EditText) dialog.findViewById(R.id.totalCountET);
        final EditText remindCount = (EditText) dialog.findViewById(R.id.remindCountET);
        Button cancel = (Button) dialog.findViewById(R.id.cancelButton);
        Button done = (Button) dialog.findViewById(R.id.doneButton);

        //onClick
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                refillReminderSB.setChecked(false);
                isRefillReminderOn = false;
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                runOutText.setText(remindCount.getText().toString() + " pill(s) before I run out");
                totalPills = totalCount.getText().toString().trim();
                remind_count = remindCount.getText().toString().trim();
                isRefillReminderOn = true;
            }
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void showTimes() {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.pop_up_new_style);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        DisplayMetrics mertics = this.getResources().getDisplayMetrics();
        int width = mertics.widthPixels;

        Window window = dialog.getWindow();
        window.setLayout((int) (width * 0.43), (int) (width * 0.77));

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        ListView listView = (ListView) dialog.findViewById(R.id.pop_up_list_view);
        TextView headerText = (TextView) dialog.findViewById(R.id.headerTV);
        headerText.setText("Times");
        ImageView headerCloseImage = (ImageView) dialog.findViewById(R.id.headerCloseIV);
        //        final String[] frequency={"0 days","2 days","3 days","4 days","5 days","6 days","7 days",
//                "8 days","9 days","10 days","11 days","12 days","13 days","14 days","15 days"};
        final List<String> times = new ArrayList<>();
        times.add("1");
        times.add("2");
        times.add("3");

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, R.layout.drop_down_item, times);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                timesDayText.setText(times.get(i) + " time(s) a day");
                timesInDay = Integer.parseInt(times.get(i));

                if (Integer.parseInt(times.get(i)) == 1) {
                    intakeTimeText1.setVisibility(View.VISIBLE);
                    intakeTimeText2.setVisibility(View.GONE);
                    intakeTimeText3.setVisibility(View.GONE);
                } else if (Integer.parseInt(times.get(i)) == 2) {
                    intakeTimeText1.setVisibility(View.VISIBLE);
                    intakeTimeText2.setVisibility(View.VISIBLE);
                    intakeTimeText3.setVisibility(View.GONE);
                } else if (Integer.parseInt(times.get(i)) == 3) {
                    intakeTimeText1.setVisibility(View.VISIBLE);
                    intakeTimeText2.setVisibility(View.VISIBLE);
                    intakeTimeText3.setVisibility(View.VISIBLE);
                }
                dialog.dismiss();
            }
        });
        headerCloseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

//        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 7) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getStringExtra("SelectedDays") != null) {
                    freqTextView.setText(data.getStringExtra("SelectedDays"));
                    selectedFrequency = data.getStringExtra("SelectedDays");
                } else if (data.getStringExtra("SelectedFreq") != null) {
                    freqTextView.setText(data.getStringExtra("SelectedFreq"));
                    selectedFrequency = data.getStringExtra("SelectedFreq");
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    /**
     * Passes the required data to the AlarmReceiver to create a notification.
     */
    public void setAlarm(NewMedModel task) {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        intent.putExtra("title", task.getMedName());
        intent.putExtra("time_stamp", task.getIntakeTime1());
        Log.e("intaketime",task.getIntakeTime1()+"");

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, task.getIntakeTime1(), pendingIntent);
    }


    public void setAlarms(NewMedModel task){
        int loopCount = (int)calculateDays(task.getStartDate(),task.getEndDate());
        List<String> freqList =findFrequency(task.getFrequency());

        long time1=task.getIntakeTime1();
        long time2=task.getIntakeTime2();
         long time3=task.getIntakeTime3();
         String cDay1=getDayFromDateString(time1);
         String cDay2=getDayFromDateString(time2);
         String cDay3=getDayFromDateString(time3);
        int alarmCount=0;

        if(selectedFrequency=="") {
            for (int i = 0; i <= loopCount; i++) {

//            if(freqList.contains(getDayFromDateString(System.currentTimeMillis()))){
                if(task.getIntakeTime1()!=0){
                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    intent.putExtra("title", task.getMedName());
                    intent.putExtra("time_stamp", task.getcID());
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, time1, pendingIntent);
                    time1=time1+AlarmManager.INTERVAL_DAY;
                    alarmCount++;
//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,task.getIntakeTime1(),AlarmManager.INTERVAL_DAY,pendingIntent);
                }
//
                if(task.getIntakeTime2()!=0){
                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    intent.putExtra("title", task.getMedName());
                    intent.putExtra("time_stamp", task.getcID());
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, time2, pendingIntent);
                    time2=time2+AlarmManager.INTERVAL_DAY;
                    alarmCount++;
                }
//
                if(task.getIntakeTime3()!=0){
                    Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                    intent.putExtra("title", task.getMedName());
                    intent.putExtra("time_stamp", task.getcID());
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, time3, pendingIntent);
                    time3=time3+AlarmManager.INTERVAL_DAY;
                    alarmCount++;
                }

            }
        }else {

            String freq="";
            boolean isDays=false;
            for(int i=0;i<freqList.size();i++){
                freq=freq+freqList.get(i)+",";
            }Log.e("Freq",freq);
            if(freq.contains(",")){ isDays=true; }else {isDays=false;}

            if (isDays) {
            for (int i = 0; i <= loopCount; i++) {


                    if (freq.contains(cDay1)) {

                        if (task.getIntakeTime1() != 0) {
                            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                            intent.putExtra("title", task.getMedName());
                            intent.putExtra("time_stamp", task.getcID());
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmManager.set(AlarmManager.RTC_WAKEUP, time1, pendingIntent);

                            alarmCount++;
//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,task.getIntakeTime1(),AlarmManager.INTERVAL_DAY,pendingIntent);
                        }
//
                        if (task.getIntakeTime2() != 0) {
                            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                            intent.putExtra("title", task.getMedName());
                            intent.putExtra("time_stamp", task.getcID());
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmManager.set(AlarmManager.RTC_WAKEUP, time2, pendingIntent);
//                            time2 = time2 + AlarmManager.INTERVAL_DAY;
//                            cDay2=getDayFromDateString(time2);
                            alarmCount++;
                        }
//
                        if (task.getIntakeTime3() != 0) {
                            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                            intent.putExtra("title", task.getMedName());
                            intent.putExtra("time_stamp", task.getcID());
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmManager.set(AlarmManager.RTC_WAKEUP, time3, pendingIntent);
//                            time3 = time3 + AlarmManager.INTERVAL_DAY;
//                            cDay3=getDayFromDateString(time3);
                            alarmCount++;
                        }
                    }
                time1 = time1 + AlarmManager.INTERVAL_DAY;
                cDay1=getDayFromDateString(time1);
                if(time2!=0){ time2 = time2 + AlarmManager.INTERVAL_DAY;}
                if(time3!=0){ time3 = time3 + AlarmManager.INTERVAL_DAY;}


                }
            }else {
                int every=Integer.parseInt(freq);
                Log.e("every",every+"");

                long statDate=task.getStartDate();
                for (int i = 0; i <= loopCount; i++) {

                    if (task.getIntakeTime1() != 0) {
                        if(statDate<task.getEndDate()) {
                            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                            intent.putExtra("title", task.getMedName());
                            intent.putExtra("time_stamp", task.getcID());
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmManager.set(AlarmManager.RTC_WAKEUP, time1, pendingIntent);
                            time1 = time1 + every*AlarmManager.INTERVAL_DAY;
                            statDate=time1;
//                            cDay1 = getDayFromDateString(time1);
                            alarmCount++;

                        }
//                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,task.getIntakeTime1(),AlarmManager.INTERVAL_DAY,pendingIntent);
                    }
//
                    if (task.getIntakeTime2() != 0) {
                        if(statDate<task.getEndDate()) {
                            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                            intent.putExtra("title", task.getMedName());
                            intent.putExtra("time_stamp", task.getcID());
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmManager.set(AlarmManager.RTC_WAKEUP, time2, pendingIntent);
                            time2 = time2 + AlarmManager.INTERVAL_DAY;
                            statDate=time2;
//                            cDay2=getDayFromDateString(time2);
                            alarmCount++;
                        }
                    }
//
                    if (task.getIntakeTime3() != 0) {
                        if(statDate<task.getEndDate()) {
                            Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
                            intent.putExtra("title", task.getMedName());
                            intent.putExtra("time_stamp", task.getcID());
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) System.currentTimeMillis(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            alarmManager.set(AlarmManager.RTC_WAKEUP, time3, pendingIntent);
                            time3 = time3 + AlarmManager.INTERVAL_DAY;
//                            cDay3=getDayFromDateString(time3);
                            statDate=time3;
                            alarmCount++;
                        }
                    }
                }
            }

        }

        Log.e("AlarmCount",alarmCount+"");
    }

    public long calculateDays(long sDate,long eDate){
        long msDiff = eDate - sDate;
        long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
        Log.e("days",daysDiff+"");
        return daysDiff;
    }

    public List<String> findFrequency(String frequency){
        List<String> freqList= new ArrayList<>();
        if(frequency.contains(",")){
            freqList = Arrays.asList(frequency.split(","));
        }else {
            freqList.add(frequency.replaceAll("[^0-9]", ""));
        }

        return freqList;
    }

    public static String getDayFromDateString(long timeinmilli)
    {
        String[] daysArray = new String[] {"Sat","Sun","Mon","Tue","Wed","Thu","Fri"};
        String day = "";

        int dayOfWeek =0;

        try {

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(timeinmilli);
            dayOfWeek = c.get(Calendar.DAY_OF_WEEK)-1;
            if (dayOfWeek < 0) {
                dayOfWeek += 7;
            }
            day = daysArray[dayOfWeek];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return day;
    }
}
