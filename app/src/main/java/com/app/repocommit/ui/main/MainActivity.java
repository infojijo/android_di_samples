package com.app.repocommit.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.repocommit.BaseActivity;
import com.app.repocommit.R;
import com.app.repocommit.ui.main.post.PostFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
