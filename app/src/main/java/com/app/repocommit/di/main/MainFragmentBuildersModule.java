package com.app.repocommit.di.main;

import com.app.repocommit.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

@ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

}
