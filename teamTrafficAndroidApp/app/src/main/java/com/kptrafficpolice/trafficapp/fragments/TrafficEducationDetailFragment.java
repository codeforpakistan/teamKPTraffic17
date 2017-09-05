package com.kptrafficpolice.trafficapp.fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kptrafficpolice.trafficapp.R;

import uk.co.senab.photoview.PhotoViewAttacher;
//raabta
//rabta
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrafficEducationDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrafficEducationDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrafficEducationDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAnalytics mFirebaseAnalytics;

    private OnFragmentInteractionListener mListener;
    ImageView ivEducationImage;
    TextView tvTitle, tvEnglishDescription, tvUrduDescription;
    public TrafficEducationDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TrafficEducationDetailFragment newInstance(String param1, String param2) {
        TrafficEducationDetailFragment fragment = new TrafficEducationDetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_traffic_education_detail, container, false);
        Bundle args = getArguments();
        ivEducationImage = (ImageView)view.findViewById(R.id.iv_traffic_education);
        tvTitle = (TextView)view.findViewById(R.id.tv_traffic_education_detail);
        tvEnglishDescription = (TextView)view.findViewById(R.id.tv_english_description);
        tvUrduDescription = (TextView)view.findViewById(R.id.tv_urdu_description);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "7");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Traffic Education Detail");
        mFirebaseAnalytics.logEvent("Traffic_Education_Detail", bundle);


        tvTitle.setText(String.valueOf(args.getString("title")));
        tvEnglishDescription.setText(String.valueOf(args.getString("englishDescription")));
        tvUrduDescription.setText(String.valueOf(args.getString("urduDescription")));
        Glide.with(getActivity()).load("http://103.240.220.76/kptraffic/uploads/traffic-education/"+
        args.getString("image")).into(ivEducationImage);
        PhotoViewAttacher pAttacher = new PhotoViewAttacher(ivEducationImage);
        pAttacher.update();
        customActionBar();
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
    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
        mTitleTextView.setText("Traffic Education Description");
//        mBackArrow.setImageResource(R.drawable.back_arrow);
//        mBackArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = new MainFragment();
//                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
//            }
//        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
}
