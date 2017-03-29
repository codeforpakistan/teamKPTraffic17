package com.ghosttech.kptrafficapp.fragments;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghosttech.kptrafficapp.R;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
        SpannableString content = new SpannableString("Forgot Password?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        tvForgotPassword = (TextView) view.findViewById(R.id.tv_forgot_password);
        tvForgotPassword.setText(content);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
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
                /*fragment = new MainFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).
                        commit();*/
            }
        });
    }

    public void formValidation() {
        etCNIC = (EditText) view.findViewById(R.id.et_cnic_login);
        etPassword = (EditText) view.findViewById(R.id.et_password_login);
        String strPassword = etPassword.getText().toString();
        String strCNIC = etCNIC.getText().toString();
        if (strCNIC.equals("") || strCNIC.length() < 13) {
            etCNIC.setError("Please insert your Complete CNIC");
        } else if (strPassword.equals("") || strPassword.length() < 6) {
            etPassword.setError("Password should be six characters long");
        }
    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
        mTitleTextView.setText("Sign In");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new RegistrationFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }
}
