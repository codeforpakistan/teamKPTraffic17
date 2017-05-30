package com.ghosttech.kptrafficapp.utilities;

import android.app.Application;
import android.content.Context;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Asus on 5/30/2017.
 */

public class MyApplication extends Application {
    Context context;
    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
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