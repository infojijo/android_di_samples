package com.app.repocommit.ui.auth;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.repocommit.R;
import com.app.repocommit.models.User;
import com.app.repocommit.viewmodel.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";

    private AuthViewModel authViewModel;
    private EditText userName;
    private Button userLogin;
    private TextView userLoginStatus;
    private ProgressBar progressBar;
    //Viewmodel factory injection
    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLogo();

        //instantiating the viewmodel factory

        authViewModel = ViewModelProviders.of(this, providerFactory)
                .get(AuthViewModel.class);

        userName = findViewById(R.id.editText);
        userLogin = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progress_bar);
        userLogin.setOnClickListener(this);
        userLoginStatus = findViewById(R.id.txtUserLoggedIn);
        subscribeObservers();
    }

    public void subscribeObservers() {
        authViewModel.observerUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING: {
                            showProgressBar(true);
                            break;
                        }
                        case AUTHENTICATED: {
                            showProgressBar(false);
                            showLoginDetails(userAuthResource);
                            break;
                        }
                        case NOT_AUTHENTICATED: {
                            showProgressBar(false);
                            Toast.makeText(getApplicationContext(),
                                    "Authentication Issue",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case ERROR: {
                            showProgressBar(false);
                            Toast.makeText(getApplicationContext(),
                                    "Error while Authentication : "
                                            + userAuthResource.message,
                                    Toast.LENGTH_SHORT).show();
                            break;
                        }

                    }
                }
            }
        });

    }

    private void showLoginDetails(AuthResource<User> user) {

        //hiding the soft keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        //setting the text
        userLoginStatus.setText(user.data.getEmail() + " Logged In");

    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo() {
        requestManager.load(logo).into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button: {
                if (TextUtils.isEmpty(userName.getText().toString())) {
                    return;
                }
                authViewModel.authenticateUserWithId(Integer.parseInt(userName.getText().toString()));
                break;
            }
        }

    }

}
