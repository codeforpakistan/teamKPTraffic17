package com.ghosttech.kptrafficapp.fragments;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ghosttech.kptrafficapp.R;
import com.ghosttech.kptrafficapp.utilities.Configuration;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class LoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnSubmit;
    EditText etCNIC, etPassword;
    View view;
    TextView tvForgotPassword;
    Fragment fragment;
    ProgressDialog dialog;
    private OnFragmentInteractionListener mListener;
    RequestQueue mRequestQueue;
    String strCNIC, strPassword;
    TextView tvSkip;
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
        customActionBar();
        tvSkip = (TextView) view.findViewById(R.id.tvSkip);
        SpannableString contentRegister = new SpannableString("Register here");
        contentRegister.setSpan(new UnderlineSpan(), 0, contentRegister.length(), 0);
        tvSkip.setText(contentRegister);
        mRequestQueue = Volley.newRequestQueue(getActivity());
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new RegistrationFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });
        onButtonClick();
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
                apiCall();
                fragment = new MainFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                        commit();
            }
        });
    }

    public void formValidation() {
        etCNIC = (EditText) view.findViewById(R.id.et_cnic_login);
        etPassword = (EditText) view.findViewById(R.id.et_password_login);
        strPassword = etPassword.getText().toString();
        strCNIC = etCNIC.getText().toString();
        Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        if (strCNIC.equals("") || strCNIC.length() < 13) {
            etCNIC.startAnimation(shake);
        } else if (strPassword.equals("") || strPassword.length() < 6) {
            etPassword.startAnimation(shake);
        }
    }

    public void apiCall() {
        String url = Configuration.END_POINT_LIVE + "/kp-traffic-police/login/?cnic=" + strCNIC + "&password=" + strPassword;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("status")) {
                        fragment = new MainFragment();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).
                                commit();
                        Log.d("zma status registration", String.valueOf(response.getBoolean("status")));
                        dialog.dismiss();

                    }else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("zma error registration", String.valueOf(error));

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(request);
    }



    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
        mBackArrow.setImageResource(R.mipmap.ic_launcher);
        mTitleTextView.setText("Sign In");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
}
