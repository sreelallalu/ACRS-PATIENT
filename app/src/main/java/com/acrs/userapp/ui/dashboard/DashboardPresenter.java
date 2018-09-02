package com.acrs.userapp.ui.dashboard;

import com.acrs.userapp.data.DataManager;
import com.acrs.userapp.ui.base.BasePresenter;

import javax.inject.Inject;

public class DashboardPresenter <T extends DashboardView> extends BasePresenter<T> implements Dashboard_i_presenter<T> {



    @Inject
    public DashboardPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
