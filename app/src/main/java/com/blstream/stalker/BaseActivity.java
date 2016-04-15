package com.blstream.stalker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.blstream.stalker.controller.LoginScreenController;
import com.blstream.stalker.view.fragments.LoginScreenFragment;
import com.blstream.stalker.view.fragments.PlaceListFragment;


public abstract class BaseActivity extends AppCompatActivity {
    private static final String FRAGMENT_KEY = "ListFragment";
    LoginScreenFragment loginScreenFragment;
    private PlaceListFragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginScreenFragment = new LoginScreenFragment();
        initializationOfSaveInstanceState(savedInstanceState);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LoginScreenController.RC_SIGN_IN) {
            loginScreenFragment.sentLoginResultToFragment(requestCode, resultCode, RESULT_OK);
        } else {
        }

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
        getSupportFragmentManager().putFragment(outState, FRAGMENT_KEY, listFragment);
    }
    private void initializationOfSaveInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            listFragment = new PlaceListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, listFragment).commit();
        } else {
            listFragment = (PlaceListFragment) getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_KEY);
        }
    }

}
