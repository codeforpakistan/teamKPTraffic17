package com.kptrafficpolice.trafficapp.utilities;

/**
 * Created by DzonE on 29-Aug-18.
 */

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class BackgroundService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Do something every 30 seconds

        SharedPreferences sharedPreferences = context.getSharedPreferences
                ("com.ghosttech.kptraffic", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("timer", "no");
        editor.apply();

//        Toast.makeText(context, "hello world", Toast.LENGTH_SHORT).show();
    }
}