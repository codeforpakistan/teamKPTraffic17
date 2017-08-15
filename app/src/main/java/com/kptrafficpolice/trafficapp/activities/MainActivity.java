package com.kptrafficpolice.trafficapp.activities;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kptrafficpolice.trafficapp.fragments.LoginFragment;
import com.kptrafficpolice.trafficapp.R;

public class MainActivity extends AppCompatActivity {
    //raabta
//rabta
    Fragment fragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String prefCNIC;
    public static boolean SLIDER_FLAG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();
        prefCNIC = sharedPreferences.getString("true", "");
        Log.d("zma shared pref drawer", prefCNIC);
        if (prefCNIC.toString().length() > 0) {
            Log.d("zma shared if drawer", prefCNIC);
            startActivity(new Intent(MainActivity.this, MainDrawerActivity.class));
            finish();
        } else {
            Log.d("zma shared pref else", prefCNIC);
            fragment = new LoginFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                    commit();

        }
    }

}
