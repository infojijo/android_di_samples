package com.app.repocommit.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import com.app.repocommit.BaseActivity;
import com.app.repocommit.R;

import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Logged Into Main Activity", Toast.LENGTH_SHORT).show();
    }
}
