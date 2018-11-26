package com.kptrafficpolice.trafficapp.fragments.ETest;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kptrafficpolice.trafficapp.R;


public class SelectTestCategory extends Fragment {

    Button btn_text_questions,
            btn_sign_question, btn_mix_questions;

    public SelectTestCategory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_test_category, container, false);

        customActionBar();

        btn_mix_questions = (Button) view.findViewById(R.id.mix);
        btn_text_questions = (Button) view.findViewById(R.id.textt);
        btn_sign_question = (Button) view.findViewById(R.id.sign);


        btn_mix_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences
                        ("com.ghosttech.kptraffic", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String group_num = sharedPreferences.getString("group", "0");


                if (group_num.equals("0")) {
                    editor.putString("group", "1");
                    editor.apply();
                } else {
                    switch (group_num) {
                        case "1":
                            editor.putString("group", "2");
                            editor.apply();
                            break;
                        case "2":
                            editor.putString("group", "3");
                            editor.apply();
                            break;
                        case "3":
                            editor.putString("group", "4");
                            editor.apply();
                            break;
                        case "4":
                            editor.putString("group", "5");
                            editor.apply();
                            break;
                        case "5":
                            editor.putString("group", "6");
                            editor.apply();
                            break;
                        case "6":
                            editor.putString("group", "7");
                            editor.apply();
                            break;
                        case "7":
                            editor.putString("group", "1");
                            editor.apply();
                            break;
                    }
                }

                Intent intent = new Intent(getActivity(), ConductTest.class);
                intent.putExtra("category", "mix");
                startActivity(intent);
            }
        });


        btn_text_questions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences
                        ("com.ghosttech.kptraffic", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String group_num = sharedPreferences.getString("text_questions_group", "0");


                if (group_num.equals("0")) {
                    editor.putString("text_questions_group", "1");
                    editor.apply();
                } else {
                    switch (group_num) {
                        case "1":
                            editor.putString("text_questions_group", "2");
                            editor.apply();
                            break;
                        case "2":
                            editor.putString("text_questions_group", "3");
                            editor.apply();
                            break;
                        case "3":
                            editor.putString("text_questions_group", "1");
                            editor.apply();
                            break;
                    }
                }

                Intent intent = new Intent(getActivity(), ConductTest.class);
                intent.putExtra("category", "text");
                startActivity(intent);
            }
        });


        btn_sign_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences
                        ("com.ghosttech.kptraffic", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String group_num = sharedPreferences.getString("images_questions_group", "0");


                if (group_num.equals("0")) {
                    editor.putString("images_questions_group", "1");
                    editor.apply();
                } else {
                    switch (group_num) {
                        case "1":
                            editor.putString("images_questions_group", "2");
                            editor.apply();
                            break;
                        case "2":
                            editor.putString("images_questions_group", "3");
                            editor.apply();
                            break;
                        case "3":
                            editor.putString("images_questions_group", "4");
                            editor.apply();
                            break;
                        case "4":
                            editor.putString("images_questions_group", "1");
                            editor.apply();
                            break;
                    }
                }

                Intent intent = new Intent(getActivity(), ConductTest.class);
                intent.putExtra("category", "images");
                startActivity(intent);
            }
        });


        return view;

    }



    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Test Category");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

}