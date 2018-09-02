package com.acrs.userapp.ui.register;


import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterWebApi {

    interface RegisterParms{

        String NAME="name";
        String GENDER="gender";
        String AGE="age";
        String POC="poc";
        String MOBILE="phonenumber";
        String EMAIL="email";
        String PASSWORD="password";
        String TAGNAME="user_reg";
        String FIREBASE="firebasetocken";
    }
    @POST("index.php")
    @FormUrlEncoded
    Call<ResponseBody> register(@FieldMap HashMap<String,String> hashMap);
}
