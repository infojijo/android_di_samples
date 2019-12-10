package com.app.repocommit.ui.auth;

import android.util.Log;
import android.widget.ProgressBar;

import com.app.repocommit.models.User;
import com.app.repocommit.network.auth.AuthApi;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;
    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();


    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Log.d(TAG, "AuthViewModel: WORKING....");
        if (authApi != null) {
            Log.d(TAG, "AuthViewModel: AuthAPI NOT NULL....");

        }
    }

    public void authenticateUserWithId(int userId) {

        //setting loading status to the LiveData
        authUser.setValue(AuthResource.loading((User) null));

        final LiveData<AuthResource<User>> source = LiveDataReactiveStreams
                .fromPublisher(authApi.getUser(userId)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                //error user dummy object
                                User errorUser = new User();
                                errorUser.setId(-1);
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
        authUser.addSource(source, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });

    }

    public LiveData<AuthResource<User>> observerUser() {
        return authUser;
    }
}
