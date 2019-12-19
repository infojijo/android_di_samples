package com.app.repocommit.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.repocommit.BaseActivity;
import com.app.repocommit.R;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity
        extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        init();
    }

    private void init() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_profile: {
                //extra navigation option -
                //here its used for clearing the backstack when you navigate to profile
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.main, true)
                        .build();
                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.profileScreen,
                                null,
                                navOptions);
                break;
            }
            case R.id.nav_posts: {
                if (isValidDestination(R.id.postsScreen)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.postsScreen);
                }
                break;
            }
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logout: {
                sessionManager.logout();
                return true;
            }
            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true; // to consume the click
                } else {
                    return false; // not to consume click
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * @return to enable the support of drawer layout in navigationUI.
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation
                .findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }

    /**
     * @param destination -id of the fragment
     * @return if the destination of the click is already the origin.
     */
    private boolean isValidDestination(int destination) {
        return destination !=
                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .getCurrentDestination().getId();
    }
}

