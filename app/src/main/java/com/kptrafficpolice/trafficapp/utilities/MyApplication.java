package com.kptrafficpolice.trafficapp.utilities;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.kptrafficpolice.trafficapp.R;

/**
 * Created by Asus on 5/30/2017.
 */

public class MyApplication extends Application {
    Context context;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        sAnalytics = GoogleAnalytics.getInstance(this);
        // Required initialization logic here!
    }
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}