package com.app.repocommit.di;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    static String getAppName(){
        //return type should be unique here for all the methods.
        return "Android DI App";
    }
}
