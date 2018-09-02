package com.acrs.userapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.acrs.userapp.data.DataManager;
import com.acrs.userapp.ui.base.BaseActivity;
import com.acrs.userapp.ui.dashboard.DashboardActivty;
import com.acrs.userapp.ui.login.LoginActivity;

import javax.inject.Inject;

import static android.view.WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;

public class Splash extends BaseActivity {


    @Inject
    DataManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(FLAG_LAYOUT_NO_LIMITS);
        TextView splash_txt = findViewById(R.id.splash_txt);

        Intent intent = null;
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/splash_t1.ttf");
        splash_txt.setTypeface(typeface);


        if (dataManager.getUserId() != null) {
            intent = new Intent(this, DashboardActivty.class);

        } else {
            intent = new Intent(this, LoginActivity.class);

        }
        final Intent finalIntent = intent;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(finalIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 3000);

    }

    @Override
    public void onBackPressed() {

    }
}
