package com.acrs.userapp.ui.alam;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;

import com.acrs.userapp.ui.medicine.medicine_add.MedicineCallActivtiy;

public class MedicineAlarmReceiver extends BroadcastReceiver {

    private Context context;
    private SharedPreferences editor;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        try {
         if(!intent.getBooleanExtra("type",false)) {
             Intent i = new Intent(context, MedicineCallActivtiy.class);
             i.putExtra("med_not", intent.getStringExtra("medi_not"));
             i.putExtra("med_name", intent.getStringExtra("medi_name"));

             i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivity(i);
         }else{
             Intent i = new Intent(context, PreviewDemo.class);
             i.putExtra("b_id", intent.getStringExtra("b_id"));
             i.putExtra("request", true);
             i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             context.startActivity(i);
         }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeNotification(final int id, final NotificationManager notificationManager) {
        Handler handler = new Handler();
        long delayInMilliseconds = 10000;
        handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    if (editor.getBoolean("user_notifify", false)) {
                        notificationManager.cancel(id);
                        Intent i = new Intent(context, PreviewDemo.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delayInMilliseconds);
    }
}
