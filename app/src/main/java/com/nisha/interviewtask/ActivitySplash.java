package com.nisha.interviewtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nisha.interviewtask.apputils.Utils;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);


    }


    @Override
    protected void onResume() {
        super.onResume();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(Utils.isLogin(getApplicationContext())){
            startActivity(new Intent(ActivitySplash.this,ActivityMain.class));

        }else{
            startActivity(new Intent(ActivitySplash.this,ActivityLogin.class));

        }
finish();
    }
}
