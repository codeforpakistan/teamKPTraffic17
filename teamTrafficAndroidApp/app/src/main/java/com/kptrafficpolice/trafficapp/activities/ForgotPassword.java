package com.kptrafficpolice.trafficapp.activities;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.LoginFragment;
import com.kptrafficpolice.trafficapp.utilities.CheckNetwork;
import com.kptrafficpolice.trafficapp.utilities.Configuration;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ForgotPassword extends AppCompatActivity {

    View view;
    Button btnSubmit;
    Fragment fragment;
    String strCNIC, strEmail;
    EditText etCNIC, etEmail;
    Animation shake;
    SweetAlertDialog pDialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_forgot_pass);

        sharedPreferences = getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();
        mRequestQueue = Volley.newRequestQueue(this);
        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        MultiDex.install(this);
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Wait a while");
        pDialog.setCancelable(false);
        onSubmitButton();
        customActionBar();

    }


    public void onSubmitButton() {
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                formValidation();


            }
        });
    }


    public void formValidation() {
        etCNIC = (EditText) findViewById(R.id.et_nic);
        etEmail = (EditText) findViewById(R.id.et_email);



        strCNIC = etCNIC.getText().toString().trim();


        if (strCNIC.equals("") || strCNIC.length() < 13 || strCNIC.contains("-")) {
            etCNIC.startAnimation(shake);
        } else {
            if (CheckNetwork.isInternetAvailable(this)) {
                apiCall();
            } else {
                new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops!")
                        .setContentText("You don't have internet connection")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                finish();
                            }
                        })
                        .show();
            }
        }
    }



    public void apiCall() {
        pDialog.show();
        String url = Configuration.END_POINT_LIVE + "signup/forgot_pass";
        Log.d("zma url", url);
        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        boolean status = response.contains("true");
                        if (status) {
                            pDialog.dismiss();

                            Toast.makeText(ForgotPassword.this, "Verification code sent to your email." , Toast.LENGTH_LONG).show();

                            finish();

                            Log.d("Zma response", response);

                        } else if (response.contains("false")) {
                            pDialog.dismiss();
                            etCNIC.setText("");
                            new SweetAlertDialog(ForgotPassword.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("CNIC not exists!")
                                    .show();
                        }
                        pDialog.dismiss();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                new SweetAlertDialog(ForgotPassword.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Server Error!")
                        .show();
                Log.d("zma error registration", String.valueOf(error));
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cnic", strCNIC);
                return params;
            }

        };

        jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(jsonObjRequest);
    }


    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Forgot Password");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
}
