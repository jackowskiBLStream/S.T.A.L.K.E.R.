package com.blstream.stalker.controller;

import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.blstream.stalker.controller.interfaces.ILoginScreenController;
import com.blstream.stalker.controller.internetConnection.InternetConnectionListener;
import com.blstream.stalker.controller.internetConnection.InternetConnectionObserver;
import com.blstream.stalker.view.fragments.LoginScreenFragment;
import com.blstream.stalker.view.interfaces.ILoginFragment;
import com.blstream.stalker.view.interfaces.IMainFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import java.util.Observable;
import java.util.Observer;


public class LoginScreenController extends FragmentController<LoginScreenFragment> implements ILoginScreenController,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final int RC_SIGN_IN = 0;

    private GoogleApiClient googleApiClient;
    private boolean signedInUser;
    private boolean mIntentInProgress;
    private ConnectionResult mConnectionResult;

    public LoginScreenController(LoginScreenFragment fragment) {
        super(fragment);
        initializeGPApiClient();
    }

    /**
     * {@inheritDoc}
     */
    public void googlePlusLogin() {
        if (!googleApiClient.isConnecting()) {
            signedInUser = true;
            resolveSignInError();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void runWithoutLogin() {
        signedInUser = false;
        view.changeFragment(IMainFragment.LIST_FRAGMENT);
    }

    /**
     * {@inheritDoc}
     */
    public void sentLoginResultToController(int requestCode, int responseCode, final int RESULT_OK) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode == RESULT_OK) {
                signedInUser = false;
            }
            mIntentInProgress = false;
            if (!googleApiClient.isConnecting()) {
                googleApiClient.connect();
            }
        }
    }

    /**
     * Method initializes google plus api client
     */
    private void initializeGPApiClient() {
        googleApiClient = new GoogleApiClient.Builder(fragment.getActivity().getApplicationContext())
                //.enableAutoManage
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    /**
     * Method created for tests.
     * @return boolean which defines if the user is loggedIn
     */
    public boolean isLoggedIn() {
        return googleApiClient.isConnected();
    }

    /**
     * Method to resolve any signIn errors
     */
    private void resolveSignInError() {
        try {
            boolean resolution = mConnectionResult.hasResolution();
            if (resolution) {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(fragment.getActivity(), RC_SIGN_IN);
            }
        } catch (IntentSender.SendIntentException | NullPointerException e) {
            mIntentInProgress = false;
            googleApiClient.connect();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        if (!result.hasResolution()) {
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int code = googleApiAvailability.isGooglePlayServicesAvailable(fragment.getActivity());
            if (googleApiAvailability.isUserResolvableError(code)) {
                googleApiAvailability.getErrorDialog(fragment.getActivity(), code, RC_SIGN_IN).show();
            }
            return;
        }
        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;
            if (signedInUser) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }
    }

    /**
     * Method switches LoginScreenFragment to ListFragment
     *
     * @param arg0 {@inheritDoc}
     */
    @Override
    public void onConnected(Bundle arg0) {
        view.changeFragment(IMainFragment.LIST_FRAGMENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }
}
