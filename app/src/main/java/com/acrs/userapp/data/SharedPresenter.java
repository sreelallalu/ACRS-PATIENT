package com.acrs.userapp.data;

/**
 * Created by sreelal on 15/12/17.
 */

public interface SharedPresenter {

    void setUserId(String centerid);
    void setUserDetails(String userDetails);
    String getUserId();
    String getUserdata();
    void setNotificationCancel(int id,boolean cancel);
    boolean getNotificationCancel(int id);
    void setFirebaseID(String firebaseID);
    String getFirebaseID();


}

