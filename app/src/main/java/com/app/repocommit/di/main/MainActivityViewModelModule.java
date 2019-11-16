package com.app.repocommit.di.main;

import com.app.repocommit.di.ViewModelKey;
import com.app.repocommit.ui.MainActivityViewModel;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public abstract ViewModel bindViewModel(MainActivityViewModel mainActivityViewModel);


}
