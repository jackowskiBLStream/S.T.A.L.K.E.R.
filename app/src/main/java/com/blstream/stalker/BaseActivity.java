package com.blstream.stalker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blstream.stalker.view.fragments.LoginScreenView;
import com.blstream.stalker.view.fragments.PlaceListView;


public abstract class BaseActivity extends AppCompatActivity {
    private static final String FRAGMENT_KEY = "LoginScreenView";
    LoginScreenView loginScreenView;
    private PlaceListView listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationOfSaveInstanceState(savedInstanceState);
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        loginScreenView.sendLoginResultToFragment(requestCode, resultCode, RESULT_OK);
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initializationOfSaveInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loginScreenView = new LoginScreenView();
            getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, loginScreenView).commit();
        } else {
            loginScreenView = (LoginScreenView) getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_KEY);
        }
    }

}
