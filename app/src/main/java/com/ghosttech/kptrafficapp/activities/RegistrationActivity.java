package com.ghosttech.kptrafficapp.activities;

import android.app.ActionBar;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ghosttech.kptrafficapp.R;
import com.ghosttech.kptrafficapp.fragments.ComplaintFragment;
import com.ghosttech.kptrafficapp.fragments.LoginFragment;
import com.ghosttech.kptrafficapp.fragments.MainFragment;
import com.ghosttech.kptrafficapp.fragments.RegistrationFragment;

public class RegistrationActivity extends AppCompatActivity {
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fragment = new LoginFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).
               commit();

    }
}
