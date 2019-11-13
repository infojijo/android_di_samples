package com.app.repocommit.di;

import com.app.repocommit.BaseApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

//interface act as the controller in this DI framework.

@Component(modules = {
        AndroidSupportInjectionModule.class //to enable Android injection in the app.

})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    //overriding the builder class
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder bindAppBase(BaseApplication application);
        //binds instance of BaseApplication to AppComponent via Builder.

        AppComponent buildMyAppComponent();
        //returns the AppComponent instance.
    }
}
