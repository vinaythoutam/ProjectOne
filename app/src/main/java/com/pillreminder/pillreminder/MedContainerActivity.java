package com.pillreminder.pillreminder;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pillreminder.pillreminder.adapters.HomeListAdapter;
import com.pillreminder.pillreminder.adapters.IntakeListAdapter;
import com.pillreminder.pillreminder.adapters.MedListAdapter;
import com.pillreminder.pillreminder.database.DatabaseHandler;
import com.pillreminder.pillreminder.helper.ReusableCode;
import com.pillreminder.pillreminder.helper.Singleton;
import com.pillreminder.pillreminder.model.NewMedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 10/8/2018.
 */

public class MedContainerActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout homeLL, intakeLL, reportLL, medContainerLL, moreLL;

    ImageView addMed;

    List<NewMedModel> newMedList;
    DatabaseHandler dbHandler;
    ListView medContinerLV;
    LinearLayout noDataL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medcontainer_activity);

        getSupportActionBar().hide();

        Singleton.Instance().setFlow("");
        homeLL = (LinearLayout) findViewById(R.id.home_bottom);
        intakeLL = (LinearLayout) findViewById(R.id.intake_bottom);
        reportLL = (LinearLayout) findViewById(R.id.report_bottom);
        medContainerLL = (LinearLayout) findViewById(R.id.medContainer_bottom);
        moreLL = (LinearLayout) findViewById(R.id.more_bottom);
        medContinerLV=(ListView)findViewById(R.id.listviewMed);
        noDataL=(LinearLayout)findViewById(R.id.noDataLL);


        addMed=(ImageView)findViewById(R.id.toolImage);
        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Singleton.Instance().setFlow("MedContainer");
//                startActivity(new Intent(MedContainerActivity.this, NewMedications.class));
                showAddMedPopup(MedContainerActivity.this);
            }
        });

        homeLL.setOnClickListener(this);
        intakeLL.setOnClickListener(this);
        reportLL.setOnClickListener(this);
        medContainerLL.setOnClickListener(this);
        moreLL.setOnClickListener(this);

        newMedList = new ArrayList<>();

        dbHandler= new DatabaseHandler(this);
        newMedList = dbHandler.getAllMedData();

        MedListAdapter medListAdapter;
        if(newMedList!=null && newMedList.size()>0) {
            Log.e("IF","IF");
            medContinerLV.setVisibility(View.VISIBLE);
            noDataL.setVisibility(View.GONE);
            medListAdapter = new MedListAdapter(MedContainerActivity.this, newMedList);
            medContinerLV.setAdapter(medListAdapter);
        }else {
            Log.e("else","else");
            noDataL.setVisibility(View.VISIBLE);
            medContinerLV.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_bottom:
                startActivity(new Intent(MedContainerActivity.this,HomeActivity.class));
                finish();
            break;

            case R.id.intake_bottom :
                startActivity(new Intent(MedContainerActivity.this,IntakeActivity.class));
                finish();
                break;

            case R.id.report_bottom:
                startActivity(new Intent(MedContainerActivity.this,ReportActivity.class));
                finish();
                break;

            case R.id.medContainer_bottom:
                startActivity(new Intent(MedContainerActivity.this,MedContainerActivity.class));
                finish();
                break;

            case R.id.more_bottom:
                startActivity(new Intent(MedContainerActivity.this,MoreActivity.class));

                break;

        }
    }

    public static void showAddMedPopup(final Context context) {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_med_popup);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        DisplayMetrics mertics = context.getResources().getDisplayMetrics();
        int width = mertics.widthPixels;

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.TOP;
        wlp.y=90;
        wlp.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        LinearLayout addMed= (LinearLayout)dialog.findViewById(R.id.addMedLL);
        LinearLayout bpL= (LinearLayout)dialog.findViewById(R.id.bpLL);

        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Singleton.Instance().setFlow("MedContainer");
                context.startActivity(new Intent(context, NewMedications.class));
                dialog.dismiss();
            }
        });

        bpL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,AddMeasurements.class);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

}
