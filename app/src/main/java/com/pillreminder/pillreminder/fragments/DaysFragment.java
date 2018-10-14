package com.pillreminder.pillreminder.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pillreminder.pillreminder.FrequencyActivity;
import com.pillreminder.pillreminder.R;

/**
 * Created by LENOVO on 10/3/2018.
 */

public class DaysFragment extends Fragment implements View.OnClickListener {

    LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5,
            linearLayout6, linearLayout7;
    TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7;
    public static boolean isMondayCkd = false;
    public static boolean isTuesdayCkd = false;
    public static boolean isWednesdayCkd = false;
    public static boolean isThursdayCkd = false;
    public static boolean isFridayCkd = false;
    public static boolean isSaturdayCkd = false;
    public static boolean isSundayCkd = false;

    public DaysFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.days_fragment, container, false);

//        simpleList = (ListView)view.findViewById(R.id.list1);
//        CustomAdapter1 customAdapter1 = new CustomAdapter1 (getActivity(), itemname1, imgid1);
//        simpleList.setAdapter(customAdapter1);

        linearLayout1 = (LinearLayout) view.findViewById(R.id.layout1);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.layout2);
        linearLayout3 = (LinearLayout) view.findViewById(R.id.layout3);
        linearLayout4 = (LinearLayout) view.findViewById(R.id.layout4);
        linearLayout5 = (LinearLayout) view.findViewById(R.id.layout5);
        linearLayout6 = (LinearLayout) view.findViewById(R.id.layout6);
        linearLayout7 = (LinearLayout) view.findViewById(R.id.layout7);

        imageView1 = (ImageView) view.findViewById(R.id.image1);
        imageView2 = (ImageView) view.findViewById(R.id.image2);
        imageView3 = (ImageView) view.findViewById(R.id.image3);
        imageView4 = (ImageView) view.findViewById(R.id.image4);
        imageView5 = (ImageView) view.findViewById(R.id.image5);
        imageView6 = (ImageView) view.findViewById(R.id.image6);
        imageView7 = (ImageView) view.findViewById(R.id.image7);

        monday = (TextView) view.findViewById(R.id.textmonday);
        tuesday = (TextView) view.findViewById(R.id.texttuesday);
        wednesday = (TextView) view.findViewById(R.id.textwednesday);
        thursday = (TextView) view.findViewById(R.id.textthursday);
        friday = (TextView) view.findViewById(R.id.textfriday);
        saturday = (TextView) view.findViewById(R.id.textsaturday);
        sunday = (TextView) view.findViewById(R.id.textsunday);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        linearLayout5.setOnClickListener(this);
        linearLayout6.setOnClickListener(this);
        linearLayout7.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout1:
                if (!isMondayCkd) {
                    imageView1.setVisibility(View.VISIBLE);
                    isMondayCkd = true;
                    FrequencyActivity.isDays = true;
                    FrequencyActivity.isFreq = false;
                } else {
                    imageView1.setVisibility(View.GONE);
                    isMondayCkd = false;
                }
                break;
            case R.id.layout2:
                if (!isTuesdayCkd) {
                    imageView2.setVisibility(View.VISIBLE);
                    isTuesdayCkd = true;
                    FrequencyActivity.isDays = true;
                    FrequencyActivity.isFreq = false;

                } else {
                    imageView2.setVisibility(View.GONE);
                    isTuesdayCkd = false;
                }
                break;
            case R.id.layout3:
                if (!isWednesdayCkd) {
                    imageView3.setVisibility(View.VISIBLE);
                    isWednesdayCkd = true;
                    FrequencyActivity.isDays = true;
                    FrequencyActivity.isFreq = false;
                } else {
                    imageView3.setVisibility(View.GONE);
                    isWednesdayCkd = false;
                }
                break;
            case R.id.layout4:
                if (!isThursdayCkd) {
                    imageView4.setVisibility(View.VISIBLE);
                    isThursdayCkd = true;
                    FrequencyActivity.isDays = true;
                    FrequencyActivity.isFreq = false;
                } else {
                    imageView4.setVisibility(View.GONE);
                    isThursdayCkd = false;
                }
                break;
            case R.id.layout5:
                if (!isFridayCkd) {
                    imageView5.setVisibility(View.VISIBLE);
                    isFridayCkd = true;
                    FrequencyActivity.isDays = true;
                    FrequencyActivity.isFreq = false;
                } else {
                    imageView5.setVisibility(View.GONE);
                    isFridayCkd = false;
                }
                break;
            case R.id.layout6:
                if (!isSaturdayCkd) {
                    imageView6.setVisibility(View.VISIBLE);
                    isSaturdayCkd = true;
                    FrequencyActivity.isDays = true;
                    FrequencyActivity.isFreq = false;
                } else {
                    imageView6.setVisibility(View.GONE);
                    isSaturdayCkd = false;
                }
                break;
            case R.id.layout7:
                if (!isSundayCkd) {
                    imageView7.setVisibility(View.VISIBLE);
                    isSundayCkd = true;
                    FrequencyActivity.isDays = true;
                    FrequencyActivity.isFreq = false;
                } else {
                    imageView7.setVisibility(View.GONE);
                    isSundayCkd = false;
                }
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isMondayCkd = false;
        isTuesdayCkd = false;
        isWednesdayCkd = false;
        isThursdayCkd = false;
        isFridayCkd = false;
        isSaturdayCkd = false;
        isSundayCkd = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isMondayCkd = false;
        isTuesdayCkd = false;
        isWednesdayCkd = false;
        isThursdayCkd = false;
        isFridayCkd = false;
        isSaturdayCkd = false;
        isSundayCkd = false;
    }
}