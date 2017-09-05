package com.kptrafficpolice.trafficapp.fragments;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.utilities.Configuration;
import com.kptrafficpolice.trafficapp.utilities.TrafficEducationAdapter;
import com.kptrafficpolice.trafficapp.utilities.TrafficEducationHelper;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.thefinestartist.Base;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.thefinestartist.utils.service.ServiceUtil.getSystemService;
//raabta
//rabta

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrafficEducationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrafficEducationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrafficEducationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MaterialSearchBar searchBar;
    List<TrafficEducationHelper> data = new ArrayList<>();
    View view;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tvNoDataFound;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private OnFragmentInteractionListener mListener;
    SweetAlertDialog pDialog;
    TrafficEducationHelper trafficEducationHelper;
    private FirebaseAnalytics mFirebaseAnalytics;

    public TrafficEducationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrafficEducationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrafficEducationFragment newInstance(String param1, String param2) {
        TrafficEducationFragment fragment = new TrafficEducationFragment();
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
        view = inflater.inflate(R.layout.fragment_traffic_education, container, false);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "6");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Traffic Education Clicked");
        mFirebaseAnalytics.logEvent("Traffic_Education_List", bundle);
        tvNoDataFound = (TextView)view.findViewById(R.id.tv_no_data_found);
        tvNoDataFound.setVisibility(View.GONE);

        customActionBar();
        Base.initialize(getActivity());
        searchBar = (MaterialSearchBar) view.findViewById(R.id.searchBar);
        searchBar.setHint("Search");
        searchBar.setSpeechMode(false);
        data.clear();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                InputMethodManager imm = (InputMethodManager) getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                return false;
            }
        });
//        final SwipeRefreshLayout swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // Your code to refresh the list here.
//                // Make sure you call swipeContainer.setRefreshing(false)
//                // once the network request has completed successfully.
//                data.clear();
//                apiCall();
//                swipeContainer.setRefreshing(false);
//
//            }
//        });
//        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light);

        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Loading data");
        apiCall();
        searchEducationList();
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

    public void apiCall() {
        pDialog.show();
        String url = Configuration.END_POINT_LIVE + "traffic_education/getEducationSigns";
        final Bundle args = new Bundle();
//
//        Log.d("zma url", url);
        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            pDialog.dismiss();
                            showJSON(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            pDialog.dismiss();
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Server Error")
                                    .setConfirmText("OK")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            Fragment fragment = new MainFragment();
                                            getFragmentManager().beginTransaction().
                                                    replace(R.id.fragment_container, fragment).commit();
                                        }
                                    })
                                    .show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Server Error")
                        .setConfirmText("OK")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Fragment fragment = new MainFragment();
                                getFragmentManager().beginTransaction().
                                        replace(R.id.fragment_container, fragment).commit();
                            }
                        })
                        .show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //params.put("dl_license",strLicenseNumber);
                Log.d("zma params", String.valueOf(params));
                return params;
            }

        };
        jsonObjRequest.setRetryPolicy(new DefaultRetryPolicy(20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjRequest);
    }

    private void showJSON(String response) throws JSONException {
        JSONObject json = new JSONObject(response);

        if (json.has("data")) {
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                trafficEducationHelper = new TrafficEducationHelper();
                trafficEducationHelper.strImageTitle = jsonObject.getString("image_title");
                trafficEducationHelper.strDescriptionEnglish = jsonObject.getString("image_description_eng");
                trafficEducationHelper.strDescriptionUrdu = jsonObject.getString("image_description_urdu");
                trafficEducationHelper.strImage = jsonObject.getString("image");
                if (jsonObject.getString("image").contains(".gif")) {
                    Log.d("zma image", jsonObject.getString("image"));
                    Configuration.Traffic_Education_Gif_Image_Boolean = true;
                    trafficEducationHelper.strImage = jsonObject.getString("image");

                }
                if (jsonArray.length() == 0) {
                    pDialog.dismiss();
                    tvNoDataFound.setVisibility(View.VISIBLE);
                }

                data.add(trafficEducationHelper);
            }


            mLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(mLayoutManager);

            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            // specify an adapter (see also next example)
            mAdapter = new TrafficEducationAdapter(getActivity(), data);
            mRecyclerView.setAdapter(mAdapter);
        }

        mAdapter.notifyDataSetChanged();

    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
        mTitleTextView.setText("Traffic Education");
//        mBackArrow.setImageResource(R.drawable.back_arrow);
//        mBackArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = new MainFragment();
//                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
//            }
//        });
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    public void searchEducationList() {

        searchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence query, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + searchBar.getText());

                query = query.toString().toLowerCase();
                // Toast.makeText(TrafficSigns.this, "Query is: "+query, Toast.LENGTH_SHORT).show();
                List<TrafficEducationHelper> newData = new ArrayList<>();

                for (int j = 0; j < data.size(); j++) {
                    final String test = data.get(j).strImageTitle.toLowerCase();
                    final String test2 = data.get(j).strDescriptionEnglish.toLowerCase();

                 /*  final String text = trafficEducationHelper.strImageTitle.toLowerCase();
                    final String text2 = trafficEducationHelper.strDescriptionEnglish.toLowerCase();
                    Log.e("matched", text+"\n");*/
                    if (test.contains(query) || test2.contains(query)) {
                        newData.add(data.get(j));
                    }
                }
                // specify an adapter (see also next example)
                mAdapter = new TrafficEducationAdapter(getActivity(), newData);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
