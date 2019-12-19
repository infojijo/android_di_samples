package com.app.repocommit.di;


import com.app.repocommit.viewmodel.ViewModelProviderFactory;

import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;

/**
 * DI module for viewmodel injection/binding to the app
 */
@Module
public abstract class ViewModelFactoryModule {

    @Binds //if the dependency is abstract.
    public abstract ViewModelProvider.Factory
    bindViewModelProvider(ViewModelProviderFactory viewModelProviderFactory);

}
