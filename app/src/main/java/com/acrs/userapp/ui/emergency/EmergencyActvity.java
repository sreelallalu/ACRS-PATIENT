package com.acrs.userapp.ui.emergency;

import android.location.Location;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.acrs.userapp.R;
import com.acrs.userapp.ui.base.BaseActivity;
import com.acrs.userapp.ui.emergency.Util.LocationHelper;
import com.acrs.userapp.ui.emergency.Util.SweetAlertDialog;
import com.acrs.userapp.util.Permission;
import com.acrs.userapp.util.PermissionHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import javax.inject.Inject;

public class EmergencyActvity extends BaseActivity implements EmergencyView {

    private CountDownTimer countdowntimer;

    @Inject
    Emergency_i_presenter<EmergencyView> presenter;
    private String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        getActivityComponent().inject(this);
        presenter.onAttach(this);
        getSupportActionBar().setTitle("Emergency");
        permissionCheck();
        Button button = findViewById(R.id.emergency_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                panic();

            }
        });


    }

    private void panic() {

        try {
            final SweetAlertDialog dialog_s = new SweetAlertDialog(EmergencyActvity.this, SweetAlertDialog.WARNING_TYPE1);
            dialog_s.setTitleText("Are you sure?");
            dialog_s.setContentText("Emergency SOS will  be initiated in");
            dialog_s.setCancelText("Abort");
            dialog_s.setConfirmText("Initiate now");
            dialog_s.showCancelButton(true);
            dialog_s.show();

            dialog_s.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    if (countdowntimer != null) {

                        countdowntimer.cancel();
                    }

                    dialog_s.dismiss();
                    panicApi();

                }
            });
            dialog_s.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    if (countdowntimer != null) {
                        countdowntimer.cancel();
                    }

                    dialog_s.dismiss();
                }
            });
            MediaPlayer mediaPlayer = MediaPlayer.create(EmergencyActvity.this, R.raw.beep);
            mediaPlayer.start();
            Panik_b(dialog_s);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    private void Panik_b(final SweetAlertDialog dialog_s) throws Exception {
        countdowntimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {

                dialog_s.setContentText("Emergency SOS will  be initiated in " + millisUntilFinished / 1000);
                //txt_timer.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                if (dialog_s != null) {
                    dialog_s.dismiss();


                }

                panicApi();


            }
        };
        countdowntimer.start();


    }

    private void panicApi() {


        Location location = LocationHelper.getLocation(this);
        String latitude = "", longitude = "";
        if (location != null) {
            latitude = location.getLatitude() + "";
            longitude = location.getLongitude() + "";
        }

        String userdata = dataManager.getUserdata();

        try {
            JSONObject userOBJ = new JSONObject(userdata);
            uId = userOBJ.getString("id");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("latitude", latitude);
        hashMap.put("longitude", longitude);
        hashMap.put("userid", uId);
        hashMap.put("tag", "panic");

        Log.e("location",hashMap.toString());
        super.progresShow(true);
        presenter.panicCall(hashMap);


    }

    private void permissionCheck() {

        PermissionHelper permissionHelper = new PermissionHelper(this, Permission.location, 12);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied() {

            }

            @Override
            public void onPermissionDeniedBySystem() {

            }
        });
    }

    @Override
    public void panicSucc() {
        super.progresShow(false);
        finish();
    }

    @Override
    public void panicFailed() {
        super.progresShow(false);

    }
}
