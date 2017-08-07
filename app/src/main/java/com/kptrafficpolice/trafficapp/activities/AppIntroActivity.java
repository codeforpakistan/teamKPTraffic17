package com.kptrafficpolice.trafficapp.activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.kptrafficpolice.trafficapp.fragments.SliderFragment;

public class AppIntroActivity extends AppIntro {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.kptraficpolice.trafficapp",0);
        editor = sharedPreferences.edit();
        addSlide(SliderFragment.newInstance(0));
        addSlide(SliderFragment.newInstance(1));
        addSlide(SliderFragment.newInstance(2));
        if (sharedPreferences.getBoolean("verfied",false)){
            startActivity(new Intent(AppIntroActivity.this, MainActivity.class));
            finish();
        }

        setSeparatorColor(Color.parseColor("#ffffff"));
        // SHOW or HIDE the statusbar
        showStatusBar(true);
        // Hide Skip/Done button
        showSkipButton(true);
        showDoneButton(true);

        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest
        setVibrate(true);
        setVibrateIntensity(30);

        // Animations -- use only one of the below. Using both could cause errors.
        setFadeAnimation(); // OR
        setZoomAnimation(); // OR
        setFlowAnimation(); // OR
        setSlideOverAnimation(); // OR
        setDepthAnimation(); // OR
        // setCustomTransformer(yourCustomTransformer);

        // Permissions -- takes a permission and slide number
        askForPermissions(new String[]{Manifest.permission.VIBRATE}, 3);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(AppIntroActivity.this, MainActivity.class);
        editor.putBoolean("verfied",true).commit();
        startActivity(intent);
        finish();

    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(AppIntroActivity.this, MainActivity.class);
        editor.putBoolean("verfied",true).commit();
        startActivity(intent);
        finish();

    }
}
