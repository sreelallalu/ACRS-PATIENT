package com.acrs.userapp.ui.dashboard;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.acrs.userapp.R;
import com.acrs.userapp.databinding.ActivityDashboardBinding;
import com.acrs.userapp.ui.base.BaseActivity;
import com.acrs.userapp.ui.buddy.buddy_add.BuddyRequestViewActvity;
import com.acrs.userapp.ui.buddy.buddy_list.BuddyListActvity;
import com.acrs.userapp.ui.emergency.EmergencyActvity;
import com.acrs.userapp.ui.login.LoginActivity;
import com.acrs.userapp.ui.medicine.medicine_list.MedicineListActvity;
import com.acrs.userapp.ui.notification.NotificationActivtiy;

import javax.inject.Inject;

public class DashboardActivty extends BaseActivity implements DashboardView, View.OnClickListener {


    @Inject
    Dashboard_i_presenter<DashboardView> presenter;
    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        //setContentView(R.layout.activity_dashboard);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        initialize();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:

                try {
                    logOut();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.item2:

                try {
                    String appname = getString(R.string.app_name);
                    String text = appname + "application"  + "This is my unique id " + dataManager.getUserId();

                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                    shareIntent.setType("text/plain");
                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(Intent.createChooser(shareIntent, "Share text..."));

                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void logOut() throws Exception {
        dataManager.setFirebaseID(null);
        dataManager.setUserDetails(null);
        dataManager.setUserId(null);
        dataManager.setNotificationCancel(1, false);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


    @Override
    public void initialize() {
        binding.buddy.setOnClickListener(this);
        binding.notification.setOnClickListener(this);
        binding.emergency.setOnClickListener(this);
        binding.medicine.setOnClickListener(this);
        binding.buddyrequest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.buddy.getId()) {

            Intent intent = new Intent(this, BuddyListActvity.class);
            startActivity(intent);
        }
        if (v.getId() == binding.emergency.getId()) {
            Intent intent = new Intent(this, EmergencyActvity.class);
            startActivity(intent);
        }
        if (v.getId() == binding.medicine.getId()) {
            Intent intent = new Intent(this, MedicineListActvity.class);
            startActivity(intent);
        }
        if (v.getId() == binding.notification.getId()) {
            Intent intent = new Intent(this, NotificationActivtiy.class);
            startActivity(intent);
        }
        if (v.getId() == binding.buddyrequest.getId()) {
            Intent intent = new Intent(this, BuddyRequestViewActvity.class);
            startActivity(intent);
        }
    }
}
