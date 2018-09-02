package com.acrs.userapp.ui.buddy.buddy_add;

import com.acrs.userapp.ui.base.MvpView;
import com.acrs.userapp.ui.buddy.buddy_list.BuddyListModel;

import java.util.List;

public interface BuddyRequestViewView extends MvpView{
   void initialize();
   void onFailerApi();
    void onSuccessApi(List<BuddyListModel> list);
    void acceptFailed();
    void acceptSuccess();
}
