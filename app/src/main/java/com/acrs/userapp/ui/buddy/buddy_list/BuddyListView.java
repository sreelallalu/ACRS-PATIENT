package com.acrs.userapp.ui.buddy.buddy_list;

import com.acrs.userapp.ui.base.MvpView;

import java.util.List;

public interface BuddyListView extends MvpView{

    void initialize();
    void refreshData();
    void onFailerApi();
    void onSuccessApi(List<BuddyListModel> list);
    void removeFailed();
    void removeSuccess();
    String tempData="{\n" +
            "\t\"list\": [{\n" +
            "\t\t\t\"name\": \"kiran\",\n" +
            "\t\t\t\"phone\": \"95623043\",\n" +
            "\t\t\t\"active\": 1\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"krishanan\",\n" +
            "\t\t\t\"phone\": \"525525\",\n" +
            "\t\t\t\"active\": 0\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"name\": \"kiran\",\n" +
            "\t\t\t\"phone\": \"95623043\",\n" +
            "\t\t\t\"active\": 1\n" +
            "\t\t}\n" +
            "\t]\n" +
            "}";
}
