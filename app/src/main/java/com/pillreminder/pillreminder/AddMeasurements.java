package com.pillreminder.pillreminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMeasurements extends AppCompatActivity {

    TextView saveAction;
    LinearLayout systolicL,diastolicL,dateL,timeL;
    EditText systolicEdit,diastolicEdit,notesEdit;
    TextView dateText,timeText;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener datE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_measurements);

        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView toolImageView = (ImageView) findViewById(R.id.toolImage);

        toolImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveAction=(TextView)findViewById(R.id.saveAction);
        systolicL=(LinearLayout)findViewById(R.id.systolicLL);
        diastolicL=(LinearLayout)findViewById(R.id.diastolicLL);
        dateL=(LinearLayout)findViewById(R.id.dateLL);
        timeL=(LinearLayout)findViewById(R.id.timeLL);

        systolicEdit=(EditText)findViewById(R.id.systolicET);
        diastolicEdit=(EditText)findViewById(R.id.diastolicET);
        notesEdit=(EditText)findViewById(R.id.notesET);

        dateText=(TextView)findViewById(R.id.dateTV);
        timeText=(TextView)findViewById(R.id.timeTV);

        saveAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        myCalendar = Calendar.getInstance();
        datE = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStartDate();
            }

        };
        dateL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddMeasurements.this, R.style.DialogTheme, datE, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        timeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddMeasurements.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
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
                        timeText.setText(hour + ":" + min + " " + AM_PM);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.show();
            }
        });

    }

    private void updateLabelStartDate() {
        String myFormat = "dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateText.setText(sdf.format(myCalendar.getTime()));
        Log.e("DateString",dateFormat.format(myCalendar.getTime()));
    }
}
