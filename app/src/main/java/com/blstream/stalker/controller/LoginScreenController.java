package com.blstream.stalker.controller;

import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.blstream.stalker.Constants;
import com.blstream.stalker.controller.interfaces.ILoginScreenController;
import com.blstream.stalker.view.fragments.LoginScreenView;
import com.blstream.stalker.view.interfaces.IBasicView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;


public class LoginScreenController extends FragmentController<LoginScreenView> implements ILoginScreenController{

    protected GoogleApiClient googleApiClient;


    public LoginScreenController(Fragment fragment, GoogleApiClient googleApiClient) {
        super(fragment);
//        initializeGPApiClient();
        this.googleApiClient = googleApiClient;
    }


    /**
     * {@inheritDoc}
     */

    public void googlePlusLogin() {
        if (!googleApiClient.isConnecting()) {
            googleApiClient.connect();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void runWithoutLogin() {

        view.changeFragment(IBasicView.LIST_FRAGMENT);

    }

    /**
     * {@inheritDoc}
     */
    public void sendLoginResultToController(int requestCode, int responseCode, final int RESULT_OK) {
        if (requestCode == Constants.RC_SIGN_IN && !googleApiClient.isConnecting() && responseCode == RESULT_OK) {
            googleApiClient.connect();
            return;
        }
    }

//    /**
//     * Method initializes google plus api client
//     */
//    private void initializeGPApiClient() {
//        googleApiClient = new GoogleApiClient.Builder(fragment.getContext())
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this).addApi(Plus.API)
//                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
//    }

    /**
     * Method created for tests.
     *
     * @return boolean which defines if the user is loggedIn
     */
    public boolean isLoggedIn() {
        return googleApiClient.isConnected();
    }

    public void connectionFailedHandling(ConnectionResult result){
        if (result != null && !result.hasResolution()) {
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int code = googleApiAvailability.isGooglePlayServicesAvailable(fragment.getContext());
            if (googleApiAvailability.isUserResolvableError(code)) {
                googleApiAvailability.getErrorDialog(fragment.getActivity(), code, Constants.RC_SIGN_IN).show();
                return;
            }
        } else {
            try {
                result.startResolutionForResult(fragment.getActivity(), Constants.RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                Toast.makeText(fragment.getContext(), "SendIntent Exception. Try again.", Toast.LENGTH_SHORT);
            }
        }
    }

    public void connectionSuccessHandling(){
        view.changeFragment(IBasicView.DETAIL_FRAGMENT);
    }

    /**
     * {@inheritDoc}
     */
/*    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        if (result != null && !result.hasResolution()) {
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int code = googleApiAvailability.isGooglePlayServicesAvailable(fragment.getContext());
            if (googleApiAvailability.isUserResolvableError(code)) {
                googleApiAvailability.getErrorDialog(fragment.getActivity(), code, Constants.RC_SIGN_IN).show();
                return;
            }
        } else {
            try {
                result.startResolutionForResult(fragment.getActivity(), Constants.RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                Toast.makeText(fragment.getContext(), "SendIntent Exception. Try again.", Toast.LENGTH_SHORT);
            }
        }
    }

    *//**
     * Method switches LoginScreenView to ListFragment
     *
     * @param arg0 {@inheritDoc}
     *//*
    @Override
    public void onConnected(Bundle arg0) {
//        view.changeFragment(IBasicView.LIST_FRAGMENT);
        view.changeFragment(IBasicView.DETAIL_FRAGMENT);
    }

    *//**
     * {@inheritDoc}
     *//*
    @Override
    public void onConnectionSuspended(int i) {
    }*/
}

