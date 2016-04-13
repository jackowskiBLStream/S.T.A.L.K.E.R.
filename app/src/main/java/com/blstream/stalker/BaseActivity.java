package com.blstream.stalker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blstream.stalker.view.LoginScreenFragment;

/**
 *
 */
    public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoginScreenFragment loginScreenFragment = new LoginScreenFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.mainContainer,loginScreenFragment).commit();
    }
}
