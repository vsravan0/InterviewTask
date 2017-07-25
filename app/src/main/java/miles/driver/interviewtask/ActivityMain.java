package miles.driver.interviewtask;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import miles.driver.interviewtask.adapter.PagerAdapter;
import miles.driver.interviewtask.apputils.LocationUpdateServices;

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

        Intent intent = new Intent(ActivityMain.this,LocationUpdateServices.class);
        startService(intent);
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
