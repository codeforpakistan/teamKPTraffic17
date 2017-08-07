package com.kptrafficpolice.trafficapp.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.location.Location;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.kptrafficpolice.trafficapp.R;
import com.kptrafficpolice.trafficapp.utilities.CheckNetwork;
import com.kptrafficpolice.trafficapp.utilities.Configuration;
import com.kptrafficpolice.trafficapp.utilities.GeneralUtils;
import com.kptrafficpolice.trafficapp.utilities.HTTPMultiPartEntity;
import com.iceteck.silicompressorr.SiliCompressor;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.orhanobut.dialogplus.DialogPlus;
import com.thefinestartist.Base;

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
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;

import static android.app.Activity.RESULT_OK;
import static com.thefinestartist.utils.content.ContextUtil.getSharedPreferences;
import static com.thefinestartist.utils.service.ServiceUtil.getSystemService;

public class ComplaintRegistrationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = ComplaintRegistrationFragment.class.getSimpleName();
    private ProgressBar progressBar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    long totalSize = 0;
    MaterialSpinner spComlaintType;
    String spinnerID, strDesciption, strUserID, strFormattedDate;

    double dblLat, dblLon;
    private OnFragmentInteractionListener mListener;
    Fragment fragment;
    private static final int CAMERA_RECORD_VIDEO_REQUEST_CODE = 200;
    Animation shake;
    Button btnSendComplaint;
    ImageView ivTakePicture, ivRecordVideo, ivCrossImage, ivCrossVideo;
    TextView tvTakePic, tvRecordVideo;
    LinearLayout linearLayout;
    EditText etDescription;
    File sourceFile;
    final int CAMERA_CAPTURE = 1;
    final int RESULT_LOAD_IMAGE = 2;
    final int CAMERA_VIDEO_CAPTURE = 3;
    final int RESULT_LOAD_VIDEO = 4;
    private String compressedVideoPath = null;
    SweetAlertDialog pDialog;
    RequestQueue requestQueue;
    boolean flag = false;
    DialogPlus dialog;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Calendar calendar = Calendar.getInstance();
    boolean isImage = false;
    boolean isVideo = false;
    File videoCaptureSourceFile;

    public ComplaintRegistrationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ComplaintRegistrationFragment newInstance(String param1, String param2) {
        ComplaintRegistrationFragment fragment = new ComplaintRegistrationFragment();
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
        Base.initialize(getActivity());
        sharedPreferences = getSharedPreferences("com.ghosttech.kptraffic", 0);
        editor = sharedPreferences.edit();
        System.out.println("Current time => " + calendar.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        strFormattedDate = df.format(calendar.getTime());
        Log.d("zma date", strFormattedDate);
        strUserID = sharedPreferences.getString("user_id", "");
        requestQueue = Volley.newRequestQueue(getActivity());
        Base.initialize(getActivity());
        ScrollView scrollView = (ScrollView) view.findViewById(R.id.sv_complaint);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager) getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return false;
            }
        });
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        linearLayout = (LinearLayout) view.findViewById(R.id.llayout_camera);
        tvTakePic = (TextView) view.findViewById(R.id.tv_take_pic);
        ivCrossImage = (ImageView) view.findViewById(R.id.iv_cross_image);
        ivCrossImage.setVisibility(View.GONE);
        tvRecordVideo = (TextView) view.findViewById(R.id.tv_record_video);
        ivCrossVideo = (ImageView) view.findViewById(R.id.iv_cross_video);
        ivCrossVideo.setVisibility(View.GONE);
        spComlaintType = (MaterialSpinner) view.findViewById(R.id.spinner);
        btnSendComplaint = (Button) view.findViewById(R.id.btn_send_complaint);
//        ivImagePreview = (ImageView) view.findViewById(R.id.iv_image_preview);
        spComlaintType.setItems("Complaint Type", "Traffic Jam", "Complaint against Wardens", "Illegal Parking", "Other");
        shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        }
        ivTakePicture = (ImageView) view.findViewById(R.id.iv_take_picture);
        ivRecordVideo = (ImageView) view.findViewById(R.id.iv_record_video);
        ivRecordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
                pictureDialog.setTitle("Choose video from");
                String[] pictureDialogItems = {
                        "\tGallery",
                        "\tCamera"};
                pictureDialog.setItems(pictureDialogItems,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        galleryVIntent();
                                        break;
                                    case 1:
                                        cameraVIntent();
                                        break;
                                }
                            }
                        });
                if (!isImage) {
                    pictureDialog.show();
                } else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Image captured already")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }

            }
        });
        ivTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder pictureDialog = new AlertDialog.Builder(getActivity());
                pictureDialog.setCancelable(true);
                pictureDialog.setTitle("Choose image from");
                String[] pictureDialogItems = {
                        "\tGallery",
                        "\tCamera"};
                pictureDialog.setItems(pictureDialogItems,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        galleryIntent();
                                        break;
                                    case 1:
                                        cameraIntent();
                                        break;
                                }
                            }
                        });
                if (!isVideo) {
                    pictureDialog.show();
                } else {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Video recorded already")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                }
                            })
                            .show();
                }

            }
        });
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#179e99"));
        pDialog.setTitleText("Sending complaint");
        spComlaintType.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                // Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                spinnerID = String.valueOf(position);
                Log.d("zma spinner id", spinnerID);
            }
        });
        customActionBar();

        SmartLocation.with(getActivity()).location()
                .start(new OnLocationUpdatedListener() {

                    @Override
                    public void onLocationUpdated(Location location) {
                        dblLat = location.getLatitude();
                        dblLon = location.getLongitude();
                        Log.d("Location : ", "" + dblLat + " " + dblLon);
                    }
                });
        onSendButton();
        ivCrossImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isImage = false;
                sourceFile = new File("");
                ivTakePicture.setImageResource(R.drawable.complaint_camera_picture);
                ivCrossImage.setVisibility(View.GONE);
                tvTakePic.setText("Replace Picture");
                tvTakePic.setTextColor(Color.BLACK);
            }
        });
        ivCrossVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isVideo = false;
                videoCaptureSourceFile = new File("");
                ivRecordVideo.setImageResource(R.drawable.complaint_camera_video);
                ivCrossVideo.setVisibility(View.GONE);
                tvRecordVideo.setText("Replace Video");
                tvRecordVideo.setTextColor(Color.BLACK);

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

        btnSendComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidation();


            }
        });
    }

    public void inputValidation() {
        etDescription = (EditText) view.findViewById(R.id.et_description);
        strDesciption = etDescription.getText().toString();

        if (spComlaintType.getText().equals("Complaint Type")) {
            spComlaintType.startAnimation(shake);
        } else if (strDesciption.length() < 10) {
            etDescription.startAnimation(shake);
        } else if (sourceFile == null && compressedVideoPath == null) {
            linearLayout.startAnimation(shake);

        } else {
            if (CheckNetwork.isInternetAvailable(getActivity())) {
                pDialog.show();
                new UploadFileToServer().execute();
            } else {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Oops")
                        .setContentText("Please check your network connection")
                        .setConfirmText("Refresh")
                        .setCancelText("Exit App")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                getActivity().finish();

                            }
                        })
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sweetAlertDialog.dismiss();
                            }
                        })
                        .show();

            }
        }
    }

    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {

        }

        @Override
        protected String doInBackground(Void... params) {
            return uploadFile();
        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString;
            if (flag) {

                Log.d("zma flag if", String.valueOf(flag));
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
                    // Adding file data to http body
                    // Extra parameters if you want to pass to server
                    File msourceFile = new File(sourceFile.getPath());
                    entity.addPart("image", new FileBody(msourceFile));
                    Looper.prepare();
                    entity.addPart("complaint_type_id", new StringBody(spinnerID));
                    entity.addPart("signup_id", new StringBody(strUserID));
                    entity.addPart("latitude", new StringBody(String.valueOf(dblLat)));
                    entity.addPart("longitude", new StringBody(String.valueOf(dblLon)));
                    entity.addPart("description", new StringBody(strDesciption));
                    entity.addPart("complaints_status_id", new StringBody("2"));
                    entity.addPart("dated", new StringBody(strFormattedDate));
                    pDialog.dismiss();
                    Bundle args = new Bundle();
                    fragment = new MainFragment();
                    args.putBoolean("status", true);
                    fragment.setArguments(args);
                    MainFragment.SUCCESS_DIALOG = true;
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    totalSize = entity.getContentLength();
                    httppost.setEntity(entity);
                    // Making server call
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity r_entity = response.getEntity();
                    int statusCode = response.getStatusLine().getStatusCode();
                    responseString = EntityUtils.toString(r_entity);
                    Log.d("zma response comp image", responseString);
                    Log.d("zma status code if", String.valueOf(statusCode));
                    Toast.makeText(getActivity(), "Great Job", Toast.LENGTH_SHORT).show();

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
            } else {
                Log.d("zma flag else", String.valueOf(flag));
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
                        File msourceFile = new File(compressedVideoPath);
                        entity.addPart("video", new FileBody(msourceFile));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // Adding file data to http body
                    // Extra parameters if you want to pass to server
                    entity.addPart("complaint_type_id", new StringBody(spinnerID));
                    entity.addPart("signup_id", new StringBody(strUserID));
                    entity.addPart("latitude", new StringBody(String.valueOf(dblLat)));
                    entity.addPart("longitude", new StringBody(String.valueOf(dblLon)));
                    entity.addPart("description", new StringBody(strDesciption));
                    entity.addPart("complaints_status_id", new StringBody("2"));
                    entity.addPart("dated", new StringBody(strFormattedDate));
                    totalSize = entity.getContentLength();
                    httppost.setEntity(entity);
                    // Making server call
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity r_entity = response.getEntity();
                    Log.d("zma response comp video", String.valueOf(response));
                    fragment = new MainFragment();
                    MainFragment.SUCCESS_DIALOG = true;
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    int statusCode = response.getStatusLine().getStatusCode();
                    responseString = EntityUtils.toString(r_entity);
                    Log.d("zma response comp image", responseString);
                    pDialog.dismiss();

                } catch (ClientProtocolException e) {
                    responseString = e.toString();
                    pDialog.dismiss();
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show();
                } catch (IOException e) {
                    responseString = e.toString();
                    Looper.prepare();
                    pDialog.dismiss();
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show();
                }
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
        mTitleTextView.setText("Write a complaint here");
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
//        mBackArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                fragment = new MainFragment();
//                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
//            }
//        });
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
        videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
        startActivityForResult(videoIntent, CAMERA_VIDEO_CAPTURE);

    }

    public void galleryVIntent() {
        Intent vv = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(vv, RESULT_LOAD_VIDEO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        flag = false;
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_VIDEO) {
            System.out.println("SELECT_VIDEO");
            Uri selectedImageUri = data.getData();
            String selectedPath = getVideoPath(selectedImageUri);
            // System.out.println("SELECT_VIDEO Path : " + selectedPath);
            sourceFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getActivity().getPackageName() + "/media/videos");
            // uploadVideo(selectedPath);
            new VideoCompressAsyncTask(getActivity()).execute(data.getData().toString(), sourceFile.getPath());
            flag = false;
            Glide.with(getActivity())
                    .load(selectedImageUri)
                    .into(ivRecordVideo);
//            Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(sourceFile.getAbsolutePath().toString(), MediaStore.Video.Thumbnails.MINI_KIND);
//            ivRecordVideo.setImageBitmap(bitmap);
            tvRecordVideo.setText("Replace Video");
            tvRecordVideo.setTextColor(Color.RED);
            isVideo = true;
            ivCrossVideo.setVisibility(View.VISIBLE);


        } else if (requestCode == RESULT_LOAD_IMAGE && null != data) {
            Uri selectedImage = data.getData();
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            sourceFile = new File(picturePath);
            Log.d("zma path load image", picturePath.toString());
            if (sourceFile != null) {
                flag = true;
            } else {
                flag = false;
            }
            cursor.close();
            ivTakePicture.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            if (picturePath.equals("")) {
                tvTakePic.setBackgroundResource(R.drawable.complaint_camera_icon);
            }
            tvTakePic.setText("Replace Picture");
            tvTakePic.setTextColor(Color.RED);
            isImage = true;
            ivCrossImage.setVisibility(View.VISIBLE);

        } else if (resultCode == RESULT_OK && requestCode == CAMERA_CAPTURE && data != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivTakePicture.setImageBitmap(photo);
            Uri tempUri = GeneralUtils.getImageUri(getActivity(), photo);
            sourceFile = new File(GeneralUtils.getRealPathFromURI(getActivity(), tempUri));
            if (sourceFile != null) {
                flag = true;
            } else {
                flag = false;
            }
            tvTakePic.setText("Replace Picture");
            tvTakePic.setTextColor(Color.RED);
            isImage = true;
            ivCrossImage.setVisibility(View.VISIBLE);

        } else if (resultCode == RESULT_OK && requestCode == CAMERA_VIDEO_CAPTURE && data != null) {
            Uri picUri = data.getData();
            if (data.getData() != null) {
                //create destination directory
                videoCaptureSourceFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getActivity().getPackageName() + "/media/videos");
                if (videoCaptureSourceFile.mkdirs() || videoCaptureSourceFile.isDirectory())
                    //compress and output new video specs
                    new VideoCompressAsyncTask(getActivity()).execute(data.getData().toString(), videoCaptureSourceFile.getPath());
                Glide.with(getActivity())
                        .load(picUri)
                        .into(ivRecordVideo);
//                Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoCaptureSourceFile.getAbsolutePath(), MediaStore.Video.Thumbnails.MINI_KIND);
//                ivRecordVideo.setImageBitmap(bitmap);
                tvRecordVideo.setText("Replace Video");
                tvRecordVideo.setTextColor(Color.RED);
                isVideo = true;
                ivCrossVideo.setVisibility(View.VISIBLE);


            }
            // sourceFile = new File(GeneralUtils.getRealPathFromURI(getActivity(), picUri));
        } else if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            sourceFile = new File(GeneralUtils.getRealPathFromURI(getActivity(), uri));
        }
    }


    class VideoCompressAsyncTask extends AsyncTask<String, String, String> {

        Context mContext;

        public VideoCompressAsyncTask(Context context) {
            mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "Compressing....");
        }

        @Override
        protected String doInBackground(String... paths) {
            try {
                compressedVideoPath = SiliCompressor.with(mContext).compressVideo(paths[0], paths[1]);
                Log.d("zma compressed path", compressedVideoPath.toString());

            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return compressedVideoPath;

        }


        @Override
        protected void onPostExecute(String compressedFilePath) {
            super.onPostExecute(compressedFilePath);
            Log.e(TAG, "Compressed Successflly! ");
        }
    }

    // New code for Method added for Gallery Video
    public String getVideoPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else return null;
    }
}