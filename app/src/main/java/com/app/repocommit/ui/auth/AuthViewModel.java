package com.app.repocommit.ui.auth;

import android.util.Log;

import com.app.repocommit.SessionManager;
import com.app.repocommit.models.User;
import com.app.repocommit.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    //private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();
    private SessionManager sessionManager = new SessionManager();


    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: WORKING....");
    }

    private LiveData<AuthResource<User>> queryUserId(int userId) {
        return LiveDataReactiveStreams
                .fromPublisher(authApi.getUser(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                //error user dummy object
                                User errorUser = new User(-1,null,null,null);
                                return errorUser;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if (user.getId() == -1) {
                                    return AuthResource.error("ERROR from Source", (User) null);
                                }
                                //normal expected result
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io()));
    }

    public void authenticateUserWithId(int userId) {

        Log.d(TAG, "authenticateUserWithId: login attempt");
        sessionManager.authenticateWithId(queryUserId(userId));

    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }
}
