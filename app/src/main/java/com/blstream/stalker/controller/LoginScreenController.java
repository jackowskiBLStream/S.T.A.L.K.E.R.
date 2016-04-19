package com.blstream.stalker.controller;

import android.content.IntentSender;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.blstream.stalker.Constants;
import com.blstream.stalker.controller.interfaces.ILoginScreenController;
import com.blstream.stalker.controller.internetConnection.InternetConnectionListener;
import com.blstream.stalker.view.fragments.LoginScreenView;
import com.blstream.stalker.view.interfaces.IBasicView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;


public class LoginScreenController extends FragmentController<LoginScreenView> implements ILoginScreenController {

    protected GoogleApiClient googleApiClient;


    public LoginScreenController(Fragment fragment, GoogleApiClient googleApiClient) {
        super(fragment);
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
        if (requestCode == Constants.RC_SIGN_IN && !googleApiClient.isConnecting()) {
            googleApiClient.connect();
        }
    }

    public void connectionFailedHandling(ConnectionResult result) {
        if (result != null && !result.hasResolution()) {
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int code = googleApiAvailability.isGooglePlayServicesAvailable(fragment.getContext());
            if (googleApiAvailability.isUserResolvableError(code)) {
                googleApiAvailability.getErrorDialog(fragment.getActivity(), code, Constants.RC_SIGN_IN).show();
            }
        } else {
            try {
                if(result != null) {
                    result.startResolutionForResult(fragment.getActivity(), Constants.RC_SIGN_IN);
                }
            } catch (IntentSender.SendIntentException e ) {
                Toast.makeText(fragment.getContext(), "SendIntent Exception. Try again.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void connectionSuccessHandling() {
        view.changeFragment(IBasicView.DETAIL_FRAGMENT);
    }
}

