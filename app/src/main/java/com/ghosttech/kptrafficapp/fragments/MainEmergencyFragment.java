package com.ghosttech.kptrafficapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ghosttech.kptrafficapp.R;
import com.imangazaliev.circlemenu.CircleMenu;
import com.imangazaliev.circlemenu.CircleMenuButton;
import com.thefinestartist.finestwebview.FinestWebView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainEmergencyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainEmergencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainEmergencyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView ivHealthEmergency, ivMechanicsEmergency, ivRescueEmergency, ivHighOfficer, ivHomeButton, ivSettingButton, ivWebsiteButton;
    Fragment fragment;
    private OnFragmentInteractionListener mListener;

    public MainEmergencyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainEmergencyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainEmergencyFragment newInstance(String param1, String param2) {
        MainEmergencyFragment fragment = new MainEmergencyFragment();
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
        view = inflater.inflate(R.layout.fragment_main_emergency, container, false);
        ivHealthEmergency = (ImageView) view.findViewById(R.id.iv_emergency_health);
        ivHighOfficer = (ImageView) view.findViewById(R.id.iv_emergency_highway_officer);
        ivMechanicsEmergency = (ImageView) view.findViewById(R.id.iv_emergency_mechanics);
        ivRescueEmergency = (ImageView) view.findViewById(R.id.iv_emergency_rescue_1122);
        footerButtons();
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

    public void footerButtons() {
        ivHomeButton = (ImageView) view.findViewById(R.id.iv_home_button);
        ivSettingButton = (ImageView) view.findViewById(R.id.iv_setting_menu);
        ivWebsiteButton = (ImageView) view.findViewById(R.id.iv_website_link);
        ivHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new MainFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
        ivWebsiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FinestWebView.Builder(getActivity())
                        .titleDefault("KP Traffic Police Official Website")
                        .titleFont("Roboto-Medium.ttf")
                        .disableIconForward(true)
                        .disableIconBack(true)
                        .show("http://www.ptpkp.gov.pk/");
                //startActivity(new Intent(getActivity(), FinestWebViewActivity.class));
            }
        });
    }
}
