package com.kptrafficpolice.trafficapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.kptrafficpolice.trafficapp.R;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.Locale;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView ivComplaintImage, ivMapPointer;
    VideoView vvMyComplaints;
    TextView tvComplaintType, tvComplaintDescription, tvComplaintDate, tvComplaintStatus;


    private OnFragmentInteractionListener mListener;

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
        tvComplaintType.setText(args.getString("complaint_type"));
        tvComplaintDescription.setText(args.getString("description"));
        tvComplaintStatus.setText(args.getString("status"));
        tvComplaintDate.setText(args.getString("date"));
        //ivComplaintImage.setImageURI(Uri.parse(args.getString("image")));
        vvMyComplaints.setVisibility(View.GONE);
        ivComplaintImage.setVisibility(View.GONE);
        if (!args.getString("video").equals("") || !args.getString("image").equals("")) {
            if (!args.getString("video").equals("")) {
                vvMyComplaints.setVisibility(View.VISIBLE);
                vvMyComplaints.setVideoURI(Uri.parse("http://103.240.220.76/kptraffic/complaints/video/" + args.getString("video")));
                vvMyComplaints.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vvMyComplaints.start();
                    }
                });


            } else if (!args.getString("image").equals("")) {
                ivComplaintImage.setVisibility(View.VISIBLE);
                Picasso.with(getActivity()).load("http://103.240.220.76/kptraffic/uploads/images/" + args.getString("image")).into(ivComplaintImage);
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
}
