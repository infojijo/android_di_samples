package com.app.repocommit.ui;

import android.util.Log;

import com.app.repocommit.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";
    private final AuthApi authApi;

    @Inject
    public MainActivityViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "MainActivityViewModel: WORKING....");
        if (authApi != null) {
            Log.d(TAG, "MainActivityViewModel: AuthAPI NOT NULL....");
        }
    }
}
