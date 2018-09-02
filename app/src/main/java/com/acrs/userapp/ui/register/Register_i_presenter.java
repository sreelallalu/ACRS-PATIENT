package com.acrs.userapp.ui.register;

import com.acrs.userapp.ui.base.MvpPresenter;
import com.acrs.userapp.ui.base.MvpView;

import java.util.HashMap;

public interface Register_i_presenter<T extends MvpView> extends MvpPresenter<T> {

    void register(HashMap<String,String> hashMap);
}
