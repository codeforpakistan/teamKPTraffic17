package com.kptrafficpolice.trafficapp.fragments.ETest;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.ETest.Database.DatabaseHelper;
import com.kptrafficpolice.trafficapp.fragments.ETest.Database.ScoresModel;
import com.kptrafficpolice.trafficapp.fragments.ETest.Database.TestPreviewCustomAdapter;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import cn.pedant.SweetAlert.SweetAlertDialog;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by DzonE on 23-Jul-18.
 */

public class ConductTest extends AppCompatActivity {

    public ArrayList<TestResultListModel> listModelArrayList = new ArrayList<TestResultListModel>();
    TextView tv_qustion, tv_option1, tv_option2, tv_option3;
    Sheet s;
    int row, progress, column, j = 1, right = 0, wrong = 0;
    String complete_String = "", status_op1, status_op2, status_op3, answer, questions_group,
            ques_group_name, formattedDate, op1_clicked = "", op2_clicked = "", op3_clicked = "",
            image, questions_group1, questions_group2;
    TestResultListModel testResultListModel;
    DatabaseHelper db;
    ImageView imageView;
    TextView tv_percentage, tv_total_score;
    LinearLayout card_layout;
    int question_no = 1;
    Date date;
    SimpleDateFormat dateFormat;
    ListView listView;
    TestPreviewCustomAdapter testPreviewCustomAdapter;
    LinearLayout linearLayout;
    ProgressBar customProgress;
    String category, user_id;
    FirebaseAnalytics mFirebaseAnalytics;
    private List<ScoresModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_etest);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "11");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Take Test Clicked");
        mFirebaseAnalytics.logEvent("Take_E_Test", bundle);


        SharedPreferences sharedPreferences = this.getSharedPreferences("com.ghosttech.kptraffic", Context.MODE_PRIVATE);
        questions_group = sharedPreferences.getString("group", "1");
        user_id = sharedPreferences.getString("user_id", "0");
        questions_group1 = sharedPreferences.getString("images_questions_group", "1");
        questions_group2 = sharedPreferences.getString("text_questions_group", "1");

        category = getIntent().getStringExtra("category");

        getCurrentDate();

        linearLayout = (LinearLayout) findViewById(R.id.llayout);
        listView = (ListView) findViewById(R.id.list);
        card_layout = (LinearLayout) findViewById(R.id.card);

        //initialize database and arraylist
        db = new DatabaseHelper(this);
        modelList = new ArrayList<>();

        tv_total_score = (TextView) findViewById(R.id.total);
        tv_percentage = (TextView) findViewById(R.id.percent);
        customProgress = (ProgressBar) findViewById(R.id.customProgress);
        tv_qustion = (TextView) findViewById(R.id.textView);
        tv_option1 = (TextView) findViewById(R.id.textView4);
        tv_option2 = (TextView) findViewById(R.id.textView5);
        tv_option3 = (TextView) findViewById(R.id.textView6);
        imageView = (ImageView) findViewById(R.id.imageView);


        tv_percentage.setText("Question #" + " " + question_no + " " + "/" + " " + "20");

        if (category.equals("mix")) {


            if (questions_group.equals("1")) {
                ques_group_name = "group1.xls";
            } else if (questions_group.equals("2")) {
                ques_group_name = "group2.xls";
            } else if (questions_group.equals("3")) {
                ques_group_name = "group3.xls";
            } else if (questions_group.equals("4")) {
                ques_group_name = "group4.xls";
            } else if (questions_group.equals("5")) {
                ques_group_name = "group5.xls";
            } else if (questions_group.equals("6")) {
                ques_group_name = "group6.xls";
            } else if (questions_group.equals("7")) {
                ques_group_name = "group7.xls";
            }

        } else if (category.equals("text")) {

            if (questions_group2.equals("1")) {
                ques_group_name = "text1.xls";
            } else if (questions_group2.equals("2")) {
                ques_group_name = "text2.xls";
            } else if (questions_group2.equals("3")) {
                ques_group_name = "text3.xls";
            }

        } else if (category.equals("images")) {

            if (questions_group1.equals("1")) {
                ques_group_name = "images1.xls";
            } else if (questions_group1.equals("2")) {
                ques_group_name = "images2.xls";
            } else if (questions_group1.equals("3")) {
                ques_group_name = "images3.xls";
            } else if (questions_group1.equals("4")) {
                ques_group_name = "images4.xls";
            }
        }

        try {

            AssetManager am = getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
            InputStream is = am.open(ques_group_name);
            Workbook wb = Workbook.getWorkbook(is);
            s = wb.getSheet(0);
            row = s.getRows();
            column = s.getColumns();
            complete_String = "";

            // call function to load question 1 when activity creates
            order();

        } catch (Exception e) {

        }


        tv_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //set progress
                setProgress();

                //get selected option and match with original answer
                op1_clicked = tv_option1.getText().toString().trim();
                if (answer.equals(op1_clicked)) {

                    right = right + 1; //increment with right if answer is correct
                    setListData();    //insert selected data to arraylist to pass to testPreviewCustomAdapter to generare listview
                    order();         //load new question and options
                } else {

                    wrong = wrong + 1; //increment with wrong if answer is wrong
                    setListData();
                    order();
                }


            }
        });


        tv_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setProgress();

                op2_clicked = tv_option2.getText().toString().trim();
                if (answer.equals(op2_clicked)) {
                    right = right + 1;
                    setListData();
                    order();
                } else {
                    wrong = wrong + 1;
                    setListData();
                    order();
                }

            }
        });


        tv_option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setProgress();

                op3_clicked = tv_option3.getText().toString().trim();
                if (answer.equals(op3_clicked)) {
                    right = right + 1;
                    setListData();
                    order();
                } else {
                    wrong = wrong + 1;
                    setListData();
                    order();
                }

            }
        });


    }


    public void order() {

        int i;

        for (i = 0; i < j; i++) {

            if (i < 20) {  //total number of questions 20

                for (int c = 0; c < column; c++) {

                    Cell z = s.getCell(c, i); //column and rows
                    complete_String = complete_String + z.getContents();

                }

                //method for string manipulation
                StringTokenizer stringTokenizer = new StringTokenizer(complete_String, ",");
                String ques = stringTokenizer.nextToken();
                tv_qustion.setText(ques);
                String one = stringTokenizer.nextToken();
                tv_option1.setText(one);
                String two = stringTokenizer.nextToken();
                tv_option2.setText(two);
                String three = stringTokenizer.nextToken();
                tv_option3.setText(three);
                answer = stringTokenizer.nextToken();
                image = stringTokenizer.nextToken();

                //set image to imageview from getting its name from excel sheet
                Resources res = getResources();
                String mDrawableName = image;
                //if image is not available hide imageview
                if (image.equals("na")) {
                    imageView.setVisibility(View.INVISIBLE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
                    Drawable drawable = res.getDrawable(resID);
                    imageView.setImageDrawable(drawable);
                }

                complete_String = "";

            } else {


                //alert dialog
                new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Test Completed")
                        .show();


                //save to database
                db.addName(right + "", formattedDate + "", Integer.parseInt(user_id));

                //set score to textview
                String total = "Your Score " + right + " " + "Out of" + " " + "20";
                tv_total_score.setText(total);

                ScoresModel n = new ScoresModel(right + "", wrong + "");
                modelList.add(n);


                //hide/unhide views
                tv_percentage.setVisibility(View.GONE);
                customProgress.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
                card_layout.setVisibility(View.VISIBLE);


                //assign arraylist to custom testPreviewCustomAdapter to populate listview
                testPreviewCustomAdapter = new TestPreviewCustomAdapter(this, listModelArrayList);
                listView.setAdapter(testPreviewCustomAdapter);

            }

        }

        j = j + 1;

    }


    /****** Function to set data in ArrayList *************/
    public void setListData() {


        //get values from all textviews
        String ques_, op1_, op2_, op3_, op4_;
        ques_ = tv_qustion.getText().toString().trim();
        op1_ = tv_option1.getText().toString().trim();
        op2_ = tv_option2.getText().toString().trim();
        op3_ = tv_option3.getText().toString().trim();


        //check the right and wrong answer to set statuses

        //if answer selected is matched
        if (answer.equals(op1_) && !op1_clicked.equals("")) {
            status_op1 = "right";
            //if answer selected not matched
        } else if (!answer.equals(op1_) && !op1_clicked.equals("")) {
            status_op1 = "wrong";
            //if wrong answer selected show right answer to user
        } else if (answer.equals(op1_) && op1_clicked.equals("")) {
            status_op1 = "right";
            //if not selected
        } else {
            status_op1 = "not_selected";
        }

        if (answer.equals(op2_) && !op2_clicked.equals("")) {
            status_op2 = "right";
        } else if (!answer.equals(op2_) && !op2_clicked.equals("")) {
            status_op2 = "wrong";
        } else if (answer.equals(op2_) && op2_clicked.equals("")) {
            status_op2 = "right";
        } else {
            status_op2 = "not_selected";
        }

        if (answer.equals(op3_) && !op3_clicked.equals("")) {
            status_op3 = "right";
        } else if (!answer.equals(op3_) && !op3_clicked.equals("")) {
            status_op3 = "wrong";
        } else if (answer.equals(op3_) && op3_clicked.equals("")) {
            status_op3 = "right";
        } else {
            status_op3 = "not_selected";
        }


        //Firstly take data in model object
        testResultListModel = new TestResultListModel(ques_, op1_, op2_, op3_, status_op1, status_op2, status_op3, image);


        //Take Model Object in ArrayList
        listModelArrayList.add(testResultListModel);


        //make all strings clear
        status_op1 = "";
        status_op2 = "";
        status_op3 = "";

        op1_clicked = "";
        op2_clicked = "";
        op3_clicked = "";
        image = "";

    }


    public void setProgress() {

        //change progress bar status
        progress = customProgress.getProgress();
        progress = progress + 5;
        question_no = question_no + 1;
        tv_percentage.setText("Question #" + " " + question_no + " " + "/" + " " + "20");

        customProgress.setProgress(progress);
    }


    public void getCurrentDate() {

        date = Calendar.getInstance().getTime();
        System.out.println("Current time => " + date);

        dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        formattedDate = dateFormat.format(date);

    }


    @Override
    public void onBackPressed() {

        if (progress < 100) {

            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Leave Test!")
                    .setContentText("Are you Sure you want to Leave Test")
                    .setConfirmText("Yes,Leave!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            ConductTest.this.finish();
                        }
                    })
                    .setCancelText("No")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                        }
                    }).show();

        } else
            super.onBackPressed();
    }
}