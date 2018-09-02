package com.acrs.userapp.ui.login;


import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginWebApi {

    interface LoginParams{

        String NAME="username";
        String PASSWRD="password";
        String TAG="tag";
        String TAG_USER="userlogin";
        String FIREBASE="firebasetocken";
    }


    @FormUrlEncoded
    @POST("index.php")
    Call<ResponseBody> login(@FieldMap HashMap<String,String> hashMap);

}
