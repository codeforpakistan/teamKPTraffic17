package com.ghosttech.kptrafficapp.fragments;

import android.app.Dialog;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ghosttech.kptrafficapp.R;
import com.ghosttech.kptrafficapp.utilities.Configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Fragment fragment;
    View view;
    RequestQueue mRequestQueue;
    EditText etLicNumber;
    Button btnShowLicRecord;
    double dblLat, dblLon;
    String strCityName, strCheckLatLon, strLicenseNumber, strCNIC, strResponseLicenseID, strResponseDLNumber,
            strResponseCNIC, strResponseLicHolderName, strResponseLicHolderFatherName, strResponseLicType,
            strResponseExpiryDate, strResponseLicHolderDistrict;
    TextView mTitleTextView;
    Animation shake;
    LinearLayout btnComplaintSystem, btnLiveTrafficUpdates, btnChallanTracking, btnTrafficEducation,
            btnLicenseVerification, btnEmergencyContacts;
    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        view = inflater.inflate(R.layout.fragment_main, container, false);
        shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        onButtonClick();
        customActionBar();
        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        SmartLocation.with(getActivity()).location()
                .start(new OnLocationUpdatedListener() {

                    @Override
                    public void onLocationUpdated(Location location) {
                        dblLat = location.getLatitude();
                        dblLon = location.getLongitude();
                        Log.d("Location : ", "" + dblLat + " " + dblLon);
                        Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault());
                        StringBuilder builder = new StringBuilder();
                        try {
                            List<Address> address = geoCoder.getFromLocation(dblLat, dblLon, 1);
                            int maxLines = address.get(0).getMaxAddressLineIndex();
                            for (int i = 0; i < maxLines; i++) {
                                String addressStr = address.get(0).getAddressLine(i);
                                builder.append(addressStr);
                                builder.append(" ");
                            }

                            strCityName = builder.toString(); //This is the complete address.
                            mTitleTextView.setText(" " + strCityName);
                            Log.d("zma city", strCityName);
                        } catch (IOException e) {
                        } catch (NullPointerException e) {
                        }

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

    public void onButtonClick() {
        btnComplaintSystem = (LinearLayout) view.findViewById(R.id.linear_layout_cs);
        btnChallanTracking = (LinearLayout) view.findViewById(R.id.linear_layout_ct);
        btnEmergencyContacts = (LinearLayout) view.findViewById(R.id.linear_layout_ec);
        btnLicenseVerification = (LinearLayout) view.findViewById(R.id.linear_layout_lv);
        btnLiveTrafficUpdates = (LinearLayout) view.findViewById(R.id.linear_layout_lu);
        btnTrafficEducation = (LinearLayout) view.findViewById(R.id.linear_layout_te);
        btnComplaintSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ComplaintFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                        addToBackStack("tag").commit();
            }
        });
        btnLiveTrafficUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new LiveUpdateFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                        addToBackStack("tag").commit();
            }
        });
        btnLicenseVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog();


            }
        });

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
        mBackArrow.setImageResource(R.drawable.map_pointer);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    public void customDialog() {
        final Dialog dialog = new Dialog(getActivity());
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_input_dialog);
        dialog.setCancelable(true);
        etLicNumber = (EditText) dialog.findViewById(R.id.et_verify_license);
        btnShowLicRecord = (Button) dialog.findViewById(R.id.btn_search_license_record);
        strLicenseNumber = etLicNumber.getText().toString();
        Log.d("zma DL number", strLicenseNumber);

        final Bundle bundle = new Bundle();
        btnShowLicRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                fragment = new LicenseFragment();
                bundle.putString("license_number", strResponseDLNumber);
                bundle.putString("name", strResponseLicHolderName);
                fragment.setArguments(bundle);
                if (strLicenseNumber.toString().length() == 12) {

                } else if (strLicenseNumber.toString().length() > 13) {
                    strCNIC = strLicenseNumber;
                } else if (!strLicenseNumber.toString().equals("") || strLicenseNumber.toString().length() >= 12) {
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                            addToBackStack("tag").commit();
                    Log.d("zma DL number not wala", strLicenseNumber);
                    apiCall();
                } else {
                    etLicNumber.startAnimation(shake);
                }


            }
        });
        dialog.show();
    }

    public void apiCall() {
        String url = Configuration.END_POINT_LIVE + "license_verification/get_license_data";
        Log.d("zma log", url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean status = response.getBoolean("status");
                    if (status) {
                        JSONArray jsonArray = response.getJSONArray("Data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            strResponseDLNumber = jsonObject.getString("dl_number");
                            strResponseCNIC = jsonObject.getString("cnic");
                            strResponseLicHolderName = jsonObject.getString("name");
                            strResponseLicHolderFatherName = jsonObject.getString("father_name");
                            strResponseLicType = jsonObject.getString("license_type");
                            strResponseExpiryDate = jsonObject.getString("License_expiry_date");
                            strResponseLicHolderDistrict = jsonObject.getString("district");
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cnic", strCNIC);
                params.put("dl_number", strLicenseNumber);
                return params;
            }
        };
        mRequestQueue.add(jsonObjectRequest);
    }
}
