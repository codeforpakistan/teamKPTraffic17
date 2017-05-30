package com.ghosttech.kptrafficapp.utilities;

/**
 * Created by Asus on 4/25/2017.
 */

import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    public static boolean flag = true;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onReceive(final Context context, Intent intent) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();

        String status = String.valueOf(CheckNetwork.isInternetAvailable(context));
        if (status.toString().equals("true")) {
            flag = true;
            editor.putString("internet_flag",String.valueOf(flag)).commit();

        } else {
            flag = false;
            editor.putString("internet_flag",String.valueOf(flag)).commit();


        }


    }

    public void onTrimMemory(final int level) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Toast.makeText(context, "app is closed", Toast.LENGTH_SHORT).show();
        }
    }

}