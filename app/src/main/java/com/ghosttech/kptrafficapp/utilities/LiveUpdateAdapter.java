package com.ghosttech.kptrafficapp.utilities;

/**
 * Created by Asus on 2/1/2017.
 */

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ghosttech.kptrafficapp.R;
import com.ghosttech.kptrafficapp.activities.MapsActivity;
import com.ghosttech.kptrafficapp.fragments.LiveUpdateResultFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.security.AccessController.getContext;

public class LiveUpdateAdapter extends RecyclerView.Adapter<LiveUpdateAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    List<LiveUpdateHelper> liveUpdateHelpers;
    String strRoadName;
    String responseRouteName,responseRouteStatus,responseTime;
    Context context;
    RequestQueue mRequestQueue;
    Fragment fragment;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cvLiveUpdate;
        TextView tvRoadName;
        ImageView ivRoadListIcon;


        public ViewHolder(View v) {
            super(v);
            ivRoadListIcon = (ImageView) itemView.findViewById(R.id.iv_road_icon);
            tvRoadName = (TextView) itemView.findViewById(R.id.tv_road_name);
            cvLiveUpdate = (CardView) itemView.findViewById(R.id.cv_item_live_update);
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
        holder.ivRoadListIcon.setImageResource(liveUpdateHelpers.get(position).roadIcon);
        final int itemPosition = position;
        Log.d("zma position",String.valueOf(itemPosition));
        holder.cvLiveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new LiveUpdateResultFragment();
                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,fragment).commit();
                apiCall();

//                switch (itemPosition){
//                    case  0:
//                        strRoadName = "R1";
//                        Log.d("zma strRoadName",strRoadName);
//                        apiCall(strRoadName);
//                        break;
//                    case 1:
//                        strRoadName = "R2";
//                        apiCall(strRoadName);
//                        break;
//                    case 2:
//                        strRoadName = "R3";
//                        apiCall(strRoadName);
//                        break;
//                    case 3:
//                        strRoadName = "R4";
//                        apiCall(strRoadName);
//                        break;
//                    case 4:
//                        strRoadName = "R5";
//                        apiCall(strRoadName);
//                        break;
//                    case 5:
//                        strRoadName = "R6";
//                        apiCall(strRoadName);
//                        break;
//                    case 6:
//                        strRoadName = "R7";
//                        apiCall(strRoadName);
//                        break;
//                }
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
    public void apiCall() {
        String url = Configuration.END_POINT_LIVE + "live_updates/get_updates";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean status = response.getBoolean("status");
                    Log.d("zma status",String.valueOf(status));
                    Log.d("zma response",String.valueOf(response));
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        responseRouteName = jsonObject.getString("route_name");
                        responseRouteStatus = jsonObject.getString("route_status");
                        responseTime = jsonObject.getString("updated_time");
                        Toast.makeText(context, responseRouteName + "\n" +
                                responseRouteStatus +
                                "\n" + responseTime, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded;charset=UTF-8";
            }
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email", roadName);
//                return params;
//            }
        };
        mRequestQueue.add(jsonObjectRequest);
    }

}