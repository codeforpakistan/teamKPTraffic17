package com.kptrafficpolice.trafficapp.utilities;

/**
 * Created by Asus on 5/18/2017.
 */

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.TrafficEducationDetailFragment;

import java.util.Collections;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Fazal Mola on 5/15/2017.
 */
public class TrafficEducationAdapter extends RecyclerView.Adapter<TrafficEducationAdapter.ViewHolder> {

    List<TrafficEducationHelper> data = Collections.emptyList();
    Context context;
    int loader;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cvTrafficEducation;
        TextView descriptionEnglish, descriptionUrdu, imageTitle;
        ImageView ivTrafficEducationImage;


        public ViewHolder(View v) {
            super(v);
            cvTrafficEducation = (CardView) itemView.findViewById(R.id.cv_traffic_education);
            imageTitle = (TextView) itemView.findViewById(R.id.tv_image_title);
            descriptionEnglish = (TextView) itemView.findViewById(R.id.description_english);
            descriptionUrdu = (TextView) itemView.findViewById(R.id.description_urdu);
            ivTrafficEducationImage = (ImageView) itemView.findViewById(R.id.traffic_education_image);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public TrafficEducationAdapter(Context context, List<TrafficEducationHelper> data) {
        this.context = context;
        this.data = data;
        ;
    }
    //raabta
//rabta
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
        final TrafficEducationHelper educationHelper = data.get(position);
        String descUrdu = String.valueOf(educationHelper.strDescriptionUrdu);
        SpannableString content = new SpannableString("Login");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        // tvSkip.setText(content);
//        descUrdu =descUrdu.substring(0,10);
        holder.imageTitle.setText(educationHelper.strImageTitle);
        holder.descriptionEnglish.setText(educationHelper.strDescriptionEnglish);
        holder.descriptionUrdu.setText(educationHelper.strDescriptionUrdu);
        Glide.with(context).load("http://103.240.220.76/kptraffic/uploads/traffic-education/" + educationHelper.strImage)
                .into(holder.ivTrafficEducationImage);

        holder.cvTrafficEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new TrafficEducationDetailFragment();
                Bundle args = new Bundle();
                args.putString("title", String.valueOf(educationHelper.strImageTitle));
                args.putString("englishDescription", String.valueOf(educationHelper.strDescriptionEnglish));
                args.putString("urduDescription", String.valueOf(educationHelper.strDescriptionUrdu));
                args.putString("image", String.valueOf(educationHelper.strImage));
                fragment.setArguments(args);
                ((AppCompatActivity) context).getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();

            }
        });

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