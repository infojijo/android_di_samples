package com.app.repocommit.ui;

import android.util.Log;

import com.app.repocommit.models.User;
import com.app.repocommit.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private MediatorLiveData<User> authUser = new MediatorLiveData<>();


    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: WORKING....");
        if (authApi != null) {
            Log.d(TAG, "AuthViewModel: AuthAPI NOT NULL....");

        }
    }

    public void authenticateUserWithId(int userId) {
        final LiveData<User> source = LiveDataReactiveStreams
                .fromPublisher(authApi.getUser(userId).subscribeOn(Schedulers.io()));
        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });

    }

    public LiveData<User> observerUser() {
        return authUser;
    }
}
