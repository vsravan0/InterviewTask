package miles.driver.interviewtask.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import miles.driver.interviewtask.R;
import miles.driver.interviewtask.apputils.Constants;
import miles.driver.interviewtask.apputils.LocationUpdateServices;


public class FragmentLocation extends Fragment {

    private TextView mTv;

 private View view;
    private MyReceiver myReceiver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.layout_location,container,false);
        mTv=(TextView)view.findViewById(R.id.id_location_longtitude);

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
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(myReceiver);
    }

    private class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context arg0, Intent arg1) {
            Location location = (Location) arg1.getParcelableExtra("DATAPASSED");
            mTv.setText(" Location :" + location.getLatitude());
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
