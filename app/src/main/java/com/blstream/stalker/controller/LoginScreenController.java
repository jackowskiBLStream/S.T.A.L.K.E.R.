package com.blstream.stalker.controller;


import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.blstream.stalker.view.fragments.LoginScreenFragment;
import com.blstream.stalker.view.interfaces.IMainFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public class LoginScreenController implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final int RC_SIGN_IN = 0;
    LoginScreenFragment fragment;
    Context context;
    private GoogleApiClient mGoogleApiClient;
    private boolean signedInUser;
    private boolean mIntentInProgress;
    private ConnectionResult mConnectionResult;

    public LoginScreenController(Fragment fragment) {
        this.fragment = (LoginScreenFragment) fragment;
        context = fragment.getActivity();
        initalizeGPApiClient();
    }

    private void initalizeGPApiClient() {
        // Initializing google plus api client
        mGoogleApiClient = new GoogleApiClient.Builder(context.getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }

    public void googlePlusLogin() {
        if (!mGoogleApiClient.isConnecting()) {
            signedInUser = true;
            resolveSignInError();
        }
    }

    /**
     * Method to resolve any signin errors
     */
    private void resolveSignInError() {
        try {
            boolean resolution = mConnectionResult.hasResolution();
            if (resolution) {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(fragment.getActivity(), RC_SIGN_IN);
                Toast.makeText(context, "startResolutionForResult", Toast.LENGTH_SHORT).show();

            }
        } catch (IntentSender.SendIntentException | NullPointerException e) {
            mIntentInProgress = false;
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), (Activity) context,
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;

            if (signedInUser) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }
    }

    @Override
    public void onConnected(Bundle arg0) {
        Toast.makeText(context, "OnConnected", Toast.LENGTH_SHORT).show();
        fragment.changeFragment(IMainFragment.LIST_FRAGMENT);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(context, "OnConnectedSuspended", Toast.LENGTH_SHORT).show();
        mGoogleApiClient.connect();
    }

    public void sentLoginResultToController(int requestCode, int responseCode, final int RESULT_OK) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode == RESULT_OK) {
                signedInUser = false;
            }
            mIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }
}
