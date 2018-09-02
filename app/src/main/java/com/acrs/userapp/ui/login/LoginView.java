package com.acrs.userapp.ui.login;

import com.acrs.userapp.ui.base.MvpView;

public interface LoginView extends MvpView {

   void initialize();
   void loginClick();
   void register();
   void onFailerApi();
   void onSuccessApi();


}
