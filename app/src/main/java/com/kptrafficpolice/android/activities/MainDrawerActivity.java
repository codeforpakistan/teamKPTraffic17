package com.kptrafficpolice.android.activities;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.crashlytics.android.Crashlytics;
import com.kptrafficpolice.android.R;
import com.kptrafficpolice.android.fragments.LoginFragment;
import com.kptrafficpolice.android.fragments.MainFragment;
import com.kptrafficpolice.android.fragments.MyComplaintsFragment;
import com.thefinestartist.finestwebview.FinestWebView;
import io.fabric.sdk.android.Fabric;

public class MainDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String prefCNIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences = getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();
         prefCNIC = sharedPreferences.getString("true", "");
        if (prefCNIC.toString().length() > 0) {
            Log.d("zma shared pref", prefCNIC);
            fragment = new MainFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        } else {
            Log.d("zma shared pref else", prefCNIC);
            fragment = new LoginFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                    commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_complaints) {
            fragment = new MyComplaintsFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
            // Handle the camera action
        } else if (id == R.id.nav_website) {
            new FinestWebView.Builder(MainDrawerActivity.this)
                    .titleDefault("KP Traffic Police Official Website")
                    .titleFont("Roboto-Medium.ttf")
                    .disableIconForward(true)
                    .disableIconBack(true)
                    .show("http://www.ptpkp.gov.pk/");
        } else if (id == R.id.nav_logout) {
            editor.clear().commit();
            startActivity(new Intent(MainDrawerActivity.this,MainDrawerActivity.class));
        } else if (id == R.id.nav_home) {
            fragment = new MainFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
