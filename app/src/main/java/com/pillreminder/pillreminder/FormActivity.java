package com.pillreminder.pillreminder;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.pillreminder.pillreminder.adapters.FormListAdapter;
import com.pillreminder.pillreminder.adapters.PagerAdapter;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView toolImageView = (ImageView) findViewById(R.id.toolImage);

        listView=(ListView)findViewById(R.id.formListView);



        final ArrayList<String> names= new ArrayList<>();
        names.add("Capsule");
        names.add("Pill");
        names.add("Procedure");
        names.add("Injection");
        names.add("Drops");
        names.add("Mixture");
        names.add("Ointment/ Cream/ Gel");
        names.add("Teaspoon");
        names.add("Patch");
        names.add("Spray");
        names.add("Puffs");
        names.add("Pieces");

        final ArrayList<Integer> imageIDs= new ArrayList<>();
        imageIDs.add(R.drawable.capsule);
        imageIDs.add(R.drawable.pill);
        imageIDs.add(R.drawable.procedure);
        imageIDs.add(R.drawable.injection);
        imageIDs.add(R.drawable.drops);
        imageIDs.add(R.drawable.mixture);
        imageIDs.add(R.drawable.ointment);
        imageIDs.add(R.drawable.teaspoon);
        imageIDs.add(R.drawable.patch);
        imageIDs.add(R.drawable.spray);
        imageIDs.add(R.drawable.puffs);
        imageIDs.add(R.drawable.pieces);

        final ArrayList<String> imageTags= new ArrayList<String>();
        imageTags.add("capsule");
        imageTags.add("pill");
        imageTags.add("procedure");
        imageTags.add("injection");
        imageTags.add("drops");
        imageTags.add("mixture");
        imageTags.add("ointment");
        imageTags.add("teaspoon");
        imageTags.add("patch");
        imageTags.add("spray");
        imageTags.add("puffs");
        imageTags.add("pieces");

        FormListAdapter formListAdapter= new FormListAdapter(this,names,imageIDs,imageTags);

        listView.setAdapter(formListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent returnIntent = new Intent();
                returnIntent.putExtra("name",names.get(i));
                returnIntent.putExtra("image",imageIDs.get(i));
                returnIntent.putExtra("Tag",imageTags.get(i));
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }

    /**
     * Created by LENOVO on 10/8/2018.
     */

    public static class FrequencyActivity extends AppCompatActivity {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.frequency_activity);

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

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
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }

        private void setSupportActionBar(android.widget.Toolbar toolbar) {
        }

    }
}
