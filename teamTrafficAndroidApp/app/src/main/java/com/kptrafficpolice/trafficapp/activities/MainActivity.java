package com.kptrafficpolice.trafficapp.activities;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {
    //raabta
//rabta
    Fragment fragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String prefCNIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();
        prefCNIC = sharedPreferences.getString("true", "");
        if (prefCNIC.toString().length() > 0) {
            startActivity(new Intent(MainActivity.this, MainDrawerActivity.class));
            finish();
        } else {
            fragment = new LoginFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
    }
}