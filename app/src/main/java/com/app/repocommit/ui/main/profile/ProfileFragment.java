package com.app.repocommit.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.repocommit.R;
import com.app.repocommit.models.User;
import com.app.repocommit.ui.auth.AuthResource;
import com.app.repocommit.viewmodel.ViewModelProviderFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerFragment;

/**
 * Profile Fragment is used to displaying profile informations about the user.
 */
public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";

    private ProfileViewModel profileViewModel;
    private TextView username;
    private TextView userEmail;
    private TextView userWebsite;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onViewCreated: Profile Fragment created");
        profileViewModel = ViewModelProviders.of(this, providerFactory)
                .get(ProfileViewModel.class);
        subscribeObservers();

        username = view.findViewById(R.id.username);
        userEmail = view.findViewById(R.id.email);
        userWebsite = view.findViewById(R.id.website);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Profile Fragment", Toast.LENGTH_SHORT).show();

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private void subscribeObservers() {
        //fragment life cycles could be replaced
        //so it's better to clear the lifecycle of a fragments
        profileViewModel.getAuthResource().removeObservers(getViewLifecycleOwner());
        //Observing for Session Manager values in order to get user info - through out application life cycle.
        profileViewModel.getAuthResource().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {

                        case AUTHENTICATED: {
                            updateUserDetails(userAuthResource.data);
                            break;
                        }
                        case ERROR: {
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        username.setText(message);
        userEmail.setText("Error");
        userWebsite.setText("Error");
    }

    private void updateUserDetails(User data) {
        username.setText(data.getUsername());
        userEmail.setText(data.getEmail());
        userWebsite.setText(data.getWebsite());
    }
}
