package com.nisha.interviewtask.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nisha.interviewtask.fragments.FragmentAnimation;
import com.nisha.interviewtask.fragments.FragmentGraph;
import com.nisha.interviewtask.fragments.FragmentLocation;

/**
 * Created by sravan on 23/07/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mTotalTabs;

    public PagerAdapter(FragmentManager manager, int totalTabs){
        super(manager);
        mTotalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return  new FragmentLocation();
            case 1 : return  new FragmentGraph();
            case 2 : return  new FragmentAnimation();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTotalTabs;
    }
}
