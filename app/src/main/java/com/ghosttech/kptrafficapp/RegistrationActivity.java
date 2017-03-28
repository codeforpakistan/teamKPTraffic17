package com.ghosttech.kptrafficapp;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fragment = new RegistrationFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).
                addToBackStack("tag").commit();
    }
}
