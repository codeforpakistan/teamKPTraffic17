package com.kptrafficpolice.trafficapp.utilities;

/**
 * Created by Asus on 2/1/2017.
 */

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.LiveUpdateResultFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LiveUpdateAdapter extends RecyclerView.Adapter<LiveUpdateAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    List<LiveUpdateHelper> liveUpdateHelpers;
    String strRoadName;
    String responseRouteName, responseRouteStatus, responseTime;
    Context context;
    RequestQueue mRequestQueue;
    Fragment fragment;
    Bundle args;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        LinearLayout cvItem;
        TextView tvRoadName, tvRoadStartEndPoint;
        ImageView ivRoadListIcon;


        public ViewHolder(View v) {
            super(v);
            // ivRoadListIcon = (ImageView) itemView.findViewById(R.id.iv_road_icon);
            tvRoadName = (TextView) itemView.findViewById(R.id.tv_road_name);
            cvItem = (LinearLayout) itemView.findViewById(R.id.item_live_update);
            tvRoadStartEndPoint = (TextView) itemView.findViewById(R.id.tv_road_detail);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public LiveUpdateAdapter(Context context, List<LiveUpdateHelper> persons) {
        this.liveUpdateHelpers = persons;
        this.context = context;
        mRequestQueue = Volley.newRequestQueue(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LiveUpdateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_update_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.tvRoadName.setText(liveUpdateHelpers.get(position).strRoadName);
        holder.tvRoadStartEndPoint.setText(liveUpdateHelpers.get(position).strRoadStartEndPoint);
        // holder.ivRoadListIcon.setImageResource(liveUpdateHelpers.get(position).roadIcon);
        final int itemPosition = position;
        args = new Bundle();
        Log.d("zma position", String.valueOf(itemPosition));
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new LiveUpdateResultFragment();

                switch (itemPosition) {
                    case 0:
                        strRoadName = "gt_road";
                        args.putString("road_name", strRoadName);
                        Log.d("zma strRoadName", strRoadName);
                        apiCall(strRoadName);
                        break;
                    case 1:
                        strRoadName = "khyber_road";
                        args.putString("road_name", strRoadName);
                        apiCall(strRoadName);
                        break;
                    case 2:
                        strRoadName = "charsadda_road";
                        args.putString("road_name", strRoadName);
                        apiCall(strRoadName);
                        break;
                    case 3:
                        strRoadName = "jail_road";
                        args.putString("road_name", strRoadName);
                        apiCall(strRoadName);
                        break;
                    case 4:
                        strRoadName = "university_road";
                        args.putString("road_name", strRoadName);
                        apiCall(strRoadName);
                        break;
                    case 5:
                        strRoadName = "dalazak_road";
                        args.putString("road_name", strRoadName);
                        apiCall(strRoadName);
                        break;
                    case 6:
                        strRoadName = "saddar_road";
                        args.putString("road_name", strRoadName);
                        apiCall(strRoadName);
                        break;
                    case 7:
                        strRoadName = "baghenaran_road";
                        args.putString("road_name", strRoadName);
                        apiCall(strRoadName);
                        break;
                    case 8:
                        strRoadName = "warsak_road";
                        args.putString("road_name", strRoadName);
                        apiCall(strRoadName);
                        break;
                    case 9:
                        strRoadName = "kohat_road";
                        args.putString("road_name", strRoadName);
                        apiCall(strRoadName);
                        break;
                }
                fragment.setArguments(args);
                Log.d("zma id", String.valueOf(position));
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return liveUpdateHelpers.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void apiCall(final String strRoadName) {

        final SweetAlertDialog pDialog = new SweetAlertDialog(((AppCompatActivity) context), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Wait a while");
        pDialog.setCancelable(true);
        pDialog.show();

        String url = Configuration.END_POINT_LIVE + "live_updates/get_updates?flag=" + strRoadName;
        Log.d("zma url", String.valueOf(url));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean status = response.getBoolean("status");
                    Log.d("zma road status",String.valueOf(status));
                    if (status) {
                        pDialog.dismiss();
                        Log.d("zma status api wala", String.valueOf(status));
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            responseRouteName = jsonObject.getString("route_name");
                            responseRouteStatus = jsonObject.getString("route_status");
                            responseTime = jsonObject.getString("updated_time");
                        }
                        fragment = new LiveUpdateResultFragment();
                        args.putString("route_name", responseRouteName.toString());

                        if (responseRouteStatus.toString().equals("Clear")) {

                            ((AppCompatActivity) context).getFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
                            args.putString("status", responseRouteStatus.toString());
                            args.putString("response_time", responseTime.toString());
                            Log.d("zma response if wala", String.valueOf(responseRouteName + "\n" + responseTime));
                            fragment.setArguments(args);

                        } else if (responseRouteStatus.toString().equals("Busy")) {
                            ((AppCompatActivity) context).getFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
                            args.putString("status", responseRouteStatus.toString());
                            args.putString("response_time", responseTime.toString());
                            Log.d("zma response if wala", String.valueOf(responseRouteName + "\n" + responseTime));
                            fragment.setArguments(args);

                        } else if (responseRouteStatus.toString().equals("Congested")) {
                            ((AppCompatActivity) context).getFragmentManager().beginTransaction()
                                    .replace(R.id.fragment_container, fragment).addToBackStack("tag").commit();
                            args.putString("status", responseRouteStatus.toString());
                            args.putString("response_time", responseTime.toString());
                            Log.d("zma response if wala", String.valueOf(responseRouteName + "\n" + responseTime));
                            fragment.setArguments(args);


                        }
                       // fragment.setArguments(args);

                    } else {
                        pDialog.dismiss();
                        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Something went wrong   !")
                                .show();
                        //main else
                    }
                } catch (JSONException e) {
                    pDialog.dismiss();
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Server Error!")
                            .show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("flag", strRoadName);
                return params;
            }
        };
        mRequestQueue.add(jsonObjectRequest);
    }

}