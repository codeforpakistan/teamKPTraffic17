package com.ghosttech.kptrafficapp.fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghosttech.kptrafficapp.R;
import com.ghosttech.kptrafficapp.utilities.LiveUpdatesMapCoorindates;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LiveUpdateResultFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LiveUpdateResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveUpdateResultFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LatLng CHARSADDA_ROAD_1 = new LatLng(34.032627, 71.576201);
    LatLng CHARSADDA_ROAD_2 = new LatLng(34.034312, 71.5779767);
    LatLng CHARSADDA_ROAD_3 = new LatLng(34.046545, 71.5806858);
    LatLng CHARSADDA_ROAD_4 = new LatLng(34.05858, 71.5868058);
    ArrayList<LatLng> locations = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MapView mMapView;
    private GoogleMap googleMap;
    String strStatus, strRoadName;
    Bundle args;
    PolylineOptions polylineOptions;
    Fragment fragment;
    View view;
    TextView tvRoadStatus, tvUpdateTime;


    private OnFragmentInteractionListener mListener;

    public LiveUpdateResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LiveUpdateResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LiveUpdateResultFragment newInstance(String param1, String param2) {
        LiveUpdateResultFragment fragment = new LiveUpdateResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_live_update_result, container, false);
        customActionBar();
        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        args = getArguments();
        strStatus = args.getString("status");
        strRoadName = args.getString("road_name");
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
                pickMapView();

//                for (LatLng point : LiveUpdatesMapCoorindates.getArrayCharsaddaRoad()) {
//                    markerOptions.position(point);
//                    googleMap.addMarker(markerOptions);
//                }
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LiveUpdatesMapCoorindates.KHYBER_ROAD_1, 12));
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void pickMapView() {
        switch (strRoadName) {
            case "gt_road":
                //setPolylineOptions();
                break;
            case "khyber_road":
                setPolylineOptions();
                break;
            case "charsadda_road":
                //setPolylineOptions();
                break;
            case "jail_road":
                //setPolylineOptions();
                break;
            case "university_road":
               // setPolylineOptions();
                break;
            case "dalazak_road":
                //setPolylineOptions();
                break;
            case "saddar_road":
                //setPolylineOptions();
                break;
            case "baghenaran_road":
               // setPolylineOptions();
                break;
            case "warsak_road":
                //setPolylineOptions();
                break;
            case "kohat_road":
                //setPolylineOptions();
                break;
        }

    }

    public void setPolylineOptions() {
//Remove the same line from map
        if (strStatus.equals("Clear")) {
            polylineOptions = new PolylineOptions().
                    addAll(LiveUpdatesMapCoorindates.getArrayKhyberRoad())
                    .width(10).color(Color.GREEN).geodesic(true);
            setText();


        } else if (strStatus.equals("Busy")) {
            polylineOptions = new PolylineOptions().
                    addAll(LiveUpdatesMapCoorindates.getArrayKhyberRoad())
                    .width(10).color(Color.RED).geodesic(true);
            setText();

        } else if (strStatus.equals("Congested")) {
            polylineOptions = new PolylineOptions().
                    addAll(LiveUpdatesMapCoorindates.getArrayKhyberRoad())
                    .width(10).color(Color.YELLOW).geodesic(true);
            setText();

        }
        googleMap.clear();
        googleMap.addPolyline(polylineOptions);
    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new LiveUpdateFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();


            }
        });

    }

    public void setText() {
        tvRoadStatus = (TextView) view.findViewById(R.id.tv_road_status);
        tvUpdateTime = (TextView) view.findViewById(R.id.tv_status_time);
        Bundle args = new Bundle(getArguments());
        tvUpdateTime.setText(String.valueOf(args.get("response_time")));
        tvRoadStatus.setText(String.valueOf(args.get("status")));
    }


}
