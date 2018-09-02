package com.acrs.userapp.ui.medicine.medicine_list;

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

public class MedicineListPresenter<T extends MedicineListView> extends BasePresenter<T> implements MedicineList_i_presenter<T> {

    @Inject
    public MedicineListPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void medicineListApi(HashMap<String, String> hashMap) {
        RestBuilderPro.getService(MedicineListWebApi.class).medicinelist(hashMap).enqueue(new Callback<ResponseBody>() {
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

    private void successResponse(String res) throws JSONException {
        Log.e("response_",res);

        JSONObject jsonObject = new JSONObject(res);

        int succ = jsonObject.getInt("success");
        if (succ == 1) {
            List<MedicineListModel> listModels = new ArrayList<MedicineListModel>();

            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                String m_id = jsonObject1.getString("m_id");
                String Patient_id = jsonObject1.getString("Patient_id");
                String Medicine = jsonObject1.getString("Medicine");
                String time = jsonObject1.getString("time");
                String note = jsonObject1.getString("note");
                String u_id = jsonObject1.getString("u_id");
                String fullname = jsonObject1.getString("fullname");
                String age = jsonObject1.getString("age");
                String gender = jsonObject1.getString("gender");
                String contact_no = jsonObject1.getString("contact_no");
                String point_ofcontact = jsonObject1.getString("point_ofcontact");
                String username = jsonObject1.getString("username");
                String Unique_id = jsonObject1.getString("Unique_id");
                MedicineListModel model = new MedicineListModel();

                model.setM_id(m_id);
                model.setPatient_id(Patient_id);
                model.setMedicine(Medicine);
                model.setTime(time);
                model.setNote(note);
                model.setU_id(u_id);
                model.setFullname(fullname);
                model.setAge(age);
                model.setGender(gender);
                model.setContact_no(contact_no);
                model.setPoint_ofcontact(point_ofcontact);
                model.setUsername(username);
                model.setU_id(Unique_id);
                listModels.add(model);
            }
            getView().onSuccessApi(listModels);


        }else{
            getView().SnakBarString("No Medicine Added Currently");
           getView().onFailerApi();
        }


        }

}
