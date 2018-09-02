package com.acrs.userapp.ui.medicine.medicine_list;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.acrs.userapp.R;
import com.acrs.userapp.databinding.ActivityMedicineListBinding;
import com.acrs.userapp.ui.base.BaseActivity;
import com.acrs.userapp.ui.dashboard.DashboardActivty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class MedicineListActvity extends BaseActivity implements MedicineListView, SwipeRefreshLayout.OnRefreshListener, MedicineAdapter.OnAdapterListener {


    @Inject
    MedicineList_i_presenter<MedicineListView> presenter;
    ActivityMedicineListBinding binding;
    private boolean refreshclick;
    private MedicineAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_medicine_list);
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
    public void initialize() {
        binding.swipeRefresh.setOnRefreshListener(this);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new MedicineAdapter(new ArrayList<MedicineListModel>(), this);
        binding.recycler.setAdapter(listAdapter);
        refreshData();
       // alarmSetting();

    }


    @Override
    public void refreshData() {
        if (refreshclick) {
            binding.progressBar.setVisibility(View.GONE);
        } else {
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        refreshclick = false;


        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("tag",MedicineListWebApi.MedicineParams.TAGNAME);
        hashMap.put(MedicineListWebApi.MedicineParams.PATIENT_ID,dataManager.getUserId());
        presenter.medicineListApi(hashMap);
    }

    @Override
    public void onFailerApi() {
        binding.swipeRefresh.setRefreshing(false);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessApi(List<MedicineListModel> list) {
        if (list != null && list.size() == 0) {
            super.SnakBarString("Medicine list items empty");
        }
        binding.swipeRefresh.setRefreshing(false);
        binding.progressBar.setVisibility(View.GONE);
        listAdapter.setList(list != null ? list.size() > 0 ? list : new ArrayList<MedicineListModel>() : new ArrayList<MedicineListModel>());


    }

    @Override
    public void alertDialog() {
        new AlertDialog.Builder(this)

                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you sure to Accept")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .show();
    }

    @Override
    public void alarmSetting() {

        Calendar cal = Calendar.getInstance();
        String aTime = "2018-08-15 12:30:22";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {

            cal.setTime(sdf.parse(aTime));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this, DashboardActivty.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 1253, intent, PendingIntent.FLAG_UPDATE_CURRENT|  Intent.FILL_IN_DATA);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent );
        Toast.makeText(this, "Alarm worked.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.swipeRefresh.setRefreshing(true);
                refreshclick = true;
                refreshData();

            }
        }, 1000);
    }

    @Override
    public void adapterItemClick(MedicineListModel item) {

    }
}
