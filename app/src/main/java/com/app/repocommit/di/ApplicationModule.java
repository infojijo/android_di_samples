package com.app.repocommit.di;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    static String getAppName(){
        return "Android DI App";

    }
}
