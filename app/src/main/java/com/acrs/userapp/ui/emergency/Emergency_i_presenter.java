package com.acrs.userapp.ui.emergency;

import com.acrs.userapp.ui.base.MvpPresenter;

import java.util.HashMap;

public interface Emergency_i_presenter<T extends EmergencyView> extends MvpPresenter<T> {


    void panicCall(HashMap<String, String> hashMap);
}
