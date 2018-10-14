package com.pillreminder.pillreminder.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pillreminder.pillreminder.fragments.DaysFragment;
import com.pillreminder.pillreminder.fragments.FrequencyFragment;

/**
 * Created by LENOVO on 8/17/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                DaysFragment tab1 = new DaysFragment();
                return tab1;
            case 1:
                FrequencyFragment tab2 = new FrequencyFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}