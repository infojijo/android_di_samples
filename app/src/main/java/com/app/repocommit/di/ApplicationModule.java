package com.app.repocommit.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.app.repocommit.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Singleton;

import androidx.core.content.ContextCompat;
import dagger.Module;
import dagger.Provides;

/*submodule other than activity provider.

 * */
@Module
public class ApplicationModule {
    @Singleton // scope added to the component
    @Provides //image placeholder,error holder
    static RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton // scope added to the component
    @Provides //glide object
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton // scope added to the component
    @Provides // drawable object
    static Drawable provideAppDrawable(Application application) {
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }
}