package com.blstream.stalker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.blstream.stalker.controller.LoginScreenController;
import com.blstream.stalker.view.fragments.LoginScreenFragment;
import com.blstream.stalker.view.fragments.PlaceListFragment;


public abstract class BaseActivity extends AppCompatActivity {
    LoginScreenFragment loginScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginScreenFragment = new LoginScreenFragment();
//        PlaceListFragment listFragment = new PlaceListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.mainContainer, loginScreenFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LoginScreenController.RC_SIGN_IN) {
            loginScreenFragment.sentLoginResultToFragment(requestCode, resultCode, RESULT_OK);
        } else {
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
