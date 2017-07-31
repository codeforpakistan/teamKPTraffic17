package com.kptrafficpolice.trafficapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.activities.MainDrawerActivity;
import com.kptrafficpolice.trafficapp.utilities.Configuration;
import com.kptrafficpolice.trafficapp.utilities.MyComplaintsHelper;
import com.kptrafficpolice.trafficapp.utilities.MyComplaintsListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyComplaintsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyComplaintsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyComplaintsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    List<MyComplaintsHelper> list;
    MyComplaintsListAdapter complaintsListAdapter;
    SweetAlertDialog pDialog;
    private String strUserID;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    TextView mTitleTextView;

    private OnFragmentInteractionListener mListener;

    public MyComplaintsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyComplaintsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyComplaintsFragment newInstance(String param1, String param2) {
        MyComplaintsFragment fragment = new MyComplaintsFragment();
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
        view = inflater.inflate(R.layout.fragment_my_complaints, container, false);
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Wait a while");
        pDialog.setCancelable(false);
        pDialog.show();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        sharedPreferences = getActivity().getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();
        strUserID = sharedPreferences.getString("user_id", "");
        Log.d("zma user id",strUserID);//problem with id, it should be one, but sending 2
        customActionBar();
        getData(strUserID);
        list = new ArrayList<>();
        complaintsListAdapter = new MyComplaintsListAdapter(getActivity(), list);
        recyclerView.setAdapter(complaintsListAdapter);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void getData(final String strUserID) {
        final String url = Configuration.END_POINT_LIVE + "complaints/myComplaints?user_id=" + strUserID;
        Log.d("zma url try na", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("zma comp response", String.valueOf(response));
                try {

                    Log.d("zma url", url + "\n" + response.getBoolean("status") + "\n" + String.valueOf(response));
                    list.clear();

                    if (response.getBoolean("status")) {
                        pDialog.dismiss();
                        JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject complaintObject = data.getJSONObject(i);
                            MyComplaintsHelper helper = new MyComplaintsHelper();
                            helper.setStrComplaintID(complaintObject.getString("complaint_id"));
                            helper.setStrComplaintStatus(complaintObject.getString("status"));
                            helper.setStrDescription(complaintObject.getString("description"));
                            helper.setStrComplaintType(complaintObject.getString("complaint_type"));
                            helper.setStrLatitude(complaintObject.getString("latitude"));
                            helper.setStrLongitude(complaintObject.getString("longitude"));
                            helper.setStrDate(complaintObject.getString("dated"));
                            helper.setStrImage(complaintObject.getString("image"));

                            helper.setStrVideo(complaintObject.getString("video"));
                            if (complaintObject.getString("video").contains("mp4")) {
                                Log.d("zma video", complaintObject.getString("video"));
                            }
                            list.add(helper);
                        }
                        complaintsListAdapter.notifyDataSetChanged();
                    } else {
                        pDialog.dismiss();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Oops")
                                .setContentText("No complaints found")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Fragment fragment = new MainFragment();
                                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                                        sweetAlertDialog.dismiss();

                                    }
                                }).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Oops")
                            .setContentText("Please Check your internet connection")
                            .setConfirmText("Refresh")
                            .setCancelText("Exit App")
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    getActivity().finish();

                                }
                            })
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    Fragment fragment = new MyComplaintsFragment();
                                    getFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                                }
                            })
                            .show();
                   // Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(getActivity(), "Something went wrong, try later", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
              //  params.put("user_id", strUserID);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }
    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Complaints List");
        //  ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
        //mBackArrow.setImageResource(R.drawable.map_pointer);
//        mBackArrow.setVisibility(View.GONE);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }
}
