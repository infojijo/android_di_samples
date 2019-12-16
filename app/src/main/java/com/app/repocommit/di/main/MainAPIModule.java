package com.app.repocommit.di.main;

import com.app.repocommit.network.main.MainApi;
import com.app.repocommit.ui.main.post.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainAPIModule {

    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }

    @Provides
    static PostsRecyclerAdapter providerAdapter() {
        return new PostsRecyclerAdapter();

    }
}
