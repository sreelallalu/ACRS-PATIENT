package com.acrs.userapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.acrs.userapp.ui.alam.MedicineAlarmReceiver;
import com.acrs.userapp.util.Permission;
import com.acrs.userapp.util.PermissionHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private PermissionHelper permissionHelper;
    private CameraController cameraController;
    private FrameLayout preview;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;
    private Camera mCamera;
    private boolean previewing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preview = findViewById(R.id.frame);

        alarmSetting("2018-08-15 17:27:05",1);
        alarmSetting("2018-08-15 17:28:05",5);
        permissionHelper = new PermissionHelper(this, Permission.camera, 122);


    }









    public void alarmSetting(String time, int idnoti) {

        Calendar cal = Calendar.getInstance();
        String aTime = time;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {

            cal.setTime(sdf.parse(aTime));
            Log.e("time_", String.valueOf(sdf.parse(aTime)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.e("getTime", cal.getTimeInMillis() + "");

        Intent intent = new Intent(this, MedicineAlarmReceiver.class);
        intent.putExtra("notification_id", idnoti);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), idnoti, intent, PendingIntent.FLAG_UPDATE_CURRENT | Intent.FILL_IN_DATA);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "Alarm worked.", Toast.LENGTH_LONG).show();
    }


}
