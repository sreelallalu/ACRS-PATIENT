package com.acrs.userapp.ui.medicine.medicine_add;

import com.acrs.userapp.data.DataManager;
import com.acrs.userapp.ui.base.BasePresenter;

import javax.inject.Inject;

public class MedicineCallPresenter<T extends MedicineCallView> extends BasePresenter<T> implements MedicineCall_i_presenter<T> {

    @Inject
    public MedicineCallPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
