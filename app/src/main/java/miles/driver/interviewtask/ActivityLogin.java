package miles.driver.interviewtask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uber.sdk.android.core.auth.AccessTokenManager;
import com.uber.sdk.android.core.auth.AuthenticationError;
import com.uber.sdk.android.core.auth.LoginCallback;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.core.auth.AccessToken;

import miles.driver.interviewtask.apputils.Utils;

public class ActivityLogin extends AppCompatActivity implements LoginCallback,View.OnClickListener {

    private boolean mIsLoginSuccess;
    private Button mBtnLogin;
    private LoginManager mLoginManager;
    private Context mCtx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCtx = getApplicationContext();
        mBtnLogin=(Button)findViewById(R.id.id_btn_login);
        mBtnLogin.setOnClickListener(this);

    }


    @Override
    public void onLoginCancel() {
        setLoginFailure();
    }

    @Override
    public void onLoginError(@NonNull AuthenticationError error) {
          setLoginFailure();
    }

    @Override
    public void onLoginSuccess(@NonNull AccessToken accessToken) {
          setLoginSuccess();
          toast("Login Successfully");
          showMainActivity();
    }

    @Override
    public void onAuthorizationCodeReceived(@NonNull String authorizationCode) {
        setLoginFailure();
    }

    @Override
    public void onClick(View v) {
        if(v==mBtnLogin){
            login();
        }
    }

    private void setLoginSuccess(){
        mIsLoginSuccess = true;
        Utils.setLogin(mCtx,mIsLoginSuccess);

    }

    private void setLoginFailure(){
        mIsLoginSuccess = false;
        Utils.setLogin(mCtx,mIsLoginSuccess);

    }


    private void login(){
        AccessTokenManager accessTokenManager = new AccessTokenManager(mCtx);
        mLoginManager= new LoginManager(accessTokenManager,this);
        mLoginManager.login(ActivityLogin.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        mLoginManager.onActivityResult(this, requestCode, resultCode, data);

    }

    private void toast(String msg){
        Toast.makeText(mCtx," msg :"+msg,Toast.LENGTH_LONG).show();
    }

    private void showMainActivity(){
        startActivity(new Intent(ActivityLogin.this,ActivityMain.class));
        finish();
    }
}
