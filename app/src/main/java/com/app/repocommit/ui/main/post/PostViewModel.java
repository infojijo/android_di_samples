package com.app.repocommit.ui.main.post;

import android.util.Log;

import com.app.repocommit.SessionManager;
import com.app.repocommit.network.main.MainApi;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class PostViewModel extends ViewModel {

    private final SessionManager sessionManager;
    private final MainApi mainApi;

    private static final String TAG = "PostViewModel";

    @Inject
    public PostViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostViewModel: is WORKING..");
    }
}
