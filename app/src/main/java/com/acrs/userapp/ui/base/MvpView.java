package com.acrs.userapp.ui.base;

/**
 * Created by sreelal on 22/1/18.
 */

public interface MvpView {

    void SnakBarId(int msg);

    void SnakBarString(String msg);

    void progresShow(boolean load);
    void progresCancel(boolean cancel);


}
