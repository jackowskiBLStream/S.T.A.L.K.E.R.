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
    protected LoginScreenFragment loginScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationOfSaveInstanceState(savedInstanceState);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LoginScreenController.RC_SIGN_IN) {
            loginScreenFragment.sentLoginResultToFragment(requestCode, resultCode, RESULT_OK);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void initializationOfSaveInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            loginScreenFragment = new LoginScreenFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, loginScreenFragment).commit();
        } else {
            loginScreenFragment = (LoginScreenFragment) getSupportFragmentManager().getFragment(savedInstanceState, FRAGMENT_KEY);
            //FIXME: what to do with this fragment?? add to activity maybe?
        }
    }

}
