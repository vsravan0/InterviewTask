package com.nisha.interviewtask;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nisha.interviewtask.adapter.PagerAdapter;
import com.nisha.interviewtask.apputils.LocationUpdateServices;

public class ActivityMain extends AppCompatActivity  {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        mTabLayout = (TabLayout) findViewById(R.id.id_tab_layout);
        mViewPager =(ViewPager)findViewById(R.id.id_view_pager);
        mTabLayout.addTab(mTabLayout.newTab().setText("Location"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Chart"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Animation"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
       // mTabLayout.addOnTabSelectedListener(this);
        mPagerAdapter =  new PagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                toast(" pos "+tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        requestPermission();

    }


private final int MY_PERMISSIONS_REQUEST_LOCATION=1001;


        private void requestPermission(){


            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            }else{


                Intent intent = new Intent(ActivityMain.this,LocationUpdateServices.class);
                startService(intent);
                toast(" location service started  ");

            }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Intent intent = new Intent(ActivityMain.this,LocationUpdateServices.class);
                    startService(intent);
                    toast(" location service started after perm ");

                } else {
                    toast(" location not service started");

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(ActivityMain.this,LocationUpdateServices.class);
        stopService(intent);
    }

    private void toast(String msg){
        Toast.makeText(getApplicationContext()," msg :"+msg,Toast.LENGTH_LONG).show();
    }


}
