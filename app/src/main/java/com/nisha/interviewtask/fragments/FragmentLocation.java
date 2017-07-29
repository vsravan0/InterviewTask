package com.nisha.interviewtask.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nisha.interviewtask.R;
import com.nisha.interviewtask.apputils.Constants;

public class FragmentLocation extends Fragment {

    private TextView mTv;

 private View view;
    private MyReceiver myReceiver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.layout_location,container,false);
        mTv=(TextView)view.findViewById(R.id.id_location);

        return view;


    }



    @Override
    public void onResume() {
        super.onResume();

if(myReceiver==null) {
    myReceiver = new MyReceiver();
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction(Constants.KEY_USER_LOC);
    getActivity().registerReceiver(myReceiver, intentFilter);

    //Start our own service

}

    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(myReceiver);
        myReceiver = null;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            Location location = (Location) arg1.getParcelableExtra("DATAPASSED");
            setText(location);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    private void setText(Location location){

        if(location!=null&& mTv!=null){
            double longitude= location.getLongitude();
            double latitude= location.getLatitude();
            double altitude= location.getAltitude();

            String text ="Location Info \n"
                    +" Longitude :"+longitude+" \n Latitude :"+latitude+"\n Altitude:"+altitude;
            mTv.setText(text);
        }






    }

}
