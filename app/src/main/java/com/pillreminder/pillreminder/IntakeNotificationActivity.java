package com.pillreminder.pillreminder;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pillreminder.pillreminder.database.DatabaseHandler;
import com.pillreminder.pillreminder.helper.ReusableCode;
import com.pillreminder.pillreminder.model.NewMedModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IntakeNotificationActivity extends AppCompatActivity {

    int cid;
    TextView medName, medCount, intakeTime, intakeTime2, intakeTime3;
    ImageView medIcon, close;
    LinearLayout delayL, skipL, takeL;
    DatabaseHandler db;
    NewMedModel nm;
    List<NewMedModel> newModel = new ArrayList<>();

    long skipLong=0,takeLong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intake_notification);

        cid = getIntent().getIntExtra("cid", 0);

        medName = (TextView) findViewById(R.id.medNameTV);
        medCount = (TextView) findViewById(R.id.medCountTV);
        intakeTime = (TextView) findViewById(R.id.intakeTimeTV);
        intakeTime2 = (TextView) findViewById(R.id.intakeTimeTV2);
        intakeTime3 = (TextView) findViewById(R.id.intakeTimeTV3);
        medIcon = (ImageView) findViewById(R.id.medIconIV);
        close = (ImageView) findViewById(R.id.close_intake);

        delayL = (LinearLayout) findViewById(R.id.delayLL);
        skipL = (LinearLayout) findViewById(R.id.skipLL);
        takeL = (LinearLayout) findViewById(R.id.takeLL);

        db = new DatabaseHandler(this);
        newModel = db.getSingleIntake(cid);

        nm = newModel.get(0);

        medName.setText(nm.getMedName());
        medCount.setText(nm.getDosageCount() + " " + nm.getDosageType());
        intakeTime.setText("Intake today at " + nm.getTime1());
        if (!nm.getTime2().equals("")) {
            intakeTime2.setVisibility(View.VISIBLE);
            intakeTime2.setText(nm.getTime2());
        }
        if (!nm.getTime3().equals("")) {
            intakeTime3.setVisibility(View.VISIBLE);
            intakeTime3.setText(nm.getTime3());
        }
        String PACKAGE_NAME = getPackageName();
        int imgId = getResources().getIdentifier(PACKAGE_NAME + ":drawable/" + nm.getMedImageID(), null, null);
        medIcon.setImageBitmap(BitmapFactory.decodeResource(getResources(), imgId));

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        delayL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        skipL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nm.setSkip_time(ReusableCode.getFullDate(Calendar.getInstance().getTimeInMillis()));
                nm.setSkip_date(ReusableCode.getDate(Calendar.getInstance().getTimeInMillis()));
                 skipLong = db.insSkipValues(nm);
                Log.e("skipintake",skipLong+"");
                finishAffinity();
            }
        });

        takeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nm.setTaken_time(ReusableCode.getFullDate(Calendar.getInstance().getTimeInMillis()));
                nm.setTaken_date(ReusableCode.getDate(Calendar.getInstance().getTimeInMillis()));
                 takeLong = db.insTakeValues(nm);
                Log.e("skipintake",takeLong+"");
                finishAffinity();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("OnDestroy","OnDestroy");
        if(skipLong==0 && takeLong==0) {
            long n = db.insMissedIntake(nm);
            Log.e("Missed Intake", n + "");
        }

    }

}
