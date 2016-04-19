package com.blstream.stalker.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blstream.stalker.BaseActivity;
import com.blstream.stalker.R;
import com.blstream.stalker.controller.DatabaseController;
import com.blstream.stalker.controller.LoginScreenController;
import com.blstream.stalker.controller.PlaceListController;
import com.blstream.stalker.view.abstractClass.BasicView;
import com.blstream.stalker.view.interfaces.ILoginView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;


public class LoginScreenView extends BasicView implements ILoginView, GoogleApiClient.ConnectionCallbacks,  GoogleApiClient.OnConnectionFailedListener{
    SignInButton signInButton;
    Button noThanksButton;
    LoginScreenController loginScreenController;
    PlaceListController placeListController;
    DatabaseController db;
    GoogleApiClient googleApiClient;
    /**
     * {@inheritDoc}
     */


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        googleApiClient = ((BaseActivity)getActivity()).getGoogleApiClient();
        return inflater.inflate(R.layout.login_screen_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signInButton = (SignInButton) view.findViewById(R.id.sign_in_button);
        noThanksButton = (Button) view.findViewById(R.id.no_thanks_button);

        loginScreenController = new LoginScreenController(this, googleApiClient);
        placeListController = new PlaceListController(this);
        loginScreenController.setView(this);
        db = new DatabaseController(getContext());
        customizeButtons();
    }

    private void customizeButtons() {
        signInButton.setColorScheme(SignInButton.COLOR_AUTO);
        signInButton.setOnClickListener(new View.OnClickListener() {
            /**
             *{@inheritDoc}
             */
            @Override
            public void onClick(View v) {
                loginScreenController.googlePlusLogin();
            }
        });
        noThanksButton.setOnClickListener(new View.OnClickListener() {
            /**
             *{@inheritDoc}
             *
             */
            @Override
            public void onClick(View v) {
                hideError();
                loginScreenController.runWithoutLogin();

            }
        });
    }

    public void sendLoginResultToFragment(int requestCode, int responseCode, final int RESULT_OK) {
        loginScreenController.sendLoginResultToController(requestCode, responseCode, RESULT_OK);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        loginScreenController.connectionSuccessHandling();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        loginScreenController.connectionFailedHandling(connectionResult);
    }
}
