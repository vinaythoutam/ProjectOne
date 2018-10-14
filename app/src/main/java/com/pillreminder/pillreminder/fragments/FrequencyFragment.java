package com.pillreminder.pillreminder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.pillreminder.pillreminder.R;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * Created by LENOVO on 10/3/2018.
 */

public class FrequencyFragment extends Fragment implements View.OnClickListener {

    //    ListView simpleList;
//    String[] itemname2 = {"Every 2 days",
//            "Every 3 days",
//            "Every 4 days",
//            "Every 5 days",
//            "Every 6 days",
//            "Every 7 days",
//            "Every 8 days"};
//    Integer[] imgid2 = {R.drawable.ic_assessment_black_24dp,
//            R.drawable.ic_assessment_black_24dp,
//            R.drawable.ic_assessment_black_24dp,
//            R.drawable.ic_assessment_black_24dp,
//            R.drawable.ic_assessment_black_24dp,
//            R.drawable.ic_assessment_black_24dp,
//            R.drawable.ic_assessment_black_24dp};
    Context context;
    TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7;

    public static String selectedFrequency;

    public FrequencyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frequency_fragment, container, false);

//        simpleList = (ListView)view.findViewById(R.id.list2);
//        CustomAdapter2 customAdapter2 = new CustomAdapter2 (getActivity(), itemname2, imgid2);
//        simpleList.setAdapter(customAdapter2);

        textView1 = (TextView) view.findViewById(R.id.everytext1);
        textView2 = (TextView) view.findViewById(R.id.everytext2);
        textView3 = (TextView) view.findViewById(R.id.everytext3);
        textView4 = (TextView) view.findViewById(R.id.everytext4);
        textView5 = (TextView) view.findViewById(R.id.everytext5);
        textView6 = (TextView) view.findViewById(R.id.everytext6);
        textView7 = (TextView) view.findViewById(R.id.everytext7);

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        textView5.setOnClickListener(this);
        textView6.setOnClickListener(this);
        textView7.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.everytext1:
                textView1.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView2.setTextColor(getResources().getColor(R.color.black));
                textView3.setTextColor(getResources().getColor(R.color.black));
                textView4.setTextColor(getResources().getColor(R.color.black));
                textView5.setTextColor(getResources().getColor(R.color.black));
                textView6.setTextColor(getResources().getColor(R.color.black));
                textView7.setTextColor(getResources().getColor(R.color.black));
                selectedFrequency=textView1.getText().toString();
             break;

            case R.id.everytext2:
                textView1.setTextColor(getResources().getColor(R.color.black));
                textView2.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView3.setTextColor(getResources().getColor(R.color.black));
                textView4.setTextColor(getResources().getColor(R.color.black));
                textView5.setTextColor(getResources().getColor(R.color.black));
                textView6.setTextColor(getResources().getColor(R.color.black));
                textView7.setTextColor(getResources().getColor(R.color.black));
                selectedFrequency=textView2.getText().toString();
                break;
            case R.id.everytext3:
                textView1.setTextColor(getResources().getColor(R.color.black));
                textView2.setTextColor(getResources().getColor(R.color.black));
                textView3.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView4.setTextColor(getResources().getColor(R.color.black));
                textView5.setTextColor(getResources().getColor(R.color.black));
                textView6.setTextColor(getResources().getColor(R.color.black));
                textView7.setTextColor(getResources().getColor(R.color.black));
                selectedFrequency=textView3.getText().toString();
                break;
            case R.id.everytext4:
                textView1.setTextColor(getResources().getColor(R.color.black));
                textView2.setTextColor(getResources().getColor(R.color.black));
                textView3.setTextColor(getResources().getColor(R.color.black));
                textView4.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView5.setTextColor(getResources().getColor(R.color.black));
                textView6.setTextColor(getResources().getColor(R.color.black));
                textView7.setTextColor(getResources().getColor(R.color.black));
                selectedFrequency=textView4.getText().toString();
                break;
            case R.id.everytext5:
                textView1.setTextColor(getResources().getColor(R.color.black));
                textView2.setTextColor(getResources().getColor(R.color.black));
                textView3.setTextColor(getResources().getColor(R.color.black));
                textView4.setTextColor(getResources().getColor(R.color.black));
                textView5.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView6.setTextColor(getResources().getColor(R.color.black));
                textView7.setTextColor(getResources().getColor(R.color.black));
                selectedFrequency=textView5.getText().toString();
                break;
            case R.id.everytext6:
                textView1.setTextColor(getResources().getColor(R.color.black));
                textView2.setTextColor(getResources().getColor(R.color.black));
                textView3.setTextColor(getResources().getColor(R.color.black));
                textView4.setTextColor(getResources().getColor(R.color.black));
                textView5.setTextColor(getResources().getColor(R.color.black));
                textView6.setTextColor(getResources().getColor(R.color.colorPrimary));
                textView7.setTextColor(getResources().getColor(R.color.black));
                selectedFrequency=textView6.getText().toString();
                break;
            case R.id.everytext7:
                textView1.setTextColor(getResources().getColor(R.color.black));
                textView2.setTextColor(getResources().getColor(R.color.black));
                textView3.setTextColor(getResources().getColor(R.color.black));
                textView4.setTextColor(getResources().getColor(R.color.black));
                textView5.setTextColor(getResources().getColor(R.color.black));
                textView6.setTextColor(getResources().getColor(R.color.black));
                textView7.setTextColor(getResources().getColor(R.color.colorPrimary));
                selectedFrequency=textView7.getText().toString();
                break;
          }
       }
    }

