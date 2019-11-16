package com.app.repocommit.ui;

import android.util.Log;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    @Inject
    public MainActivityViewModel() {
        Log.d(TAG, "MainActivityViewModel: WORKING....");
    }
}
