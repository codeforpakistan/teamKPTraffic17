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
import android.widget.Button;
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
import com.orhanobut.dialogplus.OnItemClickListener;
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
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int CAMERA_RECORD_VIDEO_REQUEST_CODE = 200;
    Animation shake;
    ImageView ivCamera, ivVideo,btnSend;
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
        spComlaintType.setItems("Complaint Type", "Traffic Jam", "Wardens Corruption", "Other");
        shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
        ivCamera = (ImageView) view.findViewById(R.id.iv_camera);
        ivCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // cameraIntent();
                bottomCustomDialog();
            }
        });
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Wait a while");
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

    public void onSendButtonClick() {


    }

    public void checkParameters() {

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
        btnSend = (ImageView) view.findViewById(R.id.iv_send_button);
        btnSend.setOnClickListener(new View.OnClickListener() {
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

        if (strDesciption.length() < 10) {
            etDescription.startAnimation(shake);
        } else if (sourceFile.toString().length() == 0) {
            ivCamera.startAnimation(shake);
            Log.d("zma path else", sourceFile.toString());
        } else {
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
            HttpPost httppost = new HttpPost(Configuration.END_POINT_LIVE + "complaints/image");

            try {
                HTTPMultiPartEntity entity = new HTTPMultiPartEntity(
                        new HTTPMultiPartEntity.ProgressListener() {

                            @Override
                            public void transferred(long num) {
                                publishProgress((int) ((num / (float) totalSize) * 100));
                            }
                        });

                File msourceFile = new File(sourceFile.getPath());

                // Adding file data to http body
                entity.addPart("image", new FileBody(msourceFile));

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
                    responseString = EntityUtils.toString(r_entity);
                    Log.d("zma response",responseString);
                    Looper.prepare();
                    if (responseString.contains("true")){
                        pDialog.dismiss();
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


//    private void postPictorialNews(final File file,final int id, final int signup_id,  final String description, final String lat, final String lon) {
//        // loading or check internet connection or something...
//        // ... then
//
//        final String url = Configuration.END_POINT_LIVE + "complaints/image";
//
//        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url, new Response.Listener<NetworkResponse>() {
//            @Override
//            public void onResponse(NetworkResponse response) {
//                Log.d("zma response url",url);
//                String resultResponse = new String(response.data);
//                Log.d("zma response 1",resultResponse);
//                try {
//                    JSONObject result = new JSONObject(resultResponse);
//                    if (result.getBoolean("status")){
//                        Log.d("zma response 2",String.valueOf(result));
//                        pDialog.dismiss();
//                    }else{
//                        Log.d("zma response 3",String.valueOf(result));
//                        pDialog.dismiss();
//
//
//                    }
//                    //TODO parse result and check status of uploading
//                    // progressDialog.dismiss();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.d("zma exception",String.valueOf(e));
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // progressDialog.dismiss();
//                NetworkResponse networkResponse = error.networkResponse;
//                String errorMessage = "Unknown error";
//                if (networkResponse == null) {
//                    if (error.getClass().equals(TimeoutError.class)) {
//                        errorMessage = "Request timeout";
//                    } else if (error.getClass().equals(NoConnectionError.class)) {
//                        errorMessage = "Failed to connect server";
//                    }
//                } else {
//                    String result = new String(networkResponse.data);
//                    try {
//                        JSONObject response = new JSONObject(result);
//                        String status = response.getString("status");
//                        String message = response.getString("message");
//                        // progressDialog.dismiss();
//                        Log.e("Error Status", status);
//                        Log.e("Error Message", message);
//                        if (networkResponse.statusCode == 404) {
//                            errorMessage = "Resource not found";
//                        } else if (networkResponse.statusCode == 401) {
//                            errorMessage = message + " Please login again";
//                        } else if (networkResponse.statusCode == 400) {
//                            errorMessage = message + " Check your inputs";
//                        } else if (networkResponse.statusCode == 500) {
//                            errorMessage = message + " Something is getting wrong";
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        Log.d("zma error js",String.valueOf(e));
//
//                    }
//                }
//                Log.i("Error", errorMessage);
//                error.printStackTrace();
//                Log.d("zma error lis",String.valueOf(error));
//            }
//        }) {
//                @Override
//                public String getBodyContentType() {
//                    return "application/x-www-form-urlencoded;charset=UTF-8";
//            }
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError{
//                Map<String, String> params = new HashMap<>();
//                params.put("complaint_type_id", String.valueOf(id));
//                params.put("signup_id", String.valueOf(signup_id));
//                params.put("description", description);
//                params.put("latitude", lat);
//                params.put("longitude", lon);
//                return params;
//            }
//
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                // file name could found file base or direct access from real path
//                // for now just get bitmap data from ImageView
//                String mimeType = GeneralUtils.getMimeTypeofFile(file);
//                Log.d("zma image",String.valueOf(file));
//                params.put("image", new VolleyMultipartRequest.DataPart("file_name", GeneralUtils.getByteArrayFromFile(file), mimeType));
//                return params;
//            }
//        };
//
//        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(
//                200000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        requestQueue.add(multipartRequest);
//    }


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

        } else if (requestCode == CAMERA_CAPTURE) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            // ivContent.setImageBitmap(photo);


            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = GeneralUtils.getImageUri(getActivity(), photo);
            // CALL THIS METHOD TO GET THE ACTUAL PATH
            sourceFile = new File(GeneralUtils.getRealPathFromURI(getActivity(), tempUri));
            Log.d("zma path 1", sourceFile.toString());
        } else if (requestCode == CAMERA_VIDEO_CAPTURE) {

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


    /**
     * Uploading the file to server
     */

    private void bottomCustomDialog() {
        DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.custom_bottom_option_menu))
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        LinearLayout ivCameraPhoto = (LinearLayout)view.findViewById(R.id.linear_layout_camera_image);
                        TextView tvCameraPicture = (TextView) view.findViewById(R.id.tv_camera_picture);
                        TextView tvCameraVideo = (TextView) view.findViewById(R.id.tv_camera_video);
                        String text = tvCameraPicture.getText().toString();
                        Log.d("zma text",text);
                        if (text.equals("Take Picture")){
                            cameraIntent();
                        }else{
                            cameraVIntent();
                        }
//                        LinearLayout ivCameraVideo = (LinearLayout)view.findViewById(R.id.linear_layout_camera_video);
//                        ivCameraPhoto.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                cameraIntent();
//                            }
//                        });
//                        ivCameraVideo.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                cameraVIntent();
//                            }
//                        });
                       Log.d("zma pos",String.valueOf(view+"item:"+item+"position:"+position));
                    }
                })
                .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                .create();
        dialog.show();
    }


}