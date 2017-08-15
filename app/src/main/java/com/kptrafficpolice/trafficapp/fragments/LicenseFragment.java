package com.kptrafficpolice.trafficapp.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kptrafficpolice.trafficapp.R;
//raabta
//rabta
public class LicenseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
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
        tvLicHolderName = (TextView) view.findViewById(R.id.tv_lh_name);
        tvLicHolderFatherName = (TextView) view.findViewById(R.id.tv_lh_fname);
        tvLicType = (TextView) view.findViewById(R.id.tv_license_type);
        tvLicHolderDistrict = (TextView) view.findViewById(R.id.tv_lh_district);
        tvLisExpiryDate = (TextView) view.findViewById(R.id.tv_expiry_date);
        tvLicenseNumber = (TextView)view.findViewById(R.id.tv_license_number);
        tvCNICNumber = (TextView)view.findViewById(R.id.tv_lh_cnic_number);
        Bundle args = new Bundle(getArguments());
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
}
