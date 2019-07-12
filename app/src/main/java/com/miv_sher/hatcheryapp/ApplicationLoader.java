package com.miv_sher.hatcheryapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.crashlytics.android.Crashlytics;
import com.miv_sher.hatcheryapp.database.AppRepository;

import io.fabric.sdk.android.Fabric;

public class ApplicationLoader extends Application {
    public static volatile Handler applicationHandler;
    private static ApplicationLoader instance;
    private static Context mContext;
    private LifecycleHandler lifecycleHandler;
    private LifecycleHandler.Listener lifecycleHandlerListener = new LifecycleHandler.Listener() {
        @Override
        public void appOnBackground() {
        }


        @Override
        public void appOnForeground() {

        }
    };

    public static synchronized ApplicationLoader getInstance() {
        return instance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
        Fabric.with(this, new Crashlytics());
        addRestartServiceReceiver();
        lifecycleHandler = new LifecycleHandler();
        lifecycleHandler.setListener(lifecycleHandlerListener);
        registerActivityLifecycleCallbacks(lifecycleHandler);
        applicationHandler = new Handler(getApplicationContext().getMainLooper());
        AppRepository.getInstance().addSampleData();
    }

    private void addRestartServiceReceiver() {
        Intent i = new Intent("com.miv_sher.hatcheryapp.restartServiceReceiver");
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC, 0, 60000, pi);
    }

    public boolean appIsBackground() {
        return lifecycleHandler.appIsBackground();
    }

    public Activity getCurrentActivity() {
        return lifecycleHandler.getCurrentActivity();
    }
}
