package com.app.repocommit.di.main;

import com.app.repocommit.ui.main.post.PostFragment;
import com.app.repocommit.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Injecting the Fragments to the app
 */
@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributePostFragment();
}
