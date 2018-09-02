package com.acrs.userapp;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.hardware.Camera;
import android.location.Location;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.acrs.userapp.di.service.RestBuilderPro;
import com.acrs.userapp.ui.base.BaseActivity;
import com.acrs.userapp.ui.emergency.Util.LocationHelper;
import com.acrs.userapp.ui.login.LoginWebApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class PreviewDemo extends BaseActivity {

    private SurfaceView preview = null;
    private SurfaceHolder previewHolder = null;
    private Camera camera = null;
    private boolean inPreview = false;
    ImageView image;
    Bitmap bmp, itembmp;
    static Bitmap mutableBitmap;
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;
    File imageFileName = null;
    File imageFileFolder = null;
    private MediaScannerConnection msConn;
    Display d;
    int screenhgt, screenwdh;
    ProgressDialog dialog;
    private ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);


        preview = (SurfaceView) findViewById(R.id.surface);
        progressBar = findViewById(R.id.progressbar);

        previewHolder = preview.getHolder();
        previewHolder.addCallback(surfaceCallback);


    }


    @Override
    public void onResume() {
        super.onResume();
        try {
            int cameraId = getFrontCameraId();
            if (cameraId != -1) {
                camera = Camera.open(1);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void counterT() throws Exception {

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                onBack();
            }

        }.start();
    }

    private int getFrontCameraId() {
        try {
            int camId = -1;
            int numberOfCameras = Camera.getNumberOfCameras();
            Camera.CameraInfo ci = new Camera.CameraInfo();

            for (int i = 0; i < numberOfCameras; i++) {
                Camera.getCameraInfo(i, ci);
                if (ci.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    camId = i;
                }
            }
            return camId;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public void onPause() {
        try {
            if (inPreview) {
                camera.stopPreview();
            }
            camera.release();
            camera = null;
            inPreview = false;
            super.onPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Camera.Size getBestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;
                    if (newArea > resultArea) {
                        result = size;
                    }
                }
            }
        }
        return (result);
    }

    SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(previewHolder);
                camera.setDisplayOrientation(90);

            } catch (Throwable t) {
                Log.e("PreviewDemo-",
                        "Exception in setPreviewDisplay()", t);
                Toast.makeText(PreviewDemo.this, t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        }

        public void surfaceChanged(SurfaceHolder holder,
                                   int format, int width,
                                   int height) {
            if (camera != null) {
                Camera.Parameters parameters = camera.getParameters();
                Camera.Size size = getBestPreviewSize(width, height,
                        parameters);

                if (size != null) {
                    //parameters.setPreviewSize(size.width, size.height);
                    camera.setParameters(parameters);
                    camera.startPreview();
                    inPreview = true;

                    try {
                        counterT();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        public void surfaceDestroyed(SurfaceHolder holder) {
            // no-op
        }
    };


    Camera.PictureCallback photoCallback = new Camera.PictureCallback() {
        public void onPictureTaken(final byte[] data, final Camera camera) {

            try{
            dialog = ProgressDialog.show(PreviewDemo.this, "", "Saving Photo");
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(500);
                    } catch (Exception ex) {
                    }
                    onPictureTake(data, camera);
                }
            }.start();
            }catch (Exception e){e.printStackTrace();}
        }
    };


    public void onPictureTake(byte[] data, Camera camera) {

        bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
        mutableBitmap = bmp.copy(Bitmap.Config.ARGB_8888, true);
        savePhoto(mutableBitmap);
    }


    class SavePhotoTask extends AsyncTask<byte[], String, String> {
        @Override
        protected String doInBackground(byte[]... jpeg) {
            File photo = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
            if (photo.exists()) {
                photo.delete();
            }
            try {
                FileOutputStream fos = new FileOutputStream(photo.getPath());
                fos.write(jpeg[0]);
                fos.close();
            } catch (java.io.IOException e) {
                Log.e("PictureDemo", "Exception in photoCallback", e);
            }
            return (null);
        }
    }


    public void savePhoto(Bitmap bmp) {
        if(dialog!=null)
        {
            dialog.dismiss();
        }


        imageFileFolder = new File(Environment.getExternalStorageDirectory(), "Rotate");
        imageFileFolder.mkdir();
        FileOutputStream out = null;
        Calendar c = Calendar.getInstance();
        String date = fromInt(c.get(Calendar.MONTH)) + fromInt(c.get(Calendar.DAY_OF_MONTH)) + fromInt(c.get(Calendar.YEAR)) + fromInt(c.get(Calendar.HOUR_OF_DAY)) + fromInt(c.get(Calendar.MINUTE)) + fromInt(c.get(Calendar.SECOND));
        imageFileName = new File(imageFileFolder, date.toString() + ".jpg");
        try {
            out = new FileOutputStream(imageFileName);
            bmp.compress(Bitmap.CompressFormat.JPEG, 40, out);
            out.flush();
            out.close();
            scanPhoto(imageFileName.toString());
            out = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 60, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        final String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

        progressBar.post(new Runnable() {
            @Override
            public void run() {
                apiNotRespondingUser(encoded);
            }
        });

    }

    private void apiNotRespondingUser(String encoded) {

        Location location = LocationHelper.getLocation(this);
        String latitude = "", logitude = "";
        if (location != null) {
            latitude = location.getLatitude() + "";
            logitude = location.getLongitude() + "";
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("image", encoded);
        hashMap.put("userId", dataManager.getUserId());
        hashMap.put("latitude", latitude);
        hashMap.put("longitude", logitude);
        hashMap.put("tag", "notresponding");


        RestBuilderPro.getService(LoginWebApi.class).login(hashMap).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {
                    try {


                        String res = response.body().string();
                        Log.e("response",res);
                       // finish();

                    } catch (Exception e) {
                        e.printStackTrace();

                         SnakBarString("Response error");
                        // finish();

                    }

                } else {
                    SnakBarString("Login failed");

                   // finish();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                SnakBarId(R.string.notconnect);

              //  finish();
            }
        });





    }

    public String fromInt(int val) {
        return String.valueOf(val);
    }

    public void scanPhoto(final String imageFileName) {
        msConn = new MediaScannerConnection(PreviewDemo.this, new MediaScannerConnection.MediaScannerConnectionClient() {
            public void onMediaScannerConnected() {
                msConn.scanFile(imageFileName, null);
                Log.i("lient obj  in P", "connection established");
            }

            public void onScanCompleted(String path, Uri uri) {
                msConn.disconnect();
                Log.i("bj in Photo Utility", "scan completed");
            }
        });
        msConn.connect();
    }


    public void onBack() {
        try {
            camera.takePicture(null, null, photoCallback);
            inPreview = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}