package com.kptrafficpolice.trafficapp.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.utilities.Configuration;
import com.kptrafficpolice.trafficapp.utilities.EmergencyHelper;
import com.kptrafficpolice.trafficapp.utilities.EmergencyListAdapter;
import com.kptrafficpolice.trafficapp.utilities.GPSTracker;
import com.thefinestartist.Base;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

import static android.content.Context.LOCATION_SERVICE;
import static com.thefinestartist.utils.service.ServiceUtil.getSystemService;
//raabta
//rabta

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EmergencyFragmentList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EmergencyFragmentList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmergencyFragmentList extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    List<EmergencyHelper> list;
    EmergencyListAdapter emergencyListAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String strEmergencyTypeID;
    double dblLat, dblLon;
    SweetAlertDialog pDialog;
    public static String CATEGORY_NAME = "";
    GPSTracker gpsTracker;
    Location location;
    private OnFragmentInteractionListener mListener;
    private FirebaseAnalytics mFirebaseAnalytics;
    TextView tvNoDataFound;
    public static boolean DistanceORDivision = false;

    public EmergencyFragmentList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmergencyFragmentList.
     */
    // TODO: Rename and change types and number of parameters
    public static EmergencyFragmentList newInstance(String param1, String param2) {
        EmergencyFragmentList fragment = new EmergencyFragmentList();
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
        View view = inflater.inflate(R.layout.emergency_fragment_list, container, false);
        tvNoDataFound = (TextView) view.findViewById(R.id.tv_no_data_found);
        tvNoDataFound.setVisibility(View.GONE);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        Base.initialize(getActivity());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "8");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Emergency Contacts");
        mFirebaseAnalytics.logEvent("Emergency_Contacts_Detail", bundle);


        Bundle args = new Bundle(getArguments());
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Wait a while");
        pDialog.show();
        strEmergencyTypeID = args.getString("emergency_id");
        if (strEmergencyTypeID.equals("2")) {
            CATEGORY_NAME = "Medical Assistance Contacts";
        } else if (strEmergencyTypeID.equals("3")) {
            CATEGORY_NAME = "Mechanics Contacts";
        } else if (strEmergencyTypeID.equals("4")) {
            CATEGORY_NAME = "Highway Officer Contacts";
        } else {
            CATEGORY_NAME = "Rescue Contacts";
        }
        customActionBar();
        _getLocation();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        Log.d("zma emergency type", strEmergencyTypeID);
        SmartLocation.with(getActivity()).location()
                .start(new OnLocationUpdatedListener() {

                    @Override
                    public void onLocationUpdated(Location location) {
                        dblLat = location.getLatitude();
                        dblLon = location.getLongitude();
                        getData(strEmergencyTypeID, String.valueOf(dblLat), String.valueOf(dblLon));
                    }
                });
        if (String.valueOf(dblLat).equals("0.0")) {
            gpsTracker = new GPSTracker(getActivity());
            location = gpsTracker.getLocation();
            dblLat = gpsTracker.getLatitude();
            dblLon = gpsTracker.getLongitude();
            getData(strEmergencyTypeID, String.valueOf(dblLat), String.valueOf(dblLon));
        }

        list = new ArrayList<>();
        Log.d("zma Location out : ", "" + dblLat + " " + dblLon);
        emergencyListAdapter = new EmergencyListAdapter(getActivity(), list);
        recyclerView.setAdapter(emergencyListAdapter);
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

    private void _getLocation() {
        // Get the location manager
        LocationManager locationManager = (LocationManager)
                getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(bestProvider);
        try {
            dblLon = location.getLatitude();
            dblLon = location.getLongitude();
            Log.d("zma new loc", String.valueOf(dblLat));
        } catch (NullPointerException e) {
            dblLon = -1.0;
            dblLon = -1.0;
        }
    }

    public void getData(final String strCategoryID, final String latitude, final String longitude) {
        final String url = Configuration.END_POINT_LIVE + "emergency_contacts/getEmergencyContact?category_id=" +
                strCategoryID + "&latitude=" + String.valueOf(dblLat) + "&longitude=" + String.valueOf(dblLon);
        Log.d("zma url", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("zma emer new res", String.valueOf(response));
                String res = response.toString();
                if (res.contains("distance")) {
                    try {
                        // Log.d("zma url emer list", url + "\n" + response.getBoolean("status") + "\n" + String.valueOf(response));
                        list.clear();
                        if (response.getBoolean("status")) {
                            pDialog.dismiss();
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject shopObject = data.getJSONObject(i);
                                EmergencyHelper helper = new EmergencyHelper();

                                helper.setStrHelperDistance(shopObject.getString("distance"));
                                helper.setStrHelperLocation(shopObject.getString("district_name"));
                                helper.setStrHelperName(shopObject.getString("name"));
                                helper.setStrHelperPhoneNumber(shopObject.getString("contact_no"));
                                list.add(helper);
                            }
                            if (data.length() == 0) {
                                pDialog.dismiss();
                                tvNoDataFound.setVisibility(View.VISIBLE);
                            }
                            Collections.sort(list, new Comparator<EmergencyHelper>() {
                                @Override
                                public int compare(EmergencyHelper emergencyHelper, EmergencyHelper t1) {
                                    String firstItem = emergencyHelper.getStrHelperDistance();
                                    String secondItem = t1.getStrHelperDistance();
                                    return firstItem.compareTo(secondItem);
                                }
                            });
                            emergencyListAdapter.notifyDataSetChanged();
                        } else {
                            pDialog.dismiss();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Oops!")
                                    .setContentText("No data found around your location")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            Fragment fragment = new MainEmergencyFragment();
                                            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                                            sweetAlertDialog.dismiss();

                                        }
                                    }).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        pDialog.dismiss();
                        tvNoDataFound.setVisibility(View.VISIBLE);
                    }
                }else {
                    try {
                        list.clear();
                        if (response.getBoolean("status")) {
                            pDialog.dismiss();
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject shopObject = data.getJSONObject(i);
                                EmergencyHelper helper = new EmergencyHelper();
                                DistanceORDivision = true;
                                helper.setStrHelperLocation(shopObject.getString("district_name"));
                                helper.setStrHelperName(shopObject.getString("name"));
                                helper.setStrHelperPhoneNumber(shopObject.getString("contact_no"));
                                list.add(helper);
                            }
                            if (data.length() == 0) {
                                pDialog.dismiss();
                                tvNoDataFound.setVisibility(View.VISIBLE);
                            }
                            emergencyListAdapter.notifyDataSetChanged();
                        } else {
                            pDialog.dismiss();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Oops!")
                                    .setContentText("No data found around your location")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            Fragment fragment = new MainEmergencyFragment();
                                            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                                            sweetAlertDialog.dismiss();

                                        }
                                    }).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        pDialog.dismiss();
                        tvNoDataFound.setVisibility(View.VISIBLE);
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(getActivity(), "Something went wrong, try later", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("category_id", strCategoryID);
                params.put("latitude", latitude);
                params.put("longitude", longitude);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }


    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText(CATEGORY_NAME);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }
}
