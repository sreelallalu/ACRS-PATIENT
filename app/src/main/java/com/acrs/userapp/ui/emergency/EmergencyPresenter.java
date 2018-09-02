package com.acrs.userapp.ui.emergency;

import android.util.Log;

import com.acrs.userapp.R;
import com.acrs.userapp.data.DataManager;
import com.acrs.userapp.di.service.RestBuilderPro;
import com.acrs.userapp.ui.base.BasePresenter;
import com.acrs.userapp.ui.buddy.buddy_list.BuddyListWebApi;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmergencyPresenter<T extends EmergencyView> extends BasePresenter<T> implements Emergency_i_presenter<T> {

    @Inject
    public EmergencyPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void panicCall(HashMap<String, String> hashMap) {
        RestBuilderPro.getService(BuddyListWebApi.class).buddylist(hashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        Log.e("response",res);
                     //   successResponseAccept(res);
                    } catch (Exception e) {
                        e.printStackTrace();

                        getView().SnakBarString("Response error");
                        getView().panicFailed();

                    }

                } else {
                    getView().SnakBarString("Something went wrong");
                    getView().panicFailed();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                getView().SnakBarId(R.string.notconnect);
                getView().panicFailed();
            }
        });


    }
}
