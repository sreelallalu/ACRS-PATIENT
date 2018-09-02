package com.acrs.userapp.ui.buddy.buddy_add;

import com.acrs.userapp.ui.base.MvpPresenter;

import java.util.HashMap;

public interface BuddyRequestView_i_presenter<T extends BuddyRequestViewView> extends MvpPresenter<T> {
    void buddyRequestList(HashMap<String, String> hashMap);
    void buddyaccept(HashMap<String,String> hashMap);
}
