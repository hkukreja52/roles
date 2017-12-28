package com.xtempo.q2payrole;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import com.xtempo.networklibrary.shared_preferences.PermanentPreferences;
import com.xtempo.networklibrary.shared_preferences.PreferenceKeys;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Harsha on 11/9/2017.
 */

public class AppSettings {
    private static final String TAG = AppSettings.class.getSimpleName();
    public enum Property {
        URL_BASE
    }

    private static final String FILENAME_LOCAL_SETTINGS = "local_settings.properties";

    private static AppSettings instance;

    private Context context;
    private Properties properties;
    private PreferenceManager privateManager;

    public static void init(Context context) {
        if (instance == null)
            instance = new AppSettings(context);
    }

    public static boolean isDevelopment() {
        return BuildConfig.DEBUG;
    }


    public static boolean containsUserCache() {
        boolean contained = false;

        if (instance != null) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(instance.context);
            contained = pref.contains(PreferenceKeys.AUTH_KEY);
        }

        return contained;
    }

    public static void clearData() {
        if (instance != null) {
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(instance.context).edit();
            editor.clear();
            editor.apply();
        }
    }

    public static void restartApp() {
        if (instance != null) {
            Intent intent = instance.context.getPackageManager().getLaunchIntentForPackage(instance.context.getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            instance.context.startActivity(intent);
        }
    }

    public static String getProperty(Property property) {
        if (instance == null)
            return null;

        String propertyStr;

        switch (property) {
            case URL_BASE:
                propertyStr = instance.privateManager.getSharedPreferences().getString(PermanentPreferences.BASE_URL, instance.getPropertyString(property));
                break;


            default:
                propertyStr = instance.getPropertyString(property);
                break;
        }

        return propertyStr;
    }

    public static void setProperty(Property property, String propertyStr) {
        switch (property) {

            case URL_BASE:
                instance.privateManager.getSharedPreferences().edit().putString(PermanentPreferences.BASE_URL, propertyStr).apply();
                break;

            default:
        }
    }

    @SuppressLint("RestrictedApi")
    private AppSettings(Context context) {
        this.context = context.getApplicationContext();

        properties = new Properties();
        try {
            properties.load(context.getAssets().open(FILENAME_LOCAL_SETTINGS));
        } catch (IOException e) {
            Q2PAYAPP.logError(context, TAG, "Unable to get local settings");
        }

        privateManager = new PreferenceManager(context);
        privateManager.setSharedPreferencesName(PermanentPreferences.NAME);
        privateManager.setSharedPreferencesMode(Context.MODE_PRIVATE);
        privateManager.setStorageDeviceProtected();
    }

    private String getPropertyString(Property property) {
        String propertyStr = isDevelopment() ? "testing_" : "production_";

        switch (property) {
            case URL_BASE:
                propertyStr += "url_base";
                break;
        }

        return properties.getProperty(propertyStr);
    }
}
