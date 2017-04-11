package com.ghosttech.kptrafficapp.activities;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ghosttech.kptrafficapp.R;
import com.ghosttech.kptrafficapp.fragments.LoginFragment;
import com.ghosttech.kptrafficapp.fragments.MainFragment;

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
        if (prefCNIC != null) {
            fragment = new MainFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        } else {
            fragment = new LoginFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                    commit();

        }


    }
}
