package com.app.repocommit.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.repocommit.BaseActivity;
import com.app.repocommit.R;
import com.app.repocommit.ui.main.post.PostFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_profile: {

            }
            break;
            case R.id.nav_posts: {

            }
            break;
        }
        menuItem.setChecked(true); //to highlight the selected one
        drawerLayout.closeDrawer(GravityCompat.START); // to close the drawer to the START of the Screen
        return true;// click is consumed.
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        Toast.makeText(this, "Logged Into Main Activity", Toast.LENGTH_SHORT).show();
        TestFragment();
    }

    //for initiating the Profile Fragment.
    private void TestFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new PostFragment())
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout: {
                sessionManager.logout();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
