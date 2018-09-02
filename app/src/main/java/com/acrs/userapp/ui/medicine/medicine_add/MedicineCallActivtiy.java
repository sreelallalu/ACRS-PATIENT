package com.acrs.userapp.ui.medicine.medicine_add;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.acrs.userapp.PreviewDemo;
import com.acrs.userapp.R;
import com.acrs.userapp.ui.base.BaseActivity;

import java.util.HashMap;
import java.util.Locale;

public class MedicineCallActivtiy extends BaseActivity implements MedicineCallView, TextToSpeech.OnUtteranceCompletedListener, TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private String medicine_not;
    private String medicine_name;
    private boolean finishtime;
    private CountDownTimer countdowntimer;
    private LinearLayout layout;
    boolean display;


    private int TIME_INTERVAL = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_add);
        Intent intent = getIntent();
        try {
            medicine_not = intent.getStringExtra("med_not");
            medicine_name = intent.getStringExtra("med_name");
        } catch (Exception e) {
            e.printStackTrace();
            medicine_not = "medicine one called";
            medicine_name = "medicine one called";
        }
        medicine_not = "medicine one called";
        medicine_name = "medicine one called";
        TextView medi_name = findViewById(R.id.medicine_name);
        TextView medi_note = findViewById(R.id.medicine_note);
        layout = findViewById(R.id.backgroud);

        display = false;

        medi_name.setText(medicine_name);
        medi_note.setText(medicine_not);


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display = true;
                dismissApp();
                finish();
            }
        });
        textToSpeech = new TextToSpeech(this, this);
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                Log.e("speek down", utteranceId);

            }

            @Override
            public void onDone(String utteranceId) {

                Log.e("speek down", utteranceId);


            }

            @Override
            public void onError(String utteranceId) {
                Log.e("speek down error", utteranceId);
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (medicine_not != null)
                    speakOut(medicine_not);
            }
        }, 500);


        timerStart();
    }

    private void dismissApp() {
        countdowntimer.cancel();
        if (textToSpeech != null) {

            textToSpeech.stop();
            textToSpeech.shutdown();
            Log.d("tts", "TTS Destroyed");
        }



    }

    private void timerStart() {
        //txt_timer.setText("" + millisUntilFinished / 1000);
        countdowntimer = new CountDownTimer(TIME_INTERVAL, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {

                finishtime = true;
                if (!display) {

                    apiNotresponding();


                }


            }
        };
        countdowntimer.start();

    }

    private void apiNotresponding() {


        startActivity(new Intent(this, PreviewDemo.class));
        dismissApp();
        finish();


        /*super.progresShow(true);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("tag", "notresponding");
        hashMap.put("userid", dataManager.getUserId());
        RestBuilderPro.getService(LoginWebApi.class).login(hashMap).enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                MedicineCallActivtiy.super.progresShow(false);

                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();


                    } catch (Exception e) {
                        e.printStackTrace();

                        SnakBarString("Response error");


                    }

                } else {
                    SnakBarString("Login failed");


                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                SnakBarId(R.string.notconnect);
                MedicineCallActivtiy.super.progresShow(false);
            }
        });*/
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            textToSpeech.setOnUtteranceCompletedListener(this);
            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //    Log.e("TTS", "This Language is not supported");
            } else {

            }
        } else {

            //     Log.e("TTS", "Initilization Failed!");
        }
    }

    @Override
    protected void onDestroy() {


        //Close the Text to Speech Library
        if (textToSpeech != null) {

            textToSpeech.stop();
            textToSpeech.shutdown();
            Log.d("tts", "TTS Destroyed");
        }
        super.onDestroy();
    }

    private void speakOut(String txt) {

        if (txt != null) {

            HashMap<String, String> myHashAlarm = new HashMap<String, String>();
            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_ALARM));
            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SOME MESSAGE");
            textToSpeech.speak(txt, TextToSpeech.QUEUE_FLUSH, myHashAlarm);


        }
    }

    @Override
    public void onUtteranceCompleted(String utteranceId) {
        Log.e("finish", utteranceId);
        if (!finishtime) {
            try {
                Thread.sleep(1000);
                if (!display)
                    speakOut(medicine_not);
            } catch (InterruptedException e) {


            }
            //speakOut(medicine_not);
           /* new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (medicine_not != null)

                }
            }, 100);*/
        }
    }
}
