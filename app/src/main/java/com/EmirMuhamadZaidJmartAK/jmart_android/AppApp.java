package com.EmirMuhamadZaidJmartAK.jmart_android;

import android.app.Application;
import android.content.Context;

/**
 * AppApp class
 * for getting context of an intent or activity
 */
public class AppApp extends Application {

    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}