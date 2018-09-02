package com.acrs.userapp.ui.buddy.buddy_list;

import com.acrs.userapp.ui.base.MvpPresenter;

import java.util.HashMap;

public interface BuddyList_i_presenter<T extends BuddyListView> extends MvpPresenter<T> {

    void buddyList(HashMap<String,String> hashMap);
    void buddyRemove(HashMap<String,String> hashMap);
}
