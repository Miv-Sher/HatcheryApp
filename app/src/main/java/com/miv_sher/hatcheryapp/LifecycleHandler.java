package com.miv_sher.hatcheryapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

public class LifecycleHandler implements Application.ActivityLifecycleCallbacks {

    private int startedCount = 0;
    private Listener listener;
    private Activity currentActivity = null;

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        startedCount++;
        if (startedCount == 1 && listener != null) {
            listener.appOnForeground();
        }
        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        startedCount--;
        if (startedCount < 0) startedCount = 0;
        if (startedCount == 0 && listener != null) {
            listener.appOnBackground();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public boolean appIsBackground() {
        return startedCount == 0;
    }

    public interface Listener {
        void appOnBackground();

        void appOnForeground();
    }


}
