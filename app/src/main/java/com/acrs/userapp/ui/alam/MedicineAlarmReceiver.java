package com.acrs.userapp.ui.alam;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;

import com.acrs.userapp.ui.medicine.medicine_add.MedicineCallActivtiy;

public class MedicineAlarmReceiver extends BroadcastReceiver {

    private Context context;
    private SharedPreferences editor;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + context.getPackageName() + "/raw/alarm_tune");
            int notificId = intent.getIntExtra("notification_id", 0);
          /*  editor = context.getSharedPreferences("main_data", Context.MODE_PRIVATE);
            editor.edit().putBoolean("user_notifify", false).commit();*/

            Intent i = new Intent(context, MedicineCallActivtiy.class);
            i.putExtra("med_not",intent.getStringExtra("medi_not"));
            i.putExtra("med_name",intent.getStringExtra("medi_name"));
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);


    /*        NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setSmallIcon(R.drawable.ic_launcher_background)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                            R.mipmap.ic_launcher));
            // Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mBuilder.setSound(alarmSound);
            mBuilder.setContentTitle("Hello you have to take medicine");
            mBuilder.setContentText("Medicine name dose 1");
            Intent intent_Yes = new Intent(context, MedicineNotificationCancel.class);
            intent_Yes.putExtra("notification_id", notificId);
            PendingIntent pend_Yes = PendingIntent.getBroadcast(context, 0,
                    intent_Yes, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.addAction(R.drawable.stop_btn, "Stop", pend_Yes);
            mBuilder.setOngoing(true);

            notificationManager.notify(notificId, mBuilder.build());
            removeNotification(notificId, notificationManager);*/
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
