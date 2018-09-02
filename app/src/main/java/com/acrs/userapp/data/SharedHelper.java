package com.acrs.userapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.acrs.userapp.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sreelal on 14/12/17.
 */
@Singleton
public class SharedHelper implements SharedPresenter {
    private SharedPreferences mSharedPreferences;

  public  String MAIN_DATA="main_data";
    String USER_ID="user_id";
    String USER_DATA="user_data";
   public  String USER_NOTIFY="user_notifify";


    @Inject
    public SharedHelper(@ApplicationContext Context context) {

        this.mSharedPreferences = context.getSharedPreferences("main_data", Context.MODE_PRIVATE);
    }



    @Override
    public void setUserId(String centerid) {
        mSharedPreferences.edit().putString(USER_ID, centerid).commit();
    }

    @Override
    public void setUserDetails(String userDetails) {
        mSharedPreferences.edit().putString(USER_DATA,userDetails).commit();

    }

    @Override
    public String getUserId() {
        return mSharedPreferences.getString(USER_ID,null);
    }

    @Override
    public String getUserdata() {
        return mSharedPreferences.getString(USER_DATA,null);
    }

    @Override
    public void setNotificationCancel(int id, boolean cancel) {
        mSharedPreferences.edit().putBoolean("user_notifify"+id,true).commit();
    }

    @Override
    public boolean getNotificationCancel(int id) {
        return  mSharedPreferences.getBoolean("user_notifify"+id,false);
    }

    @Override
    public void setFirebaseID(String firebaseID) {
        mSharedPreferences.edit().putString("firebase_id",firebaseID).commit();

    }

    @Override
    public String getFirebaseID() {
        return mSharedPreferences.getString("firebase_id",null);
    }
}
