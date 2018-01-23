package com.kptrafficpolice.trafficapp.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.thefinestartist.utils.service.ServiceUtil.getSystemService;

public class TrafficEducationFragment extends Fragment {

    List<TrafficEducationHelper> ListTrafficEducation;
    View view;
    TextView tvNoDataFound;
    SweetAlertDialog pDialog;
    TrafficEducationHelper trafficEducationHelper;
    private MaterialSearchBar searchBar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GridView mGridView;
    private TrafficEducationAdapter mAdapter;
    private FirebaseAnalytics mFirebaseAnalytics;

    public TrafficEducationFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TrafficEducationFragment newInstance(String param1, String param2) {
        TrafficEducationFragment fragment = new TrafficEducationFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("ClickableViewAccessibility")
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
        tvNoDataFound = (TextView) view.findViewById(R.id.tv_no_data_found);
        tvNoDataFound.setVisibility(View.GONE);
        customActionBar();
        Base.initialize(getActivity());
        searchBar = (MaterialSearchBar) view.findViewById(R.id.searchBar);
        searchBar.setHint("Search");
        searchBar.setSpeechMode(false);
        // ListTrafficEducation.clear();
        mGridView = (GridView) view.findViewById(R.id.gridView);
        mGridView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                InputMethodManager imm = (InputMethodManager) getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                return false;
            }
        });
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Loading data");
        //apiCall();
        ListTrafficEducation = new ArrayList<>();
        mAdapter = new TrafficEducationAdapter(getActivity(), R.layout.traffic_education_items, ListTrafficEducation);
        mGridView.setAdapter(mAdapter);
        apiCall();
        searchEducationList();
        return view;
    }
    public void apiCall() {
        pDialog.show();
        String url = Configuration.END_POINT_LIVE + "traffic_education/getEducationSigns";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("zma traf respo", String.valueOf(response));
                    JSONArray data = response.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        pDialog.dismiss();
                        JSONObject jsonObject = data.getJSONObject(i);
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
                        if (data.length() == 0) {
                            pDialog.dismiss();
                            tvNoDataFound.setVisibility(View.VISIBLE);
                        }
                        ListTrafficEducation.add(trafficEducationHelper);
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("zma exception edu", String.valueOf(e));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonObjectRequest);
    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Traffic Education");
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
                for (int j = 0; j < ListTrafficEducation.size(); j++) {
                    final String test = ListTrafficEducation.get(j).strImageTitle.toLowerCase();
                    final String test2 = ListTrafficEducation.get(j).strDescriptionEnglish.toLowerCase();
                    if (test.contains(query) || test2.contains(query)) {
                        newData.add(ListTrafficEducation.get(j));
                    }
                }
                // specify an adapter (see also next example)
                 mAdapter = new TrafficEducationAdapter(getActivity(),  R.layout.traffic_education_items, newData);
                mGridView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
//        ListTrafficEducation.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new TrafficEducationAdapter(getActivity(), R.layout.traffic_education_items, ListTrafficEducation);
        mGridView.setAdapter(mAdapter);
        apiCall();
        Log.d("zma list resume  ", "de");

    }
}
