package com.kptrafficpolice.trafficapp.fragments.ETest.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.kptrafficpolice.trafficapp.R;

import java.util.List;

/**
 * Created by DzonE on 11-Jul-18.
 */

public class ScoresAdapter extends ArrayAdapter<ScoresModel> {

    //storing all the modelList in the list
    private List<ScoresModel> modelList;
    private Context context;
    ScoresModel scoresModel;

    public ScoresAdapter(Context context, int resource, List<ScoresModel> modelList) {
        super(context, resource, modelList);
        this.context = context;
        this.modelList = modelList;
    }


    public ScoresModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        //getting the layoutinflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //getting listview itmes
        View listViewItem = inflater.inflate(R.layout.scores_item, null, true);
        TextView score = (TextView) listViewItem.findViewById(R.id.score);
        TextView date = (TextView) listViewItem.findViewById(R.id.date);

        //getting the current scoresModel
        scoresModel = modelList.get(position);


        //setting the scoresModel to textview
        score.setText("You Scored" + " " + scoresModel.getScore() + " " + "out of 20 ");


        date.setText(scoresModel.getDate());


        return listViewItem;
    }


}
