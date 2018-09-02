package com.acrs.userapp.ui.medicine.medicine_list;

import com.acrs.userapp.ui.base.MvpView;

import java.util.List;

public interface MedicineListView extends MvpView{

    void initialize();
    void refreshData();
    void onFailerApi();
    void onSuccessApi(List<MedicineListModel> list);
    void alertDialog();
    void alarmSetting();
}
