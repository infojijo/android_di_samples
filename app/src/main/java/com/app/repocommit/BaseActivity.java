package com.app.repocommit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.app.repocommit.models.User;
import com.app.repocommit.ui.auth.AuthResource;
import com.app.repocommit.ui.auth.LoginActivity;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();

    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void subscribeObservers() {
        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING: {
                            break;
                        }
                        case AUTHENTICATED: {
                            Log.d(TAG, "onChanged: Logged in successfully");
                            break;
                        }
                        case NOT_AUTHENTICATED: {
                            navigateToLogin();
                            break;
                        }
                        case ERROR: {
                            Log.d(TAG, "onChanged: error while authenticating");
                            break;
                        }
                    }
                }
            }
        });
    }
}
