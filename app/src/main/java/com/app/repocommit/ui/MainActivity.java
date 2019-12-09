package com.app.repocommit.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
        userLogin.setOnClickListener(this);

        authViewModel.observerUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                Log.d(TAG, "onChanged: USER HERE -> "
                        + user.getUsername()
                        + "::"
                        + user.getEmail());
            }
        });
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
