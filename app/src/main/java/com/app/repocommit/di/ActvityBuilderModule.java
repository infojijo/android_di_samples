package com.app.repocommit.di;

import com.app.repocommit.di.auth.AuthModule;
import com.app.repocommit.di.auth.AuthViewModelsModule;
import com.app.repocommit.di.main.MainFragmentBuildersModule;
import com.app.repocommit.di.main.MainViewModelsModule;
import com.app.repocommit.ui.auth.LoginActivity;
import com.app.repocommit.ui.main.MainActivity;

import dagger.Module;
        import dagger.android.ContributesAndroidInjector;

@Module
abstract public class ActvityBuilderModule {

    //Sub-component for LoginActivity
    @ContributesAndroidInjector(modules = {AuthViewModelsModule.class, AuthModule.class})
    abstract LoginActivity provideLoginActivity(); //providing(injecting) LoginActivity to AppComponent.

    @ContributesAndroidInjector(modules = {MainViewModelsModule.class, MainFragmentBuildersModule.class})
    abstract MainActivity provideMainActivity(); //providing(injecting) LoginActivity to AppComponent.

}

