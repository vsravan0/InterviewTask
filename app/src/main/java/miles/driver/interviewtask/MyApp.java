package miles.driver.interviewtask;

import android.app.Application;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

import miles.driver.interviewtask.apputils.Constants;

/**
 * Created by sravan on 23/07/17.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        loadUber();
    }

    private void loadUber(){
        SessionConfiguration config = new SessionConfiguration.Builder().
                setClientId(Constants.CLIENT_ID)
                .setServerToken(Constants.SERVER_TOKEN)
                .setClientSecret(Constants.CLIENT_SECRET)
                .setRedirectUri(Constants.REDERICT_URI)
                .setScopes(Arrays.asList(Scope.PROFILE, Scope.RIDE_WIDGETS))
                .setEnvironment(SessionConfiguration.Environment.SANDBOX).build();
        UberSdk.initialize(config);
    }
}
