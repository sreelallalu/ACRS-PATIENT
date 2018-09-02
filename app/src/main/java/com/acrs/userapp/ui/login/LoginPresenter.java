package com.acrs.userapp.ui.login;

import android.util.Log;

import com.acrs.userapp.R;
import com.acrs.userapp.data.DataManager;
import com.acrs.userapp.di.service.RestBuilderPro;
import com.acrs.userapp.ui.base.BasePresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter<T extends LoginView> extends BasePresenter<T> implements Login_i_presenter<T> {

    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void loginApiCall(HashMap<String, String> hashMap) {

        RestBuilderPro.getService(LoginWebApi.class).login(hashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        successResponse(res);
                    } catch (Exception e) {
                        e.printStackTrace();

                        getView().SnakBarString("Response error");
                        getView().onFailerApi();

                    }

                } else {
                    getView().SnakBarString("Login failed");
                    getView().onFailerApi();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                getView().SnakBarId(R.string.notconnect);
                getView().onFailerApi();
            }
        });
    }

    private void successResponse(String res) {


        try {
            Log.e("response_login",res);

            JSONObject jsonObject = new JSONObject(res);
            int succ = jsonObject.getInt("success");
            if (succ == 1) {

                JSONObject userdata = jsonObject.getJSONObject("data");
                String patientId = userdata.getString("Patient Id");
                getDataManager().setUserId(patientId);
                getDataManager().setUserDetails(userdata.toString());
                getView().onSuccessApi();
            }else{
                getView().onFailerApi();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            getView().onFailerApi();

        }


    }
}
