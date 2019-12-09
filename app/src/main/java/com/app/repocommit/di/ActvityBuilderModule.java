package com.app.repocommit.di;

import com.app.repocommit.di.main.AuthModule;
import com.app.repocommit.di.main.AuthViewModelsModule;
import com.app.repocommit.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract public class ActvityBuilderModule {

    //Sub-component for MainActivity
    @ContributesAndroidInjector(modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract MainActivity provideMainActivity(); //providing(injecting) MainActivity to AppComponent.

}

