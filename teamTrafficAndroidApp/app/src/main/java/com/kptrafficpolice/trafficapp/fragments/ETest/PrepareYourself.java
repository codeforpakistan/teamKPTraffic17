package com.kptrafficpolice.trafficapp.fragments.ETest;

import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.ETest.Database.TestPreviewCustomAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class PrepareYourself extends Fragment {

    public ArrayList<TestResultListModel> resultModelsArraylist = new ArrayList<TestResultListModel>();
    TestPreviewCustomAdapter testPreviewCustomAdapter;
    ListView listView;
    Sheet sheet;
    int row, column;
    String complete_String = "";
    int j = 1, i, pos, max;
    TestResultListModel testResultListModel;
    String op1_status, op2_status, op3_status;
    FirebaseAnalytics mFirebaseAnalytics;

    Button left, right;
    TextView op1, op2, op3, ques;

    public PrepareYourself() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prepare_yourself, container, false);

//        left = (Button) view.findViewById(R.id.btn1);
//        right = (Button) view.findViewById(R.id.btn2);
//        ques = (TextView) view.findViewById(R.id.ques);
//        op1 = (TextView) view.findViewById(R.id.op1);
//        op2 = (TextView) view.findViewById(R.id.op2);
//        op3 = (TextView) view.findViewById(R.id.op3);
//
//        left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pos = pos - 1;
//
//                if (pos >= 0) {
//
//                    ques.setText( resultModelsArraylist.get(pos).getQuestion());
//                    op1.setText( resultModelsArraylist.get(pos).getOp1());
//                    op2.setText( resultModelsArraylist.get(pos).getOp2());
//                    op3.setText( resultModelsArraylist.get(pos).getOp3());
//
//                } else
//                    pos = pos + 1;
//            }
//        });
//
//
//        right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                pos = pos + 1;
//
//                if (pos < 5) {
//
//                    ques.setText( resultModelsArraylist.get(pos).getQuestion());
//                    op1.setText( resultModelsArraylist.get(pos).getOp1());
//                    op2.setText( resultModelsArraylist.get(pos).getOp2());
//                    op3.setText( resultModelsArraylist.get(pos).getOp3());
//                } else {
//                    pos = pos - 1;
//                }
//            }
//        });


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "12");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "E-Test Preparation Clicked");
        mFirebaseAnalytics.logEvent("E_Test_Preparation", bundle);


        listView = view.findViewById(R.id.list);

        customActionBar();

        run_excelSheet();


        return view;
    }


    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Test Preparation");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }


    public void load_excelSheet() {


        for (i = 0; i < 141; i++) {

            if (i < 140) {  //total number of questions 141

                for (int c = 0; c < column; c++) {

                    Cell z = sheet.getCell(c, i); //column and rows
                    complete_String = complete_String + z.getContents();

                    Log.e("complete", complete_String);
                }

                //method for string manipulation
                StringTokenizer stringTokenizer = new StringTokenizer(complete_String, ",");
                String ques = stringTokenizer.nextToken();
                String one = stringTokenizer.nextToken();
                String two = stringTokenizer.nextToken();
                String three = stringTokenizer.nextToken();
                String answer = stringTokenizer.nextToken();
                String image = stringTokenizer.nextToken();

                if (answer.equals(one)) {
                    op1_status = "right";
                    op2_status = "na";
                    op3_status = "na";
                } else if (answer.equals(two)) {
                    op1_status = "na";
                    op2_status = "right";
                    op3_status = "na";
                } else if (answer.equals(three)) {
                    op1_status = "na";
                    op2_status = "na";
                    op3_status = "right";
                }

                testResultListModel = new TestResultListModel(ques, one, two, three, op1_status,
                        op2_status, op3_status, image);
                resultModelsArraylist.add(testResultListModel);


                complete_String = "";

            } else {


//                assign arraylist to custom testPreviewCustomAdapter to populate listview
                testPreviewCustomAdapter = new TestPreviewCustomAdapter(getActivity(), resultModelsArraylist);
                listView.setAdapter(testPreviewCustomAdapter);
//
//                max = resultModelsArraylist.size();
//                ques.setText( resultModelsArraylist.get(0).getQuestion());
//                op1.setText( resultModelsArraylist.get(0).getOp1());
//                op2.setText( resultModelsArraylist.get(0).getOp2());
//                op3.setText( resultModelsArraylist.get(0).getOp3());

            }

        }

        i = i + 1;


    }


    public void run_excelSheet() {


        try {

//            AssetManager am = getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
            AssetManager am = getActivity().getAssets();
            InputStream is = am.open("all_questions.xls");
            Workbook wb = Workbook.getWorkbook(is);
            sheet = wb.getSheet(0);
            row = sheet.getRows();
            column = sheet.getColumns();
            complete_String = "";

            // call function to load question 1 when activity creates
            load_excelSheet();

        } catch (Exception e) {

            Toast.makeText(getActivity(), e + "\n" + "error", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
