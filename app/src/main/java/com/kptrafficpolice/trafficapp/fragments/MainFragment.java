package com.kptrafficpolice.trafficapp.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.activities.MainDrawerActivity;
import com.kptrafficpolice.trafficapp.utilities.CheckNetwork;
import com.kptrafficpolice.trafficapp.utilities.Configuration;
import com.kptrafficpolice.trafficapp.utilities.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
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
    TextView tvUserName;
    Dialog dialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RequestQueue mRequestQueue;
    EditText etLicNumber;
    Button btnShowRecord;
    double dblLat, dblLon;
    ImageView ivSettingButton, ivWebsiteButton;
    String strCityName, strChallanTrackingID, strCheckLatLon, strLicenseNumber, strCNIC, strResponseLicenseID, strResponseDLNumber,
            strResponseCNIC, strResponseLicHolderName, strResponseLicHolderFatherName, strResponseLicType,
            strResponseExpiryDate, strResponseLicHolderDistrict;
    String strChallanDate, strChallanDistrict, strChallanName, strChallanDutyPoint, strChallanAmount, strChallanStatus;
    TextView mTitleTextView;
    SweetAlertDialog pDialog;
    Animation shake;
    LinearLayout btnComplaintSystem, btnLiveTrafficUpdates, btnChallanTracking, btnTrafficEducation,
            btnLicenseVerification, btnEmergencyContacts;
    private OnFragmentInteractionListener mListener;
    private Tracker mTracker;
    private FirebaseAnalytics mFirebaseAnalytics;
    public static boolean SUCCESS_DIALOG = false;
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle("Main Screen");
        if (SUCCESS_DIALOG){
            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Complaint registered")
                    .show();
            SUCCESS_DIALOG = false;
        }
        setHasOptionsMenu(true);
//        MyApplication application = (MyApplication) getActivity().getApplication();
//        mTracker = application.getDefaultTracker();
        if (!CheckNetwork.isInternetAvailable(getActivity())) {
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Oops")
                    .setContentText("You don't have internet connection!")
                    .setConfirmText("Refresh")
                    .setCancelText("Exit App")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            getActivity().finish();

                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            startActivity(new Intent(getActivity(), MainDrawerActivity.class));
                            getActivity().finish();
                        }
                    })
                    .show();

        }else {
            SmartLocation.with(getActivity()).location()
                    .start(new OnLocationUpdatedListener() {

                        @Override
                        public void onLocationUpdated(Location location) {
                            dblLat = location.getLatitude();
                            dblLon = location.getLongitude();
                            Log.d("Location : ", "" + dblLat + " " + dblLon);
                            Geocoder geoCoder = null;
                            try {


                                geoCoder = new Geocoder(getActivity(), Locale.getDefault());
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            StringBuilder builder = new StringBuilder();

                            try {
                                List<Address> address = geoCoder.getFromLocation(dblLat, dblLon, 1);
                                int maxLines = address.get(0).getMaxAddressLineIndex();
                                for (int i = 0; i < maxLines; i++) {
                                    String addressStr = address.get(0).getAddressLine(i);
                                    String city = address.get(0).getLocality();
                                    String state = address.get(0).getAdminArea();
                                    String country = address.get(0).getCountryName();
                                    String postalCode = address.get(0).getPostalCode();
                                    String knownName = address.get(0).getFeatureName();
                                    String subadmin = address.get(0).getSubLocality();
//                                Log.d("zma city 2", "city " + city + "\nstate " + state + "\n country " +
//                                        country + "\n postal code " + postalCode + "\nknow name " + knownName + "get sub admin area" + subadmin);
                                    builder.append(addressStr);
                                    builder.append(" ");
                                }

                                strCityName = builder.toString(); //This is the complete address.
                                mTitleTextView.setText(strCityName);
                                // Log.d("zma city", strCityName);
                                if (address.size() > 0) {

                                    System.out.println(address.get(0).getCountryName());
                                    System.out.println(address.get(0).getAdminArea());
                                    System.out.println(address.get(0).getSubLocality());
                                }
                            } catch (IOException e) {
                            } catch (NullPointerException e) {
                            }

                        }
                    });

        }
        MyApplication application = new MyApplication();
        mTracker = application.getDefaultTracker();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        Log.d("zma analytics", "Setting screen name: " + "Main Fragment");
//        mTracker.setScreenName("Image~" + "Main Fragment");
       // mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        tvUserName = (TextView)view.findViewById(R.id.tv_user_name);
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setCancelable(false);

//        Bundle args = getArguments();
//        boolean status = args.getBoolean("status");
//        if (status){
//            Toast.makeText(getActivity(), "Good nice", Toast.LENGTH_SHORT).show();
//        }
        sharedPreferences = getActivity().getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();
        tvUserName.setText(sharedPreferences.getString("user_name",null));
        MultiDex.install(getActivity());
        shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        if (CheckNetwork.isInternetAvailable(getActivity())) {
            onButtonClick();
        } else {
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Oops")
                    .setContentText("You don't have internet connection!")
                    .setConfirmText("Refresh")
                    .setCancelText("Exit App")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            getActivity().finish();

                        }
                    })
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            startActivity(new Intent(getActivity(), MainDrawerActivity.class));
                            sweetAlertDialog.dismiss();
                            getActivity().finish();

                        }
                    })
                    .show();
        }
        //customActionBar();
        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

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
//                mTracker.send(new HitBuilders.EventBuilder()
//                        .setCategory("Complaint Module")
//                        .setAction("Opening Complaint")
//                        .build());
                fragment = new ComplaintRegistrationFragment();
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
                customDialogLicenseVerification();


            }
        });
        btnEmergencyContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new MainEmergencyFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                        addToBackStack("tag").commit();
            }
        });
        btnTrafficEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new TrafficEducationFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                        addToBackStack("tag").commit();
            }
        });
        btnChallanTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogChallanStatus();
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
      //  ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
        //mBackArrow.setImageResource(R.drawable.map_pointer);
//        mBackArrow.setVisibility(View.GONE);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }


    public void customDialogLicenseVerification() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_input_dialog);
        dialog.setCancelable(true);
        etLicNumber = (EditText) dialog.findViewById(R.id.et_verify_license);
        btnShowRecord = (Button) dialog.findViewById(R.id.btn_search_license_record);
        dialog.show();
        inputValidationLicense();
    }

    public void customDialogChallanStatus() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_input_dialog);
        dialog.setCancelable(true);
        etLicNumber = (EditText) dialog.findViewById(R.id.et_verify_license);
        etLicNumber.setHint("Enter Challan Number");
        btnShowRecord = (Button) dialog.findViewById(R.id.btn_search_license_record);
        dialog.show();
        inputValidationChallan();
    }

    public void inputValidationChallan() {
        btnShowRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strChallanTrackingID = etLicNumber.getText().toString();
                if (strChallanTrackingID.toString().equals("")
                        || strChallanTrackingID.toString().length() > 10) {
                  //  Log.d("zma else", strChallanTrackingID);
                    etLicNumber.startAnimation(shake);
                } else {
                    apiCallChallan(strChallanTrackingID);
                    dialog.dismiss();
                }

            }
        });
    }

    public void inputValidationLicense() {
        btnShowRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strLicenseNumber = etLicNumber.getText().toString();
                if (strLicenseNumber.toString().equals("")
                        || strLicenseNumber.toString().length() < 12) {
                  //  Log.d("zma else", strLicenseNumber);
                    etLicNumber.startAnimation(shake);
                } else if (strLicenseNumber.toString().length() == 12) {
                   // Log.d("zma License", strLicenseNumber);
                    apiCallLicense(strLicenseNumber);
                } else if (strLicenseNumber.toString().length() == 13) {
                    strCNIC = strLicenseNumber;
                    apiCallLicense(strCNIC);
                }

            }
        });
    }

    public void apiCallLicense(final String cnic) {
        pDialog.setTitleText("Verifying Your License");
        pDialog.show();
        String url = Configuration.END_POINT_LIVE + "license_verification/get_license_data";
        final Bundle args = new Bundle();
        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponseObject = new JSONObject(response);
                            boolean status = jsonResponseObject.getBoolean("status");
                          //  Log.d("zma status", String.valueOf(status));
                            if (status) {
                                pDialog.dismiss();
                                dialog.dismiss();
                                JSONArray jsonArray = jsonResponseObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    strResponseLicHolderName = String.valueOf(jsonObject.get("name"));
                                    strResponseDLNumber = String.valueOf(jsonObject.get("dl_number"));
                                    strResponseCNIC = String.valueOf(jsonObject.get("cnic"));
                                    strResponseLicHolderFatherName = String.valueOf(jsonObject.get("father_name"));
                                    strResponseLicType = String.valueOf(jsonObject.get("license_type"));
                                    strResponseExpiryDate = String.valueOf(jsonObject.get("license_expiry_date"));
                                    strResponseLicHolderDistrict = String.valueOf(jsonObject.get("district"));
                                }
                                args.putString("name", strResponseLicHolderName);
                                args.putString("f_name", strResponseLicHolderFatherName);
                                args.putString("cnic", strResponseCNIC);
                                args.putString("license_number", strResponseDLNumber);
                                args.putString("lic_type", strResponseLicType);
                                args.putString("expiry_date", strResponseExpiryDate);
                                args.putString("district", strResponseLicHolderDistrict);
                                fragment = new LicenseFragment();
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
                                fragment.setArguments(args);
                                //TODO extract data from jsonarray data
                            } else {
                                pDialog.dismiss();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Oops...")
                                        .setContentText("No Data found")
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.dismiss();
                            Log.d("zma status lic", String.valueOf(response));
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("You are not a verified license holder")
                                    .show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Server Error!")
                        .show();
                Log.d("zma error registration", String.valueOf(error));
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cnic", cnic);
                Log.d("zma params", String.valueOf(params));
                return params;
            }

        };
        jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjRequest);
    }

    public void apiCallChallan(final String strChallanID) {
        pDialog.setTitleText("Verifying Challan ID");
        pDialog.show();
        String url = Configuration.END_POINT_LIVE + "challan/get_challan_info?TicketId=" + strChallanID;
        Log.d("zma url", url);
        final Bundle args = new Bundle();
        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject mainResponse = new JSONObject(response);
                            if (mainResponse.getBoolean("status")) {
                                pDialog.dismiss();
                                JSONObject data = mainResponse.getJSONObject("data");
                                strChallanDate = data.getString("date");
                                strChallanDistrict = data.getString("district");
                                strChallanName = data.getString("name");
                                strChallanDutyPoint = data.getString("duty_point");
                                String ticket_id = data.getString("ticket_id");
                                strChallanAmount = data.getString("amount");
                                strChallanStatus = data.getString("status");

                                args.putString("date", strChallanDate);
                                args.putString("district", strChallanDistrict);
                                args.putString("dutyPoint", strChallanDutyPoint);
                                args.putString("name", strChallanName);
                                args.putString("amount", strChallanAmount);
                                args.putString("status", strChallanStatus);
                                fragment = new ChallanFragment();
                                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
                                fragment.setArguments(args);

                            } else {
                                pDialog.dismiss();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Oops...")
                                        .setContentText("Challan number not found")
                                        .show();
                            }
                        } catch (JSONException e) {
                            pDialog.dismiss();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Invalid Challan number")
                                    .show();
                            e.printStackTrace();
                        }
                    }
                }
                , new Response.ErrorListener()

        {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Server Error!")
                        .show();
                Log.d("zma error registration", String.valueOf(error));
            }
        });


        jsonObjRequest.setRetryPolicy(new

                DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)

        );
        mRequestQueue.add(jsonObjRequest);
    }

//    public void footerButtons() {
//        ivSettingButton = (ImageView) view.findViewById(R.id.iv_setting_menu);
//        ivWebsiteButton = (ImageView) view.findViewById(R.id.iv_website_link);
//        ivWebsiteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new FinestWebView.Builder(getActivity())
//                        .titleDefault("KP Traffic Police Official Website")
//                        .titleFont("Roboto-Medium.ttf")
//                        .disableIconForward(true)
//                        .disableIconBack(true)
//                        .show("http://www.ptpkp.gov.pk/");            }
//        });
//    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
