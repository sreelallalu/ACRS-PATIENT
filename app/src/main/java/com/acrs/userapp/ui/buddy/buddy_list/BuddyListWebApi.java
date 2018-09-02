package com.acrs.userapp.ui.buddy.buddy_list;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BuddyListWebApi {


    @POST("index.php")
    @FormUrlEncoded
    Call<ResponseBody> buddylist(@FieldMap HashMap<String, String> hashMap);
}
