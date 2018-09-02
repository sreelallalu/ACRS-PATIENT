package com.acrs.userapp.ui.login;

import com.acrs.userapp.ui.base.MvpPresenter;

import java.util.HashMap;

public interface Login_i_presenter<T extends LoginView> extends MvpPresenter<T> {
    void loginApiCall(HashMap<String,String> hashMap);



}
