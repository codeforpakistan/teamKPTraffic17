package com.ghosttech.kptrafficapp.utilities;

/**
 * Created by Asus on 5/18/2017.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ghosttech.kptrafficapp.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Fazal Mola on 5/15/2017.
 */
public class TrafficEducationAdapter extends RecyclerView.Adapter<TrafficEducationAdapter.ViewHolder> {

    List<TrafficEducationHelper> data = Collections.emptyList();
    Context context;
    int loader;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        TextView descriptionEnglish;
        TextView descriptionUrdu;
        TextView imageTitle;
        ImageView ivTrafficEducationImage, ivTrafficEducationGif;


        public ViewHolder(View v) {
            super(v);
            cv = (CardView) itemView.findViewById(R.id.cv);
            imageTitle = (TextView) itemView.findViewById(R.id.image_title);
            descriptionEnglish = (TextView) itemView.findViewById(R.id.description_english);
            descriptionUrdu = (TextView) itemView.findViewById(R.id.description_urdu);
            ivTrafficEducationImage = (ImageView) itemView.findViewById(R.id.traffic_education_image);
            ivTrafficEducationGif = (ImageView) itemView.findViewById(R.id.gif_view);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public TrafficEducationAdapter(Context context, List<TrafficEducationHelper> data) {
        this.context = context;
        this.data = data;
        ;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrafficEducationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.traffic_education_items, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Log.d("position", String.valueOf(position));


        TrafficEducationHelper educationHelper = data.get(position);
        holder.imageTitle.setText(educationHelper.strImageTitle);
        holder.descriptionEnglish.setText(educationHelper.strDescriptionEnglish);
        holder.descriptionUrdu.setText(educationHelper.strDescriptionUrdu);
        Glide.with(context).load("http://103.240.220.76/kptraffic/uploads/traffic-education/" + educationHelper.strImage)
                .into(holder.ivTrafficEducationImage);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}