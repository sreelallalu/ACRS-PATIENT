package com.acrs.userapp.data;

import android.content.Context;

import com.acrs.userapp.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by sreelal on 28/12/17.
 */
@Singleton
public class AppDataManager implements DataManager {
    private Context mContext;
    private SharedPresenter mPreferencesHelper;


    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          SharedPresenter preferencesHelper) {
        mContext = context;

        mPreferencesHelper = preferencesHelper;

    }


    @Override
    public void setUserId(String centerid) {

        mPreferencesHelper.setUserId(centerid);

    }

    @Override
    public void setUserDetails(String userDetails) {
     mPreferencesHelper.setUserDetails(userDetails);
    }

    @Override
    public String getUserId() {
        return mPreferencesHelper.getUserId();
    }

    @Override
    public String getUserdata() {
        return mPreferencesHelper.getUserdata();
    }

    @Override
    public void setNotificationCancel(int id, boolean cancel) {
        mPreferencesHelper.setNotificationCancel(id,cancel);
    }

    @Override
    public boolean getNotificationCancel(int id) {
        return mPreferencesHelper.getNotificationCancel(id);
    }

    @Override
    public void setFirebaseID(String firebaseID) {
        mPreferencesHelper.setFirebaseID(firebaseID);
    }

    @Override
    public String getFirebaseID() {
        return mPreferencesHelper.getFirebaseID();
    }
}
