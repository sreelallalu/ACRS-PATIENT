package com.acrs.userapp.ui.notification;

import com.acrs.userapp.data.DataManager;
import com.acrs.userapp.ui.base.BasePresenter;

public class NotificationPresenter<T extends NotificationView> extends BasePresenter<T> implements Notification_i_presenter<T> {


    public NotificationPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
