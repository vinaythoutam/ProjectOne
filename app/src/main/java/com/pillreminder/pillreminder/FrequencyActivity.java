package com.pillreminder.pillreminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.pillreminder.pillreminder.adapters.PagerAdapter;
import com.pillreminder.pillreminder.fragments.DaysFragment;
import com.pillreminder.pillreminder.fragments.FrequencyFragment;

import java.util.Calendar;

/**
 * Created by LENOVO on 10/8/2018.
 */

public class FrequencyActivity extends AppCompatActivity {

    public static boolean isDays=true;
    public static boolean isFreq=false;
    static String selctedDays="";
    static String selectedFreq="";
    TextView freqDone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frequency_activity);

        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        selctedDays="";
        selectedFreq="";
        TabLayout tabLayout = (TabLayout)findViewById(R.id.htab_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Days"));
        tabLayout.addTab(tabLayout.newTab().setText("Frequency"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount() );
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.e("position",tab.getPosition()+"");

                if(tab.getPosition()==0){
                    isDays=true;
                    isFreq=false;
                }else {
                    isDays=false;
                    isFreq=true;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        freqDone=(TextView)findViewById(R.id.freqDoneBtn);
        freqDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDays){
                    selctedDays="";
                    if(DaysFragment.isMondayCkd){selctedDays=selctedDays+ "Monday,";}
                    if(DaysFragment.isTuesdayCkd){selctedDays=selctedDays+ "Tuesday,";}
                    if(DaysFragment.isWednesdayCkd){selctedDays=selctedDays+ "Wednesday,";}
                    if(DaysFragment.isThursdayCkd){selctedDays=selctedDays+ "Thursday,";}
                    if(DaysFragment.isFridayCkd){selctedDays=selctedDays+ "Friday,";}
                    if(DaysFragment.isSaturdayCkd){selctedDays=selctedDays+ "Saturday,";}
                    if(DaysFragment.isSundayCkd){selctedDays=selctedDays+ "Sunday,";}

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("SelectedDays",selctedDays);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }else if(isFreq){
                    selectedFreq= FrequencyFragment.selectedFrequency;
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("SelectedFreq",selectedFreq);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }

            }
        });

    }

    private void setSupportActionBar(android.widget.Toolbar toolbar) {
    }

}
