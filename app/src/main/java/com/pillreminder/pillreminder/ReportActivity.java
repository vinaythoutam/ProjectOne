package com.pillreminder.pillreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by LENOVO on 10/8/2018.
 */

public class ReportActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout homeLL, intakeLL, reportLL, medContainerLL, moreLL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_activity);

        getSupportActionBar().hide();

        homeLL = (LinearLayout) findViewById(R.id.home_bottom);
        intakeLL = (LinearLayout) findViewById(R.id.intake_bottom);
        reportLL = (LinearLayout) findViewById(R.id.report_bottom);
        medContainerLL = (LinearLayout) findViewById(R.id.medContainer_bottom);
        moreLL = (LinearLayout) findViewById(R.id.more_bottom);

        homeLL.setOnClickListener(this);
        intakeLL.setOnClickListener(this);
        reportLL.setOnClickListener(this);
        medContainerLL.setOnClickListener(this);
        moreLL.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_bottom:
                startActivity(new Intent(ReportActivity.this,HomeActivity.class));
                finish();
                break;

            case R.id.intake_bottom :
                startActivity(new Intent(ReportActivity.this,IntakeActivity.class));
                finish();
                break;

            case R.id.report_bottom:
                startActivity(new Intent(ReportActivity.this,ReportActivity.class));
                finish();
                break;

            case R.id.medContainer_bottom:
                startActivity(new Intent(ReportActivity.this,MedContainerActivity.class));
                finish();
                break;

            case R.id.more_bottom:
                startActivity(new Intent(ReportActivity.this,MoreActivity.class));

                break;

        }
    }

}
