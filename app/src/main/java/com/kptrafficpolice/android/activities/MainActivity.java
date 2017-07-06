package com.kptrafficpolice.android.activities;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kptrafficpolice.android.fragments.MainFragment;
import com.kptrafficpolice.android.R;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new MainFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
    }
}
