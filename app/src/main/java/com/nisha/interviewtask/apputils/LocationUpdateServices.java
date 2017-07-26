package com.nisha.interviewtask.apputils;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nisha on 7/23/2017.
 */

public class LocationUpdateServices extends Service implements LocationListener{

    private static final String TAG = LocationUpdateServices.class.getSimpleName();

    public GoogleApiClient mGoogleApiClient;
    public LocationRequest mLocationRequest;
    public Location userlocation;
    private WebServiceAPI mService;
    private LocationManager locationManager;
    private Context ctx;


    @Override
    public void onCreate() {
        super.onCreate();
        ctx = getApplicationContext();
        //createLocationRequest();
        mService= Utils.getWebServiceObj();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Criteria myCriteria = new Criteria();
        myCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
        myCriteria.setPowerRequirement(Criteria.POWER_LOW);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String myProvider = locationManager.getBestProvider(myCriteria, true);
        if (myProvider == null)
            myProvider = LocationManager.NETWORK_PROVIDER;


        if (locationManager.getAllProviders().contains(myProvider)) {
           startLocationUpdates(myProvider);
        }

        return START_STICKY;
    }
    @Override
    public void onDestroy() {

        super.onDestroy();

        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }


    protected void startLocationUpdates(String provider) {

        if (ContextCompat.checkSelfPermission(ctx,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(provider,
                    Constants.LOCATION_TIME_INTERVAL, 0, this);
            //   LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        Log.e(TAG, "Location update started ..............: ");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * This method used assign the location request.
     */
    private void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(Constants.LOCATION_TIME_INTERVAL);
        mLocationRequest.setFastestInterval(Constants.LOCATION_TIME_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }




    @Override
    public void onLocationChanged(Location location) {
        userlocation=location;

        Handler h=new Handler(Looper.getMainLooper());
        h.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ctx,"lat: "+userlocation.getLatitude()+"  lat: "+userlocation.getLongitude(),
                        Toast.LENGTH_LONG).show();
            }
        });

        MyThread thread = new MyThread(location);
        thread.start();


        RequestSendLocations request=new RequestSendLocations();
        request.setLatitude(location.getLatitude()+"");
        request.setLongitude(location.getLongitude()+"");
        request.setUserName("Nisha");
        request.setName("Nisha Android");
        request.setChannel("#Android");
        request.setIconEmoji(":ghost");

       // Map<String, String> map = request.getMapObject(request);

        Call<Void> resultCall = mService.sendLocation("sample channel",
                request.getText(),
                request.getUserName(),
                request.getName(),request.getLatitude(),request.getLongitude(),request.getIconEmoji());

        resultCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                    Log.e(TAG,"Success");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });

//        Call<Void> resultcall = mService.sendLocation(request);
//        resultcall.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override

    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    public class MyThread extends Thread{

        private Location location;
        public MyThread(Location location){
            this.location = location;
        }

        @Override
        public void run() {
                    Intent intent = new Intent();
                    intent.setAction(Constants.KEY_USER_LOC);
                    intent.putExtra("DATAPASSED", location);
                    sendBroadcast(intent);
                }
    }
}
