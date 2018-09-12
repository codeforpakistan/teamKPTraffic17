package com.kptrafficpolice.trafficapp.fragments.ETest.Database;

/**
 * Created by DzonE on 09-Jul-18.
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.fragments.ETest.TestResultListModel;

import java.util.ArrayList;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class TestPreviewCustomAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    public Resources res;
    TestResultListModel tempValues = null;
    /*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;


    /*************  TestPreviewCustomAdapter Constructor *****************/
    public TestPreviewCustomAdapter(Activity a, ArrayList d) {


        /********** Take passed values **********/
        activity = a;
        data = d;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.history_listitem, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();

            holder.ques_num = vi.findViewById(R.id.num);

            holder.ques = vi.findViewById(R.id.ques);
            holder.op1 = vi.findViewById(R.id.op1);
            holder.op2 = vi.findViewById(R.id.op2);
            holder.op3 = vi.findViewById(R.id.op3);
            holder.op1_img = vi.findViewById(R.id.imageView1);
            holder.op2_img = vi.findViewById(R.id.imageView2);
            holder.op3_img = vi.findViewById(R.id.imageView3);
            holder.img = vi.findViewById(R.id.img);

            holder.relative_layout1 = vi.findViewById(R.id.relative_layout1);
            holder.relative_layout2 = vi.findViewById(R.id.relative_layout2);
            holder.relative_layout3 = vi.findViewById(R.id.relative_layout3);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();

        if (data.size() <= 0) {
            holder.ques.setText("No Data");

        } else {
            /***** Get each Model object from Arraylist ********/
            tempValues = null;
            tempValues = (TestResultListModel) data.get(position);

            /************  Set Model values in Holder elements ***********/

            int question_num;
            question_num = position + 1;
            holder.ques_num.setText("Q#" + question_num + "");


            holder.image = tempValues.getImage();
            Resources res = activity.getResources();
            String mDrawableName = holder.image;


            if (holder.image.equals("na")) {
                holder.img.setVisibility(View.GONE);
            } else {
                holder.img.setVisibility(View.VISIBLE);
                int resID = res.getIdentifier(mDrawableName, "drawable", activity.getPackageName());
                Drawable drawable = res.getDrawable(resID);
                holder.img.setImageDrawable(drawable);
            }


            holder.ques.setText(tempValues.getQuestion());
            holder.op1.setText("A: " + tempValues.getOp1());
            holder.op2.setText("B: " + tempValues.getOp2());
            holder.op3.setText("C: " + tempValues.getOp3());

            holder.status_op1 = tempValues.getStatus_op1();
            holder.status_op2 = tempValues.getStatus_op2();
            holder.status_op3 = tempValues.getStatus_op3();


            Log.e("op1", holder.status_op1);
            Log.e("op2", holder.status_op2);
            Log.e("op3", holder.status_op3);
            Log.e("//////////////////", "//////////////////////");


            if (tempValues.getStatus_op1().equals("right")) {
                holder.relative_layout1.setBackgroundResource(R.drawable.textfield_green);
                holder.op1_img.setBackgroundResource(R.drawable.tick);
            } else if (tempValues.getStatus_op1().equals("wrong")) {
                holder.relative_layout1.setBackgroundResource(R.drawable.textfield_red);
                holder.op1_img.setBackgroundResource(R.drawable.cross_image);
            } else {
                holder.relative_layout1.setBackgroundResource(R.drawable.dotted_background);
                holder.op1_img.setBackgroundResource(R.drawable.cross);
            }


            if (tempValues.getStatus_op2().equals("right")) {
                holder.relative_layout2.setBackgroundResource(R.drawable.textfield_green);
                holder.op2_img.setBackgroundResource(R.drawable.tick);
            } else if (tempValues.getStatus_op2().equals("wrong")) {
                holder.relative_layout2.setBackgroundResource(R.drawable.textfield_red);
                holder.op2_img.setBackgroundResource(R.drawable.cross_image);
            } else {
                holder.relative_layout2.setBackgroundResource(R.drawable.dotted_background);
                holder.op2_img.setBackgroundResource(R.drawable.cross);
            }


            if (tempValues.getStatus_op3().equals("right")) {
                holder.relative_layout3.setBackgroundResource(R.drawable.textfield_green);
                holder.op3_img.setBackgroundResource(R.drawable.tick);
            } else if (tempValues.getStatus_op3().equals("wrong")) {
                holder.relative_layout3.setBackgroundResource(R.drawable.textfield_red);
                holder.op3_img.setBackgroundResource(R.drawable.cross_image);
            } else {
                holder.relative_layout3.setBackgroundResource(R.drawable.dotted_background);
                holder.op3_img.setBackgroundResource(R.drawable.cross);
            }


        }
        return vi;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder {

        TextView ques, op1, op2, op3, ques_num;
        String status_op1, status_op2, status_op3, image;
        ImageView op1_img, op2_img, op3_img, img;
        RelativeLayout relative_layout1, relative_layout2, relative_layout3;

    }

}
