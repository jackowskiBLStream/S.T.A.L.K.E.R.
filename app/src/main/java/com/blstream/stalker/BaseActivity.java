package com.blstream.stalker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.blstream.stalker.controller.LoginScreenController;
import com.blstream.stalker.controller.interfaces.ILoginScreenController;
import com.blstream.stalker.view.fragments.LoginScreenView;
import com.blstream.stalker.view.fragments.PlaceListView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;



public abstract class BaseActivity extends AppCompatActivity {
    private static final String FRAGMENT_KEY = "LoginScreenView";
    LoginScreenView loginScreenView;
    private PlaceListView listFragment;
    protected GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationOfSaveInstanceState(savedInstanceState);
        initializeGPApiClient();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.RC_SIGN_IN) {
            loginScreenView.sendLoginResultToFragment(requestCode, resultCode, RESULT_OK);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * Method initializes google plus api client
     */
    private void initializeGPApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(loginScreenView)
                .addOnConnectionFailedListener(loginScreenView).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
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

    public GoogleApiClient getGoogleApiClient(){
        return googleApiClient;
    }
}
