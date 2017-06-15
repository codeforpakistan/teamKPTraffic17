package com.ghosttech.kptrafficapp.utilities;

/**
 * Created by Asus on 6/15/2017.
 */



import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghosttech.kptrafficapp.R;

public class EmergencyListAdapter extends RecyclerView.Adapter<EmergencyListAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    List<EmergencyHelper> emergencyHelperList;
    public class ViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        CardView cv;
        TextView tvEmergencyHelperName, tvEmergencyPhone, tvEmergencyLocation, tvEmergencyDistance;
        ImageView ivCallHelper, ivHelperIcon;


        public ViewHolder(View v) {
            super(v);
            tvEmergencyHelperName = (TextView) itemView.findViewById(R.id.tv_emergency_helper_name);
            tvEmergencyPhone = (TextView) itemView.findViewById(R.id.tv_emergency_phone);
            tvEmergencyDistance = (TextView) itemView.findViewById(R.id.tvDistance);
            tvEmergencyLocation = (TextView) itemView.findViewById(R.id.tv_emergency_location);
            ivCallHelper = (ImageView) itemView.findViewById(R.id.ivCallNow);
            ivHelperIcon = (ImageView) itemView.findViewById(R.id.iv_emergency_icon);

        }
    }




    // Provide a suitable constructor (depends on the kind of dataset)
        public EmergencyListAdapter(List<EmergencyHelper> emergencyHelperList) {
            this.emergencyHelperList = emergencyHelperList;;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public EmergencyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_emergency_layout, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.tvEmergencyHelperName.setText(emergencyHelperList.get(position).strHelperName);
            holder.tvEmergencyDistance.setText(emergencyHelperList.get(position).strHelperDistance);
            holder.tvEmergencyLocation.setText(emergencyHelperList.get(position).strHelperLocation);
            holder.tvEmergencyPhone.setText(emergencyHelperList.get(position).strHelperPhoneNumber);

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return emergencyHelperList.size();
        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }
    }

