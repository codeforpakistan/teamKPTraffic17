package com.kptrafficpolice.trafficapp.activities;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.kptrafficpolice.trafficapp.Manifest;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.introSlider.FirstSlider;

public class AppIntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(FirstSlider.newInstance(0));
        addSlide(FirstSlider.newInstance(1));
        addSlide(FirstSlider.newInstance(2));
        Log.d("zma appintro","show sho");

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
//        addSlide(AppIntroFragment.newInstance("Complaint Registration", "Register a complaint regarding traffic violations",
//                R.drawable.intro_complaint_icon,Color.parseColor("#32c1d6")));
//
//        addSlide(AppIntroFragment.newInstance("Traffic Status", "Find out about the latest traffic situation",
//                R.drawable.intro_traffic_status_icon,Color.BLACK));
//
//        addSlide(AppIntroFragment.newInstance("Emergency Contacts", "Contact the nearest help provider",
//                R.drawable.intro_emergency_icon,Color.BLACK));

        // OPTIONAL METHODS

        // Override bar/separator color
        String colorCode[] = {"#32c1d6","#1f9b8c","#f79668"};
        for (int i = 0; i<colorCode.length;i++) {
            setBarColor(Color.parseColor(colorCode[i]));
        }
        setSeparatorColor(Color.parseColor("#2196F3"));

        // SHOW or HIDE the statusbar
        showStatusBar(true);

        // Edit the color of the nav bar on Lollipop+ devices
       // setNavBarColor(Color.parseColor("#3F51B5"));

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
        //askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}
