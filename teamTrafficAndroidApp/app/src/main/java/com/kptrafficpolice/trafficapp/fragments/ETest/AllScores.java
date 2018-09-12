package com.kptrafficpolice.trafficapp.fragments.ETest;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.ETest.Database.DatabaseHelper;
import com.kptrafficpolice.trafficapp.fragments.ETest.Database.ScoresAdapter;
import com.kptrafficpolice.trafficapp.fragments.ETest.Database.ScoresModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by DzonE on 23-Jul-18.
 */

public class AllScores extends Fragment {

    ListView listView;
    DatabaseHelper databaseHelper;
    ScoresAdapter scores_adapter;
    FirebaseAnalytics mFirebaseAnalytics;
    String user_id;
    private List<ScoresModel> scoresModelList;

    public AllScores() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scores, container, false);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.ghosttech.kptraffic", Context.MODE_PRIVATE);
        user_id = sharedPreferences.getString("user_id", "0");


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "10");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Test Scores Clicked");
        mFirebaseAnalytics.logEvent("E_Test_Scores", bundle);


        listView = view.findViewById(R.id.list);

        databaseHelper = new DatabaseHelper(getActivity());
        scoresModelList = new ArrayList<>();

        //load listView of btn_scores from database
        LoadScores();


        return view;
    }


    //load listView of btn_scores from database
    private void LoadScores() {
        scoresModelList.clear();
        Cursor cursor = databaseHelper.getNames(Integer.parseInt(user_id));
        if (cursor.moveToFirst()) {
            do {
                ScoresModel name = new ScoresModel(
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_Score)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE))
                );
                scoresModelList.add(name);
            } while (cursor.moveToNext());
        }

        Collections.reverse(scoresModelList); //to reverse listview pass listView through collection
        scores_adapter = new ScoresAdapter(getActivity(), R.layout.scores_item, scoresModelList);
        listView.setAdapter(scores_adapter);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}

