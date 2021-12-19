package com.EmirMuhamadZaidJmartAK.jmart_android;

import android.app.Application;
import android.content.Context;

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