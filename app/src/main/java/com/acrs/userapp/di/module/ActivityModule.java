package com.acrs.userapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.acrs.userapp.di.ActivityContext;
import com.acrs.userapp.ui.buddy.buddy_add.BuddyRequestViewPresenter;
import com.acrs.userapp.ui.buddy.buddy_add.BuddyRequestViewView;
import com.acrs.userapp.ui.buddy.buddy_add.BuddyRequestView_i_presenter;
import com.acrs.userapp.ui.buddy.buddy_list.BuddyListPresenter;
import com.acrs.userapp.ui.buddy.buddy_list.BuddyListView;
import com.acrs.userapp.ui.buddy.buddy_list.BuddyList_i_presenter;
import com.acrs.userapp.ui.dashboard.DashboardPresenter;
import com.acrs.userapp.ui.dashboard.DashboardView;
import com.acrs.userapp.ui.dashboard.Dashboard_i_presenter;
import com.acrs.userapp.ui.emergency.EmergencyPresenter;
import com.acrs.userapp.ui.emergency.EmergencyView;
import com.acrs.userapp.ui.emergency.Emergency_i_presenter;
import com.acrs.userapp.ui.login.LoginPresenter;
import com.acrs.userapp.ui.login.LoginView;
import com.acrs.userapp.ui.login.Login_i_presenter;
import com.acrs.userapp.ui.medicine.medicine_add.MedicineCallPresenter;
import com.acrs.userapp.ui.medicine.medicine_add.MedicineCallView;
import com.acrs.userapp.ui.medicine.medicine_add.MedicineCall_i_presenter;
import com.acrs.userapp.ui.medicine.medicine_list.MedicineListPresenter;
import com.acrs.userapp.ui.medicine.medicine_list.MedicineListView;
import com.acrs.userapp.ui.medicine.medicine_list.MedicineList_i_presenter;
import com.acrs.userapp.ui.register.RegisterPresenter;
import com.acrs.userapp.ui.register.RegisterView;
import com.acrs.userapp.ui.register.Register_i_presenter;

import dagger.Module;
import dagger.Provides;


/**
 * Created by sreelal on 13/12/17.
 */
@Module
public class ActivityModule {
    AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {

        this.activity = activity;
    }


    @Provides
    @ActivityContext
    Context provideContext() {

        return activity;
    }


    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    Login_i_presenter<LoginView> login_i_presenter(LoginPresenter<LoginView> loginPresenter) {
        return loginPresenter;
    }

    @Provides
    Dashboard_i_presenter<DashboardView> dashboard_i_presenter(DashboardPresenter<DashboardView> presenter) {
        return presenter;
    }

    @Provides
    Register_i_presenter<RegisterView> register_i_presenter(RegisterPresenter<RegisterView> presenter) {
        return presenter;
    }


    @Provides
    MedicineList_i_presenter<MedicineListView> medicine_i_presenter(MedicineListPresenter<MedicineListView> presenter) {
        return presenter;
    }

    @Provides
    MedicineCall_i_presenter<MedicineCallView> medicineadd_i_presenter(MedicineCallPresenter<MedicineCallView> presenter) {
        return presenter;
    }

    @Provides
    BuddyList_i_presenter<BuddyListView> buddyAdd_i_presenter(BuddyListPresenter<BuddyListView> presenter) {
        return presenter;
    }

    @Provides
    BuddyRequestView_i_presenter<BuddyRequestViewView> buddyrequest_i_presenter(BuddyRequestViewPresenter<BuddyRequestViewView> presenter) {
        return presenter;
    }

    @Provides
    Emergency_i_presenter<EmergencyView> emergency_i_presenter(EmergencyPresenter<EmergencyView> presenter) {
        return presenter;
    }

}
