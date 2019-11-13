package com.app.repocommit.di;

import com.app.repocommit.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract public class ActvityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity provideMainActivity(); //providing(injecting) MainActivity to AppComponent.

}

