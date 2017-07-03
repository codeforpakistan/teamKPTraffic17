package com.ghosttech.kptrafficapp.utilities;

/**
 * Created by Asus on 6/15/2017.
 */


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.ghosttech.kptrafficapp.R;

import java.util.ArrayList;
import java.util.List;

public class MyComplaintsListAdapter extends RecyclerView.Adapter<MyComplaintsListAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    List<MyComplaintsHelper> complaintsHelperList;
    Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        CardView cv;
        TextView tvComplaintsID, tvComplaintDescription, tvComplaintStatus, ivHelperIcon;
        //ImageView  ivHelperIcon;
        LinearLayout lLCallNow;
        VideoView vvComplaints;


        public ViewHolder(View v) {
            super(v);
            tvComplaintsID = (TextView) itemView.findViewById(R.id.tv_complaint_id);
            tvComplaintStatus = (TextView) itemView.findViewById(R.id.tv_complaint_status);
            tvComplaintDescription = (TextView) itemView.findViewById(R.id.tv_complaint_description);
            ivHelperIcon = (TextView) itemView.findViewById(R.id.iv_complaint_image);


        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyComplaintsListAdapter(Context context, List<MyComplaintsHelper> myComplaintsHelperList) {
        this.complaintsHelperList = myComplaintsHelperList;
        this.context = context;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyComplaintsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_mycomplaints_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final MyComplaintsHelper helper = complaintsHelperList.get(position);
        holder.tvComplaintsID.setText(helper.getStrComplaintID());
        holder.tvComplaintStatus.setText(helper.getStrComplaintStatus());
        holder.tvComplaintDescription.setText(helper.getStrDescription());
        holder.ivHelperIcon.setText(helper.getStrDate());
       //Glide.with(context).load(Configuration.END_POINT_LIVE+"uploads/images").into(holder.ivHelperIcon);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return complaintsHelperList.size();
        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }

