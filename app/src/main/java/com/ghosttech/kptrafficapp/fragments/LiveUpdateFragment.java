package com.ghosttech.kptrafficapp.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.ghosttech.kptrafficapp.R;
import com.ghosttech.kptrafficapp.utilities.LiveUpdateAdapter;
import com.ghosttech.kptrafficapp.utilities.LiveUpdateHelper;

import java.util.ArrayList;
import java.util.List;

public class LiveUpdateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] myDataset;
    List<LiveUpdateHelper> liveUpdateHelpers;
    private OnFragmentInteractionListener mListener;
    EditText etSearchBar;
    ImageView ivSearchIcon;

    public LiveUpdateFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LiveUpdateFragment newInstance(String param1, String param2) {
        LiveUpdateFragment fragment = new LiveUpdateFragment();
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
        View view = inflater.inflate(R.layout.fragment_live_update, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        initializeData();
        mAdapter = new LiveUpdateAdapter(getActivity(), liveUpdateHelpers);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }




    private void initializeData() {
        liveUpdateHelpers = new ArrayList<>();
        liveUpdateHelpers.add(new LiveUpdateHelper("G.T Road", "Pir Zakuri - Suri Pul"));
        liveUpdateHelpers.add(new LiveUpdateHelper("Khyber Road", "Suri Pul - Aman Chowk"));
        liveUpdateHelpers.add(new LiveUpdateHelper("Charsadda Road", "Ring Road - Bacha Khan Chowk"));
        liveUpdateHelpers.add(new LiveUpdateHelper("Jail Road", "Khyber Bazar - FC Chowk"));
        liveUpdateHelpers.add(new LiveUpdateHelper("University Road", "Sifat Ghayour - Phase-3"));
        liveUpdateHelpers.add(new LiveUpdateHelper("Dalazak Road", "Not available - not available"));
        liveUpdateHelpers.add(new LiveUpdateHelper("Saddar Road", "Suri Pul - Cantt Area"));
        liveUpdateHelpers.add(new LiveUpdateHelper("Bagh e Naran Road", "Not available - not available"));
        liveUpdateHelpers.add(new LiveUpdateHelper("Warsak Road", "Not available - not available"));
        liveUpdateHelpers.add(new LiveUpdateHelper("Kohat Road", "Ramdas Chowk - Kohat Adda"));

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
