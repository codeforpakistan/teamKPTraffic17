package com.ghosttech.kptrafficapp.fragments;

import android.Manifest;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ghosttech.kptrafficapp.R;
import com.ghosttech.kptrafficapp.utilities.Configuration;
import com.ghosttech.kptrafficapp.utilities.GeneralUtils;
import com.ghosttech.kptrafficapp.utilities.HTTPMultiPartEntity;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

import static android.app.Activity.RESULT_CANCELED;

public class ComplaintFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = ComplaintFragment.class.getSimpleName();
    private ProgressBar progressBar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    long totalSize = 0;
    String contentPath;
    MaterialSpinner spComlaintType;
    String spinnerID, strDesciption;

    double dblLat, dblLon;
    private OnFragmentInteractionListener mListener;
    Fragment fragment;
    private Uri fileUri;
    private static final int CAMERA_RECORD_VIDEO_REQUEST_CODE = 200;
    Animation shake;
    ImageView ivStartCamera, ivSendComplaint;
    EditText etDescription;
    File sourceFile;
    final int CAMERA_CAPTURE = 1;
    final int RESULT_LOAD_IMAGE = 2;
    final int CAMERA_VIDEO_CAPTURE = 3;
    final int RESULT_LOAD_VIDEO = 4;
    SweetAlertDialog pDialog;
    RequestQueue requestQueue;

    public ComplaintFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComplaintFragment newInstance(String param1, String param2) {
        ComplaintFragment fragment = new ComplaintFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_complaint, container, false);
        requestQueue = Volley.newRequestQueue(getActivity());
        spComlaintType = (MaterialSpinner) view.findViewById(R.id.spinner);
        ivSendComplaint = (ImageView) view.findViewById(R.id.iv_send_button);
        spComlaintType.setItems("Complaint Type", "Traffic Jam", "Wardens Corruption", "Other");
        shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        ivStartCamera = (ImageView) view.findViewById(R.id.iv_camera);
        ivStartCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomCustomDialog();
            }
        });
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Sending complaint");
        customActionBar();
        onSendButton();
        SmartLocation.with(getActivity()).location()
                .start(new OnLocationUpdatedListener() {

                    @Override
                    public void onLocationUpdated(Location location) {
                        dblLat = location.getLatitude();
                        dblLon = location.getLongitude();
                        Log.d("Location : ", "" + dblLat + " " + dblLon);
                    }
                });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onSendButton() {

        ivSendComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidation();

            }
        });
    }

    public void inputValidation() {
        etDescription = (EditText) view.findViewById(R.id.et_description);
        strDesciption = etDescription.getText().toString();
        spComlaintType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                spinnerID = String.valueOf(position);
                Log.d("zma id", spinnerID);
            }
        });
        if (spComlaintType.getText().equals("Complaint Type")) {
            spComlaintType.startAnimation(shake);
        } else if (strDesciption.length() < 10) {
            etDescription.startAnimation(shake);
        } else if (sourceFile.toString().length()==0) {
            ivStartCamera.startAnimation(shake);
        }else{
            pDialog.show();
            new UploadFileToServer().execute();
        }
    }

    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            // Making progress bar visible
//            progressBar.setVisibility(View.VISIBLE);

            // updating progress bar value
            // progressBar.setProgress(progress[0]);

            // updating percentage value
            //  txtPercentage.setText(String.valueOf(progress[0]) + "%");
        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Configuration.END_POINT_LIVE + "complaints/video");

            try {
                HTTPMultiPartEntity entity = new HTTPMultiPartEntity(
                        new HTTPMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });
                try {
                    File msourceFile = new File(sourceFile.getPath());
                    entity.addPart("image", new FileBody(msourceFile));
                }catch(Exception e){
                    e.printStackTrace();
                }
                // Adding file data to http body
                // Extra parameters if you want to pass to server
                entity.addPart("complaint_type_id", new StringBody("12"));
                entity.addPart("signup_id", new StringBody("21"));
                entity.addPart("latitude", new StringBody(String.valueOf(dblLat)));
                entity.addPart("longitude", new StringBody(String.valueOf(dblLon)));
                entity.addPart("description", new StringBody(strDesciption));
                totalSize = entity.getContentLength();
                httppost.setEntity(entity);
                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {

                    // Server response
                    fragment = new MainFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    responseString = EntityUtils.toString(r_entity);
                    Log.d("zma response", responseString);
                    Looper.prepare();
                    if (responseString.contains("true")) {

                        new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Good job!")
                                .setContentText("Your complaint has been registered!")
                                .show();

                    }
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                    pDialog.dismiss();
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show();
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
                pDialog.dismiss();
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();
            } catch (IOException e) {
                responseString = e.toString();
                pDialog.dismiss();
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText("Something went wrong!")
                        .show();
            }

            return responseString;

        }
    }

    public void customActionBar() {
        android.support.v7.app.ActionBar mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
        ImageView mBackArrow = (ImageView) mCustomView.findViewById(R.id.iv_back_arrow);
        mTitleTextView.setText("Write a Complaint");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new MainFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }

    public void cameraIntent() {

        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(captureIntent, CAMERA_CAPTURE);

    }

    public void galleryIntent() {

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);

    }

    public void cameraVIntent() {

        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(videoIntent, CAMERA_VIDEO_CAPTURE);

    }

    public void galleryVIntent() {

        Intent vv = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(vv, RESULT_LOAD_VIDEO);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            sourceFile = new File(picturePath);
            Log.d("zma path", picturePath.toString());
            cursor.close();
            //ivContent.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        } else if (requestCode != RESULT_CANCELED || data != null) {
            if (requestCode == CAMERA_CAPTURE) {
                try {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    // ivContent.setImageBitmap(photo);
                    // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
                    Uri tempUri = GeneralUtils.getImageUri(getActivity(), photo);
                    // CALL THIS METHOD TO GET THE ACTUAL PATH
                    sourceFile = new File(GeneralUtils.getRealPathFromURI(getActivity(), tempUri));
                    Log.d("zma path 1", sourceFile.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        else if (requestCode == CAMERA_VIDEO_CAPTURE) {

            Uri picUri = data.getData();
            Bundle extras = data.getExtras();
            String path = data.getData().toString();
            sourceFile = new File(picUri.getPath());
            Log.d("zma path 1111", sourceFile.toString());


        } else if (resultCode == RESULT_LOAD_VIDEO) {

            Uri selectedVideo = data.getData();
            String[] filePathColumn = {MediaStore.Video.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedVideo,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            // String videoPath = cursor.getString(columnIndex);
            String videoPath = data.getData().toString();
            sourceFile = new File(videoPath);
            cursor.close();

        }

    }

    private void bottomCustomDialog() {
        DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.custom_bottom_option_menu))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        final LinearLayout linearLayoutCamera = (LinearLayout) dialog.findViewById(R.id.linear_layout_camera_image);
                        LinearLayout linearLayoutVideo = (LinearLayout) dialog.findViewById(R.id.linear_layout_camera_video);
                        linearLayoutCamera.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cameraIntent();

                            }
                        });
                        linearLayoutVideo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cameraVIntent();
                            }
                        });
                    }
                })
                .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();
    }


}