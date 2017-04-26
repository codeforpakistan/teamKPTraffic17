package com.ghosttech.kptrafficapp.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ghosttech.kptrafficapp.R;
import com.ghosttech.kptrafficapp.utilities.Configuration;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LicenseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText etLicNumber;
    Button btnShowLicRecord;
    String strGetLicenseNumber, strGetLicHolderName, strGetLicHolderFName, strGetLicType, strLicDistrict, strLicExpiryDate;
    TextView tvLicHolderName, tvLicHolderFatherName, tvLicHolderDistrict, tvLicType, tvLicIssueDate,
            tvLisExpiryDate,tvCNICNumber,tvLicenseNumber;
    private OnFragmentInteractionListener mListener;

    public LicenseFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LicenseFragment newInstance(String param1, String param2) {
        LicenseFragment fragment = new LicenseFragment();
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
        view = inflater.inflate(R.layout.fragment_license, container, false);
        tvLicHolderName = (TextView) view.findViewById(R.id.tv_name_lic_holder);
        tvLicHolderFatherName = (TextView) view.findViewById(R.id.tv_fn_lic_holder);
        tvLicType = (TextView) view.findViewById(R.id.tv_lic_type);
        tvLicHolderDistrict = (TextView) view.findViewById(R.id.tv_district_lic_holder);
        tvLisExpiryDate = (TextView) view.findViewById(R.id.tv_expiry_date);
        tvLicenseNumber = (TextView)view.findViewById(R.id.tv_license_number);
        tvCNICNumber = (TextView)view.findViewById(R.id.tv_cnic);
        Bundle args = new Bundle(getArguments());

        /*strGetLicenseNumber = String.valueOf(args.get("name"));
        strGetLicHolderFName = String.valueOf(args.get("f_name"));
        strGetLicType = String.valueOf("district"+"expiry_date");*/

        tvLicHolderName.setText(String.valueOf(args.get("name")));
        tvLicHolderDistrict.setText(String.valueOf(args.get("district")));
        tvLicHolderFatherName.setText(String.valueOf(args.get("f_name")));
        tvLisExpiryDate.setText(String.valueOf(args.get("expiry_date")));
        tvLicType.setText(String.valueOf(args.get("lic_type")));
        tvCNICNumber.setText(String.valueOf(args.get("cnic")));
        tvLicenseNumber.setText(String.valueOf(args.get("license_number")));


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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void apiCall() {
        String url = Configuration.END_POINT_LIVE + "license_verification/get_license_data";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

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
                params.put("cnic", "");
                params.put("dl_number", "");
                return params;
            }
        };
    }


}
