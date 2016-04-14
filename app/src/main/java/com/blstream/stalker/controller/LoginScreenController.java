package com.blstream.stalker.controller;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.blstream.stalker.view.LoginScreenFragment;
import com.blstream.stalker.view.interfaces.IMainFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public class LoginScreenController {

    private static final int RC_SIGN_IN = 0;
    LoginScreenFragment fragment;
    private GoogleApiClient mGoogleApiClient;
    Context context;
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
        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) context)
                .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) context).addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
    }

    private void googlePlusLogin() {

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
                mConnectionResult.startResolutionForResult((Activity) context, RC_SIGN_IN);
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
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode == Activity.RESULT_OK) {
                signedInUser = false;
            }
            mIntentInProgress = false;
            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onConnected(Bundle arg0) {
// change Fragments
    }

    public void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    public void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


}
