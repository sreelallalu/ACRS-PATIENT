package com.acrs.userapp.ui.buddy.buddy_add;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.acrs.userapp.R;
import com.acrs.userapp.databinding.ActivityBuddyListBinding;
import com.acrs.userapp.ui.base.BaseActivity;
import com.acrs.userapp.ui.buddy.buddy_list.BuddyListAdapter;
import com.acrs.userapp.ui.buddy.buddy_list.BuddyListModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

public class BuddyRequestViewActvity extends BaseActivity implements BuddyRequestViewView,BuddyListAdapter.OnAdapterListener,SwipeRefreshLayout.OnRefreshListener {
    ActivityBuddyListBinding binding;
    private boolean refreshclick;

    @Inject
    BuddyRequestView_i_presenter<BuddyRequestViewView> presenter;

    private BuddyListAdapter listAdapter;
    private String u_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_buddy_list);
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        initialize();
    }

    @Override
    public void initialize() {
        binding.swipeRefresh.setOnRefreshListener(this);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new BuddyListAdapter(new ArrayList<BuddyListModel>(), this,true);
        binding.recycler.setAdapter(listAdapter);
        refreshData();
    }

    @Override
    public void onFailerApi() {
        binding.swipeRefresh.setRefreshing(false);
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessApi(List<BuddyListModel> list) {
        if (list != null && list.size() == 0) {
            super.SnakBarString("Buddy list items empty");
        }
        binding.swipeRefresh.setRefreshing(false);
        binding.progressBar.setVisibility(View.GONE);
        listAdapter.setList(list != null ? list.size() > 0 ? list : new ArrayList<BuddyListModel>() : new ArrayList<BuddyListModel>());
    }

    @Override
    public void acceptFailed() {
        super.progresCancel(false);
    }

    @Override
    public void acceptSuccess() {
        super.progresCancel(false);


    }

    public void refreshData() {
        if (refreshclick) {
            binding.progressBar.setVisibility(View.GONE);
        } else {
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        refreshclick = false;


        HashMap<String, String> hashMap = new HashMap<>();


        String userdata = dataManager.getUserdata();

        try {
            JSONObject userOBJ = new JSONObject(userdata);
            u_id = userOBJ.getString("id");

        } catch (JSONException e) {
            e.printStackTrace();
        }hashMap.put("tag", "Viewrequest");

        hashMap.put("userid", u_id);
        Log.e("hashmap",hashMap.toString());

        presenter.buddyRequestList(hashMap);
    }

    @Override
    public void adapterItemClick(BuddyListModel item) {

       HashMap<String,String> hashMap=new HashMap<>();
       hashMap.put("buddyid",item.getBuddy_id());
       hashMap.put("userid",u_id!=null?u_id:"");
       hashMap.put("status","1");
       hashMap.put("tag","Approve");



       super.progresShow(true);
       presenter.buddyaccept(hashMap);


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
}
