package miles.driver.interviewtask.apputils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sravan on 23/07/17.
 */

public class Utils {

    public static boolean  isLogin(Context ctx){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(ctx);
        return pref.getBoolean(Constants.KEY_LOGIN,false);

    }
    public static void setLogin(Context ctx, boolean islogin){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(ctx);
        SharedPreferences.Editor edit = pref.edit();
        edit.putBoolean(Constants.KEY_LOGIN,islogin);
        edit.commit();
    }

    public static WebServiceAPI getWebServiceObj(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.MAP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WebServiceAPI.class);
    }

    public static int getAppUID(Context mCtx){
        ApplicationInfo ai;
        try {
            ai=mCtx.getPackageManager().getApplicationInfo(mCtx.getPackageName(), 0);
            return ai.uid;
        } catch (final PackageManager.NameNotFoundException e) {
            return -1;
        }
    }
}
