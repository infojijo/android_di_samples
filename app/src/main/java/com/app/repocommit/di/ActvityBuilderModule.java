package com.app.repocommit.di;

import com.app.repocommit.di.main.MainActivityViewModelModule;
import com.app.repocommit.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract public class ActvityBuilderModule {

    @ContributesAndroidInjector(modules = MainActivityViewModelModule.class)
    abstract MainActivity provideMainActivity(); //providing(injecting) MainActivity to AppComponent.

}

