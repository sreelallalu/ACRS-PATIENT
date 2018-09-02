package com.acrs.userapp.di.component;

import android.app.Application;
import android.content.Context;

import com.acrs.userapp.App;
import com.acrs.userapp.data.DataManager;
import com.acrs.userapp.di.ApplicationContext;
import com.acrs.userapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sreelal on 13/12/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

   void inject(App app);

   @ApplicationContext
   Context context();


    Application application();
    DataManager getDataManager();

}
