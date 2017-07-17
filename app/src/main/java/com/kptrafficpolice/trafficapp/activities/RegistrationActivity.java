package com.kptrafficpolice.trafficapp.activities;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.LoginFragment;
import com.kptrafficpolice.trafficapp.fragments.MainFragment;

public class RegistrationActivity extends AppCompatActivity {
    Fragment fragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sharedPreferences = getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();
        String prefCNIC = sharedPreferences.getString("true", "");
        if (prefCNIC.toString().length()>0) {
            Log.d("zma shared pref",prefCNIC);
            fragment = new MainFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        } else {
            Log.d("zma shared pref else",prefCNIC);
            fragment = new LoginFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                    commit();

        }


    }
}
