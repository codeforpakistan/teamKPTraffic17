package com.kptrafficpolice.trafficapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.activities.MainDrawerActivity;
import com.kptrafficpolice.trafficapp.utilities.CheckNetwork;
import com.kptrafficpolice.trafficapp.utilities.Configuration;
import com.thefinestartist.Base;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.thefinestartist.utils.service.ServiceUtil.getSystemService;

public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnSubmit;
    EditText etCNIC;
    View view;
    Fragment fragment;
    private OnFragmentInteractionListener mListener;
    RequestQueue mRequestQueue;
    String strCNIC, strUserID;
    SweetAlertDialog pDialog;
    TextView tvSkip;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

        view = inflater.inflate(R.layout.fragment_login, container, false);
        Base.initialize(getActivity());
        sharedPreferences = getActivity().getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();
        customActionBar();
        tvSkip = (TextView) view.findViewById(R.id.tvSkip);
        SpannableString contentRegister = new SpannableString("Register Here");
        contentRegister.setSpan(new UnderlineSpan(), 0, contentRegister.length(), 0);
        tvSkip.setText(contentRegister);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("getting login");
        pDialog.setCancelable(false);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new RegistrationFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("tag")
                        .commit();
            }
        });
        onButtonClick();
        ScrollView scrollView = (ScrollView) view.findViewById(R.id.sv_login);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return false;
            }
        });
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

    public void onButtonClick() {
        btnSubmit = (Button) view.findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formValidation();
            }
        });
    }

    public void formValidation() {
        etCNIC = (EditText) view.findViewById(R.id.et_cnic_login);
        strCNIC = etCNIC.getText().toString();
        Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        if (strCNIC.equals("") || strCNIC.length() < 13 || strCNIC.contains("-")) {
            etCNIC.startAnimation(shake);
            Snackbar.make(view, "Wrong CNIC ", Snackbar.LENGTH_LONG).show();
        } else {
            pDialog.show();
            if (CheckNetwork.isInternetAvailable(getActivity())) {
                apiCall();
            } else {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Oops")
                        .setContentText("You don't have internet connection!")
                        .setConfirmText("Refresh")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                getActivity().startActivity(new Intent(getActivity(), MainDrawerActivity.class));
                            }
                        })
                        .show();
            }

        }
    }

    public void apiCall() {
        String url = Configuration.END_POINT_LIVE + "login/userlogin";
        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("true")) {
                            pDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject temp = jsonArray.getJSONObject(i);
                                    strUserID = temp.getString("user_id");
                                    Log.d("zma user id login", strUserID);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            editor.putString("user_id", strUserID).commit();
                            editor.putString("true", strCNIC).commit();
                            startActivity(new Intent(getActivity(), MainDrawerActivity.class));
                            getActivity().finish();

                            Log.d("Zma response", response + "\n" + strUserID);

                        } else if (response.contains("false")) {
                            pDialog.dismiss();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Invalid CNIC!")
                                    .show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
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
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
//        mBackArrow.setImageResource(R.mipmap.ic_launcher);
        mTitleTextView.setText("Sign In");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
}
