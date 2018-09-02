package com.acrs.userapp.ui.register;

import com.acrs.userapp.ui.base.MvpView;

public interface RegisterView extends MvpView {
    void initialize();
    void register();
    void onFailerApi();
    void onSuccessApi();
}
