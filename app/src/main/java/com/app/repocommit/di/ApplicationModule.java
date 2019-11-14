package com.app.repocommit.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import com.app.repocommit.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import androidx.core.content.ContextCompat;
import dagger.Module;
import dagger.Provides;

/*submodule other than activity provider.

* */
@Module
public class ApplicationModule
    {

        @Provides //image placeholder,error holder
        static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

        @Provides //glide object
        static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

        @Provides // drawable object
        static Drawable provideAppDrawable(Application application){
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }
    }