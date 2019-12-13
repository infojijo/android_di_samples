package com.app.repocommit.ui.main.profile;

import android.util.Log;

import com.app.repocommit.SessionManager;
import com.app.repocommit.models.User;
import com.app.repocommit.ui.auth.AuthResource;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Log.d(TAG, "ProfileViewModel: ViewModel is ready!!");
    }

    //Observing the Session Manager for User Information.
    public LiveData<AuthResource<User>> getAuthResource(){
        return sessionManager.getAuthUser();
    }
}
