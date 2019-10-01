package com.kptrafficpolice.trafficapp.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.utilities.Configuration;
import com.kptrafficpolice.trafficapp.utilities.MyComplaintsHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
//raabta
//rabta
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyComplaintsDetailedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyComplaintsDetailedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyComplaintsDetailedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView ivComplaintImage, ivMapPointer;
    VideoView vvMyComplaints;
    TextView tvComplaintType, tvComplaintDescription, tvComplaintDate, tvComplaintStatus
            ,TvStatus1, TvStatus2, TvStatus3, TvResponse1, TvResponse2, TvResponse3;
    String complaint_id, arg_status;
    SweetAlertDialog pDialog;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private FirebaseAnalytics mFirebaseAnalytics;
    LinearLayout feedbackfrom1, feedbackfrom2, feedbackfrom3;
    List<MyComplaintsHelper> list;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    String[] complaint_response, complaint_status;

    public MyComplaintsDetailedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyComplaintsDetailedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyComplaintsDetailedFragment newInstance(String param1, String param2) {
        MyComplaintsDetailedFragment fragment = new MyComplaintsDetailedFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_complaints_detailed, container, false);
        final Bundle args = getArguments();
        Log.d("zma args detailed", String.valueOf(args.get("description")));
        tvComplaintType = (TextView) view.findViewById(R.id.tv_complaint_type);
        tvComplaintDescription = (TextView) view.findViewById(R.id.tv_complaint_description);
        tvComplaintDate = (TextView) view.findViewById(R.id.tv_complaint_date);
        tvComplaintStatus = (TextView) view.findViewById(R.id.tv_complaint_status);
        ivComplaintImage = (ImageView) view.findViewById(R.id.iv_detail_title_image);
        ivMapPointer = (ImageView) view.findViewById(R.id.iv_detail_marker);
        vvMyComplaints = (VideoView) view.findViewById(R.id.vv_my_complaints);
        feedbackfrom1 = (LinearLayout) view.findViewById(R.id.feedbackform1);
        feedbackfrom2 = (LinearLayout) view.findViewById(R.id.feedbackform2);
        feedbackfrom3 = (LinearLayout) view.findViewById(R.id.feedbackform3);
        TvResponse1 = (TextView) view.findViewById(R.id.response1);
        TvResponse2 = (TextView) view.findViewById(R.id.response2);
        TvResponse3 = (TextView) view.findViewById(R.id.response3);
        TvStatus1 = (TextView) view.findViewById(R.id.status1);
        TvStatus2 = (TextView) view.findViewById(R.id.status2);
        TvStatus3 = (TextView) view.findViewById(R.id.status3);

        feedbackfrom2.setVisibility(View.GONE);
        feedbackfrom3.setVisibility(View.GONE);

        list = new ArrayList<>();
        complaint_id = args.getString("complaint_id");
        arg_status = args.getString("status");
        TvStatus1.setText(arg_status);
        getData(complaint_id);


        customActionBar();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
        Bundle bundle = new Bundle();

        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "2");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "My Complaint List");
        mFirebaseAnalytics.logEvent("My_Complaint_screen", bundle);

        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Loading Video");
        WindowManager.LayoutParams dialogPosition = pDialog.getWindow().getAttributes();
        dialogPosition.gravity = Gravity.TOP | Gravity.CENTER;
        dialogPosition.y = 120;   //y position
        tvComplaintType.setText(args.getString("complaint_type"));
        tvComplaintDescription.setText(args.getString("description"));
        tvComplaintStatus.setText(args.getString("status"));
        tvComplaintDate.setText(args.getString("date"));
        //ivComplaintImage.setImageURI(Uri.parse(args.getString("image")));
        vvMyComplaints.setVisibility(View.GONE);
        ivComplaintImage.setVisibility(View.GONE);
        if (!args.getString("video").equals("") || !args.getString("image").equals("")) {
            if (!args.getString("video").equals("")) {
                pDialog.show();
                vvMyComplaints.setVisibility(View.VISIBLE);
                try {
                    // Start the MediaController
                    MediaController mediacontroller = new MediaController(
                            getActivity());
                    mediacontroller.setAnchorView(vvMyComplaints);
                    // Get the URL from String VideoURL
                    Uri video = Uri.parse("http://103.240.220.76/kptraffic/uploads/videos/" + args.getString("video"));
                    Log.d("zma video uri",String.valueOf(video));
                    vvMyComplaints.setMediaController(mediacontroller);
                    vvMyComplaints.setVideoURI(video);

                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }

                vvMyComplaints.requestFocus();
                vvMyComplaints.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    // Close the progress bar and play the video
                    public void onPrepared(MediaPlayer mp) {
                         pDialog.dismiss();
                    }
                });

            } else if (!args.getString("image").equals("")) {
                ivComplaintImage.setVisibility(View.VISIBLE);
                Glide.with(getActivity()).load(Configuration.END_POINT_LIVE+"uploads/images/" + args.getString("image")).into(ivComplaintImage);
            }
        }


//        String full_url = "http://103.240.220.76/kptraffic/uploads/images/" + args.getString("image");
//        Log.d("zma image adapter",full_url);

        ivMapPointer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "http://maps.google.com/?q=" +
                        String.valueOf(args.getString("lat")) + "," + String.valueOf(args.getString("lon"));
                Log.d("zma url complaint", link);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                getActivity().startActivity(intent);
            }
        });

        return view;
    }



    public void getData(final String strUserID) {
        final String url = Configuration.END_POINT_LIVE + "complaints/complaint_response?complaint_id=" + strUserID;
        Log.d("zma url try na", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("zma comp response", String.valueOf(response));
                try {

                    Log.d("zma url", url + "\n" + response.getBoolean("status") + "\n" + String.valueOf(response));

                    if (response.getBoolean("status")) {
                        pDialog.dismiss();
                        JSONArray data = response.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject complaintObject = data.getJSONObject(i);
                            MyComplaintsHelper helper = new MyComplaintsHelper();
                            helper.setStrComplaintID(complaintObject.getString("complaint_id"));
                            helper.setStrDescription(complaintObject.getString("complaint_response"));
                            helper.setStrComplaintStatus(complaintObject.getString("response_status"));

                            list.add(helper);
                        }

                        complaint_response = new String[list.size()];
                        complaint_status = new String[list.size()];

                        int list_size = list.size();
                        for (int i=0; i<list_size; i++){

                            complaint_response[i] = list.get(i).getStrDescription();
                            setStatus(i);
                        }

                        if (complaint_status.length == 3){
                            feedbackfrom3.setVisibility(View.VISIBLE);
                            feedbackfrom2.setVisibility(View.VISIBLE);
                            TvStatus3.setText(complaint_status[2]);
                            TvResponse3.setText(complaint_response[2]);
                            TvStatus2.setText(complaint_status[1]);
                            TvResponse2.setText(complaint_response[1]);
                            TvStatus1.setText(complaint_status[0]);
                            TvResponse1.setText(complaint_response[0]);
                        }
                        else if (complaint_status.length == 2){
                            feedbackfrom2.setVisibility(View.VISIBLE);
                            TvStatus2.setText(complaint_status[1]);
                            TvResponse2.setText(complaint_response[1]);
                            TvStatus1.setText(complaint_status[0]);
                            TvResponse1.setText(complaint_response[0]);
                        }
                        else {
                            TvStatus1.setText(complaint_status[0]);
                            TvResponse1.setText(complaint_response[0]);
                        }

                    } else {
                        pDialog.dismiss();

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
                                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
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
        request.setTag("request");

        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

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
        requestQueue.cancelAll("request");
        mListener = null;
    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        mTitleTextView.setText("Complaint Detail");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
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


    public  void setStatus(int position){

        if (list.get(position).getStrComplaintStatus().equals("1")){
            complaint_status[position] = "Completed";
        }else if (list.get(position).getStrComplaintStatus().equals("2")){
            complaint_status[position] = "Pending";
        }else if (list.get(position).getStrComplaintStatus().equals("3")){
            complaint_status[position] = "In Progress";
        }else if (list.get(position).getStrComplaintStatus().equals("4")){
            complaint_status[position] = "Irrelevant";
        }else if (list.get(position).getStrComplaintStatus().equals("5")){
            complaint_status[position] = "Not Understandable";
        }
    }
}
