package com.xtempo.q2payrole;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Harsha on 11/9/2017.
 */

public class Q2PAYAPP extends Application {
    private static final String TAG = Q2PAYAPP.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        AppSettings.init(getApplicationContext());
        NetworkManager.init(getApplicationContext());

    }


    public static void logError(Context context, String tag, String message) {
        if (context != null && tag != null && message != null && AppSettings.isDevelopment()) {
            Log.e(tag, message);
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
