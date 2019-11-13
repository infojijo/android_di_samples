package com.app.repocommit;

import com.app.repocommit.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        //bind the application instance and to the Binder and injects appcomponent
        return DaggerAppComponent.builder().bindAppBase(this).buildMyAppComponent();
    }
}
