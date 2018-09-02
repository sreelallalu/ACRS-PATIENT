package com.acrs.userapp.ui.base;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.acrs.userapp.App;
import com.acrs.userapp.R;
import com.acrs.userapp.data.DataManager;
import com.acrs.userapp.di.component.ActivityComponent;
import com.acrs.userapp.di.component.DaggerActivityComponent;
import com.acrs.userapp.di.module.ActivityModule;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity implements MvpView {
    private ActivityComponent activityComponent;
    protected Bitmap bitmapMain;
    protected int colorMain;

    protected String userToken;
    private ProgressDialog dialog;

    @Inject
    protected DataManager dataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((App) getApplication()).getComponent())
                .build();
        activityComponent.inject(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("loading...");
        if (dataManager.getFirebaseID() == null) {
            String firebaseID = FirebaseInstanceId.getInstance().getToken();

            if (firebaseID != null) {

                dataManager.setFirebaseID(firebaseID);

            }

        }

    }


    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }


    @Override
    public void SnakBarId(int msg) {

        try {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    msg, Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(getResources().getColor(R.color.snackbarcolor));
            TextView textView = (TextView) sbView
                    .findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
            snackbar.show();
        } catch (Exception e) {
        }

    }

    static Callback addCallback;

    public interface Callback {
        void back();
    }

    public void SnakBarCallback(String msg, Callback callback) {
        addCallback = callback;
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(getResources().getColor(R.color.snackbarcolor));
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
        snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {

                addCallback.back();

            }
        });


    }

    @Override
    public void SnakBarString(String msg) {
        try {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                    msg, Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            sbView.setBackgroundColor(getResources().getColor(R.color.snackbarcolor));
            TextView textView = (TextView) sbView
                    .findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(ContextCompat.getColor(this, R.color.white));
            snackbar.show();
        } catch (Exception e) {
        }
    }

    @Override
    public void progresShow(boolean load) {
        if (dialog != null && load)

        {
            dialog.show();
        } else if (dialog != null && !load) {
            dialog.dismiss();
        }
    }

    @Override
    public void progresCancel(boolean cancel) {
        if (dialog != null && cancel) {
            dialog.setCancelable(cancel);
        } else if (dialog != null && !cancel)

        {
            dialog.setCancelable(cancel);
        }

    }

}
