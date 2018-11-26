package com.kptrafficpolice.trafficapp.fragments.ETest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kptrafficpolice.trafficapp.R;

//raabta
//rabta
public class TestMainFragment extends Fragment {

    Button btn_start_test, btn_scores, btn_prepare;
    Fragment fragment;
    FirebaseAnalytics mFirebaseAnalytics;

    public TestMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test_main, container, false);

        customActionBar();


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "9");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "E-Test Module Clicked");
        mFirebaseAnalytics.logEvent("E_Test_Module_Main", bundle);


        btn_start_test = (Button) view.findViewById(R.id.start);
        btn_scores = (Button) view.findViewById(R.id.scores);
        btn_prepare = (Button) view.findViewById(R.id.prepare);

        btn_prepare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new PrepareYourself();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                        addToBackStack("tag").commit();
            }
        });


        btn_start_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new SelectTestCategory();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                        addToBackStack("tag").commit();

            }
        });


        btn_scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new AllScores();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                        addToBackStack("tag").commit();
            }
        });

        return view;
    }


    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Licence Test");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
