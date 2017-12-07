package com.kptrafficpolice.trafficapp.utilities;

/**
 * Created by Asus on 5/18/2017.
 */

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.TrafficEducationDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kashif Ahmad on 12/1/2017.
 */
public class TrafficEducationAdapter extends ArrayAdapter {
    private Context context;
    List<TrafficEducationHelper> data = new ArrayList<>();
    private int layoutResourceId;

    public TrafficEducationAdapter(Context context, int layoutResourceId, List<TrafficEducationHelper> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((AppCompatActivity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.tv_image_title);
            holder.image = (ImageView) row.findViewById(R.id.traffic_education_image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        final TrafficEducationHelper item = data.get(position);
        holder.imageTitle.setText(item.strImageTitle);
        Glide.with(context).load(Configuration.END_POINT_LIVE +
                "uploads/traffic-education/" + item.strImage)
                .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("title", item.strImageTitle);
                args.putString("englishDescription", item.strDescriptionEnglish);
                args.putString("urduDescription", item.strDescriptionUrdu);
                args.putString("image", item.strImage);
                Fragment fragment = new TrafficEducationDetailFragment();
                fragment.setArguments(args);
                ((AppCompatActivity) context).getFragmentManager().beginTransaction().
                        replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
            }
        });
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

    @Override
    public int getCount() {
        return data.size();
    }
}