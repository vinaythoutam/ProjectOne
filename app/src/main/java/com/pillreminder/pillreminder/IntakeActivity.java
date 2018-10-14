package com.pillreminder.pillreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.pillreminder.pillreminder.adapters.IntakeListAdapter;
import com.pillreminder.pillreminder.database.DatabaseHandler;
import com.pillreminder.pillreminder.model.NewMedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 10/6/2018.
 */

public class IntakeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout homeLL, intakeLL, reportLL, medContainerLL, moreLL;

    ListView intakeLV;

    DatabaseHandler dbHandler;

    List<NewMedModel> missedIntakeList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intake_activity);

        dbHandler = new DatabaseHandler(this);

        getSupportActionBar().hide();
        homeLL = (LinearLayout) findViewById(R.id.home_bottom);
        intakeLL = (LinearLayout) findViewById(R.id.intake_bottom);
        reportLL = (LinearLayout) findViewById(R.id.report_bottom);
        medContainerLL = (LinearLayout) findViewById(R.id.medContainer_bottom);
        moreLL = (LinearLayout) findViewById(R.id.more_bottom);
        intakeLV = (ListView) findViewById(R.id.listview_intake);

        homeLL.setOnClickListener(this);
        intakeLL.setOnClickListener(this);
        reportLL.setOnClickListener(this);
        medContainerLL.setOnClickListener(this);
        moreLL.setOnClickListener(this);

//        onNewIntent(getIntent());

        List<NewMedModel> newMedList= new ArrayList<>();
        missedIntakeList = dbHandler.getAllMissedIntake();
        for(int i=0;i<missedIntakeList.size();i++){
            if(missedIntakeList.get(i).getStatus().equals("")){
                newMedList.add(missedIntakeList.get(i));
            }
        }
        IntakeListAdapter intakeListAdapter = new IntakeListAdapter(this, newMedList);
        intakeLV.setAdapter(intakeListAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_bottom:
                startActivity(new Intent(IntakeActivity.this, HomeActivity.class));
                finish();
                break;

            case R.id.intake_bottom:
                startActivity(new Intent(IntakeActivity.this, IntakeActivity.class));
                finish();
                break;

            case R.id.report_bottom:
                startActivity(new Intent(IntakeActivity.this, ReportActivity.class));
                finish();
                break;

            case R.id.medContainer_bottom:
                startActivity(new Intent(IntakeActivity.this, MedContainerActivity.class));
                finish();
                break;

            case R.id.more_bottom:
                startActivity(new Intent(IntakeActivity.this, MoreActivity.class));

                break;

        }
    }

//    @Override
//    public void onNewIntent(Intent intent){
////        Bundle extras = intent.getExtras();
//        int time_stamp;
//
//        Log.e("TEMP", "Tab Number: " + intent.getIntExtra("cid",0));
//
//        if(intent.getIntExtra("cid",0)>0) {
//            nm = dbHandler.getSingleIntake(intent.getIntExtra("cid", 0));
////        nm= dbHandler.getAllMedData();
//            Log.e("Intake List size", nm.size() + "");
//
//            IntakeListAdapter intakeListAdapter = new IntakeListAdapter(this, nm);
//            intakeLV.setAdapter(intakeListAdapter);
//        }
//
//    }
}
