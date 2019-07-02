package com.miv_sher.hatcheryapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.FrameLayout;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;

import java.util.concurrent.TimeUnit;

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
  //      Stetho.initializeWithDefaults(this);
//        Fabric.with(this, new Crashlytics());
        addRestartServiceReceiver();
        lifecycleHandler = new LifecycleHandler();
        lifecycleHandler.setListener(lifecycleHandlerListener);
        registerActivityLifecycleCallbacks(lifecycleHandler);
        applicationHandler = new Handler(getApplicationContext().getMainLooper());
        // Don't do this! This is just so cold launches take some time
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(0));


    }

    private void addRestartServiceReceiver() {
        Intent i = new Intent("com.itsoft.lan.irbis.restartServiceReceiver");
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
