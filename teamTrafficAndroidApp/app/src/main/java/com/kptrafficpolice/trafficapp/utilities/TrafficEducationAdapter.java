package com.kptrafficpolice.trafficapp.utilities;

/**
 * Created by Asus on 5/18/2017.
 */

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
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
import java.util.Collections;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Fazal Mola on 5/15/2017.
 */
public class TrafficEducationAdapter extends ArrayAdapter{
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
            row = inflater.inflate(layoutResourceId, parent, false);            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.tv_image_title);
            holder.image = (ImageView) row.findViewById(R.id.traffic_education_image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        TrafficEducationHelper item = data.get(position);
        holder.imageTitle.setText(item.strImageTitle);
       // Log.d("zma image title", item.strImageTitle);
        Glide.with(context).load(Configuration.END_POINT_LIVE+"uploads/traffic-education/" + item.strImage)
                .into(holder.image);
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