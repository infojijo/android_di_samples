package com.app.repocommit.di;

import android.app.Application;

import com.app.repocommit.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

//interface act as the controller in this DI framework.
@Singleton // scope added to the component
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActvityBuilderModule.class,
                ApplicationModule.class,
        })
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}