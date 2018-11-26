package com.kptrafficpolice.trafficapp.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.utilities.GPSTracker;
import com.thefinestartist.Base;
import com.tt.whorlviewlibrary.WhorlView;

import java.util.ArrayList;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

import static android.content.Context.LOCATION_SERVICE;
import static com.thefinestartist.utils.service.ServiceUtil.getSystemService;

//raabta
//rabta
public class LiveUpdateResultFragment extends Fragment {

    ArrayList<LatLng> locations = new ArrayList<>();
    MapView mMapView;
    String strStatus, strRoadName;
    Bundle args;
    PolylineOptions polylineOptions;
    Fragment fragment;
    Polyline polyline;
    View view;
    TextView tvRoadStatus, tvUpdateTime, tv_getting_location;
    ImageView ivHomeButton, ivSettingButton, ivWebsiteButton;
    CardView cardView;
    boolean timeFlag = false;
    private GoogleMap googleMap;
    double dblLat, dblLon;
    RelativeLayout fade_layout;
    WhorlView whorlView;
    Handler handler;


    public LiveUpdateResultFragment() {
    }


    public static LiveUpdateResultFragment newInstance() {
        return new LiveUpdateResultFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_live_update_result, container, false);
        Base.initialize(getActivity());

        handler = new Handler();

        fade_layout = (RelativeLayout) view.findViewById(R.id.fade_layout);
        tv_getting_location  = (TextView) view.findViewById(R.id.textView3);
        whorlView = (WhorlView) view.findViewById(R.id.whorl);
        whorlView.start();
        getLocation();

        mMapView = (MapView) view.findViewById(R.id.mapView);
        cardView = (CardView) view.findViewById(R.id.card_view_live_update);
        mMapView.onCreate(savedInstanceState);

        customActionBar();
        Log.d("zma road and status", strRoadName + "\n" + strStatus);
        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                // For showing a move to my ARRAY_CHARSADDA_ROAD button
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                googleMap.setIndoorEnabled(true);
                googleMap.setTrafficEnabled(true);

            }
        });

        return view;
    }

    public void pickMapView() {

        //moving camera to marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(dblLat, dblLon)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        final LatLng latLng = new LatLng(dblLat, dblLon);
        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Your Location"));

    }


    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Traffic Status");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }

    public void setText() {
        tvRoadStatus = (TextView) view.findViewById(R.id.tv_road_status);
        tvUpdateTime = (TextView) view.findViewById(R.id.tv_status_time);
        Bundle args = new Bundle(getArguments());
        tvUpdateTime.setText(String.valueOf(args.get("response_time")));
        tvRoadStatus.setText(String.valueOf(args.get("status")));
    }



    //show dialog if gps is off
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        builder.setMessage("Turn On your Location")
                .setCancelable(true)
                .setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });

        builder.create();
        builder.show();
    }

    public void getLocation() {

        SmartLocation.with(getActivity()).location()
                .start(new OnLocationUpdatedListener() {

                    @Override
                    public void onLocationUpdated(Location location) {
                        dblLat = location.getLatitude();
                        dblLon = location.getLongitude();
                    }
                });
        GPSTracker gpsTracker = new GPSTracker(getActivity());
        dblLat = gpsTracker.getLatitude();
        dblLon = gpsTracker.getLongitude();

        Log.e("zma compl Location : ", "" + dblLat + " " + dblLon);
    }


    @Override
    public void onResume() {
        super.onResume();
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.e("gps", "enabled");

            getLocation();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    SmartLocation.with(getActivity()).location()
                            .start(new OnLocationUpdatedListener() {

                                @Override
                                public void onLocationUpdated(Location location) {
                                    dblLat = location.getLatitude();
                                    dblLon = location.getLongitude();
                                }
                            });
                    GPSTracker gpsTracker = new GPSTracker(getActivity());
                    dblLat = gpsTracker.getLatitude();
                    dblLon = gpsTracker.getLongitude();

                    pickMapView();
                    Log.e("onResume", dblLat + "\t" + dblLon);

                    if (dblLat == 0){
                        getLocation();
                    }
                    else {
                        fade_layout.setVisibility(View.INVISIBLE);
                        whorlView.stop();
                        tv_getting_location.setVisibility(View.INVISIBLE);
                        whorlView.setVisibility(View.INVISIBLE);
                    }

                }
            }, 4000);

        } else {
            Log.e("gps", "not enabled");
            buildAlertMessageNoGps();
        }

    }


    @Override
    public void onDestroy() {
        //remove pending handler
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}