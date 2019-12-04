package com.app.repocommit.ui;

import android.util.Log;

import com.app.repocommit.models.User;
import com.app.repocommit.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";
    private final AuthApi authApi;

    @Inject
    public MainActivityViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "MainActivityViewModel: WORKING....");
        if (authApi != null) {
            Log.d(TAG, "MainActivityViewModel: AuthAPI NOT NULL....");

            authApi.getUser(1).toObservable()
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<User>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(User value) {
                            Log.d(TAG, "onNext: EMAIL" + value.getEmail());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: ", e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
}
