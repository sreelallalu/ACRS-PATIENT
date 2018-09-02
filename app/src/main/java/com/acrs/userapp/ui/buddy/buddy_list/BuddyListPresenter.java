package com.acrs.userapp.ui.buddy.buddy_list;

import android.util.Log;

import com.acrs.userapp.R;
import com.acrs.userapp.data.DataManager;
import com.acrs.userapp.di.service.RestBuilderPro;
import com.acrs.userapp.ui.base.BasePresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuddyListPresenter<T extends BuddyListView> extends BasePresenter<T> implements BuddyList_i_presenter<T> {

    @Inject
    public BuddyListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void buddyList(HashMap<String, String> hashMap) {

        RestBuilderPro.getService(BuddyListWebApi.class).buddylist(hashMap).enqueue(new Callback<ResponseBody>() {
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
                    getView().SnakBarString("Something went wrong");
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

    @Override
    public void buddyRemove(HashMap<String, String> hashMap) {
        RestBuilderPro.getService(BuddyListWebApi.class).buddylist(hashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        Log.e("response", res);
                        JSONObject jsonObject = new JSONObject(res);
                        if (jsonObject.getString("status").equals("1")) {
                          getView().removeSuccess();

                        } else {
                            getView().SnakBarString("Already Done This Process");
                            getView().removeFailed();

                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                        getView().SnakBarString("Response error");
                        getView().removeFailed();

                    }

                } else {
                    getView().SnakBarString("Something went wrong");
                    getView().removeFailed();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                getView().SnakBarId(R.string.notconnect);
                getView().removeFailed();
            }
        });


    }

    private void successResponse(String res) throws JSONException {
        Log.e("response_", res);

        JSONObject jsonObject = new JSONObject(res);
        int succ = jsonObject.getInt("success");
        if (succ == 1) {

            List<BuddyListModel> list_array = new ArrayList<>();
            JSONArray listarr = jsonObject.getJSONArray("data");
            for (int i = 0; i < listarr.length(); i++) {

                JSONObject object = listarr.getJSONObject(i);
                String buddy_id = object.getString("buddy_id");
                String buddy_name = object.getString("buddy_name");
                String email = object.getString("email");
                String phone_no = object.getString("phone_no");
                String gender = object.getString("gender");
                String firebasetocken = object.getString("firebasetocken");
                String status = object.getString("status");
                String Message = object.getString("Message");

                BuddyListModel model = new BuddyListModel();
                model.setBuddy_id(buddy_id);
                model.setBuddy_name(buddy_name);
                model.setEmail(email);
                model.setFirebasetocken(firebasetocken);
                model.setGender(gender);
                model.setPhone_no(phone_no);
                model.setStatus(status);
                model.setMessage(Message);


                list_array.add(model);


            }
            getView().onSuccessApi(list_array);

        } else {
            getView().SnakBarString("Currently There are No Buddies");

            getView().onFailerApi();
        }


    }
}

