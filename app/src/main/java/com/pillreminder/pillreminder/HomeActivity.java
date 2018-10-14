package com.pillreminder.pillreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;

import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.pillreminder.pillreminder.adapters.HomeListAdapter;
import com.pillreminder.pillreminder.database.DatabaseHandler;
import com.pillreminder.pillreminder.helper.ReusableCode;
import com.pillreminder.pillreminder.model.NewMedModel;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements DatePickerListener, View.OnClickListener {

    LinearLayout homeLL, intakeLL, reportLL, medContainerLL, moreLL,noDataL;

    DatabaseHandler dbHandler;
    List<NewMedModel> newMedList=new ArrayList<>();

    ListView listViewHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHandler=new DatabaseHandler(this);
        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView toolImageView = (ImageView) findViewById(R.id.toolImage);

        toolImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                startActivity(new Intent(HomeActivity.this, NewMedications.class));

            }
        });

        // find the picker
        HorizontalPicker picker = (HorizontalPicker) findViewById(R.id.datePicker);
        // initialize it and attach a listener
        picker.setListener(this).init();
        picker.setDate(DateTime.now());
        //find objects
        homeLL = (LinearLayout) findViewById(R.id.home_bottom);
        intakeLL = (LinearLayout) findViewById(R.id.intake_bottom);
        reportLL = (LinearLayout) findViewById(R.id.report_bottom);
        medContainerLL = (LinearLayout) findViewById(R.id.medContainer_bottom);
        moreLL = (LinearLayout) findViewById(R.id.more_bottom);
        noDataL = (LinearLayout) findViewById(R.id.noDataLL);
        listViewHome=(ListView)findViewById(R.id.recyclerview_home);

        homeLL.setOnClickListener(this);
        intakeLL.setOnClickListener(this);
        reportLL.setOnClickListener(this);
        medContainerLL.setOnClickListener(this);
        moreLL.setOnClickListener(this);


//        newMedList=dbHandler.getCdayMedModel(ReusableCode.getDate(System.currentTimeMillis()));
//        newMedList=dbHandler.getAllMedData();

//        Log.e("Size",dbHandler.getCdayMedModel(ReusableCode.getDate(System.currentTimeMillis()))+"");
//        listViewHome=(ListView)findViewById(R.id.recyclerview_home);
//        HomeListAdapter homeListAdapter;
//        if(newMedList!=null) {
//            listViewHome.setVisibility(View.VISIBLE);
//            noDataL.setVisibility(View.GONE);
//            homeListAdapter = new HomeListAdapter(HomeActivity.this, newMedList);
//            listViewHome.setAdapter(homeListAdapter);
//        }else {
//            noDataL.setVisibility(View.VISIBLE);
//            listViewHome.setVisibility(View.GONE);
//        }

    }

    @Override
    public void onDateSelected(DateTime dateSelected) {
        // log it for demo
        Log.i("HorizontalPicker", "Selected date is " + dateSelected.toString()+" "+ ReusableCode.convertDate(dateSelected.toString()));
        newMedList = new ArrayList<>();
        newMedList = dbHandler.getCdayMedModel( ReusableCode.convertDate(dateSelected.toString()));
//        newMedList = dbHandler.getAllMedData();

        HomeListAdapter homeListAdapter;
        if(newMedList!=null && newMedList.size()>0) {
            Log.e("IF","IF");
            listViewHome.setVisibility(View.VISIBLE);
            noDataL.setVisibility(View.GONE);
            homeListAdapter = new HomeListAdapter(HomeActivity.this, newMedList);
            listViewHome.setAdapter(homeListAdapter);
        }else {
            Log.e("else","else");
            noDataL.setVisibility(View.VISIBLE);
            listViewHome.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.home_bottom:
                startActivity(new Intent(HomeActivity.this,HomeActivity.class));
                finish();
                break;

            case R.id.intake_bottom :
                startActivity(new Intent(HomeActivity.this,IntakeActivity.class));
                finish();
                break;

            case R.id.report_bottom:
                startActivity(new Intent(HomeActivity.this,ReportActivity.class));
                finish();
                break;

            case R.id.medContainer_bottom:
                startActivity(new Intent(HomeActivity.this,MedContainerActivity.class));
                finish();
                break;

            case R.id.more_bottom:
                startActivity(new Intent(HomeActivity.this,MoreActivity.class));
                break;

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

//        newMedList = new ArrayList<>();
//        newMedList = dbHandler.getCdayMedModel( ReusableCode.convertDate(dateSelected.toString()));
////        newMedList = dbHandler.getAllMedData();
//
//        HomeListAdapter homeListAdapter;
//        if(newMedList!=null && newMedList.size()>0) {
//            Log.e("IF","IF");
//            listViewHome.setVisibility(View.VISIBLE);
//            noDataL.setVisibility(View.GONE);
//            homeListAdapter = new HomeListAdapter(HomeActivity.this, newMedList);
//            listViewHome.setAdapter(homeListAdapter);
//        }else {
//            Log.e("else","else");
//            noDataL.setVisibility(View.VISIBLE);
//            listViewHome.setVisibility(View.GONE);
//        }
    }
}
