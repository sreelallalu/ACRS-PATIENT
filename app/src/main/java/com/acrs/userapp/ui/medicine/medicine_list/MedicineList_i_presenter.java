package com.acrs.userapp.ui.medicine.medicine_list;

import com.acrs.userapp.ui.base.MvpPresenter;

import java.util.HashMap;

public interface MedicineList_i_presenter<T extends MedicineListView> extends MvpPresenter<T> {

    void medicineListApi(HashMap<String, String> hashMap);

}
