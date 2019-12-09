package com.app.repocommit.ui;

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
import android.widget.TextView;

import com.app.repocommit.R;
import com.app.repocommit.models.User;
import com.app.repocommit.viewmodel.ViewModelProviderFactory;
import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements View.OnClickListener {


    private static final String TAG = "MainActivity";

    private AuthViewModel authViewModel;
    private EditText userName;
    private Button userLogin;
    private TextView userLoginStatus;
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
        userLoginStatus = findViewById(R.id.txtUserLoggedIn);

        authViewModel.observerUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                Log.d(TAG, "onChanged: USER HERE -> "
                        + user.getUsername()
                        + "::"
                        + user.getEmail());

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                userLoginStatus.setText(user.getUsername() + " Logged In");

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
