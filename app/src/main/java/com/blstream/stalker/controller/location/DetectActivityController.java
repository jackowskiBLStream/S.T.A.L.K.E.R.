package com.blstream.stalker.controller.location;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.blstream.stalker.view.fragments.PlaceListFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;

/**
 *
 */
public class DetectActivityController implements IOperationsController, GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, ResultCallback<Status> {

    private static final String TAG = "DetectController: ";
    private PlaceListFragment fragment;
    private ActivityDetectionBroadcastReceiver activityDetectionBroadcastReceiver;
    private GoogleApiClient googleApiClientActivityDetector;

    public DetectActivityController(PlaceListFragment fragment) {
        this.fragment = fragment;
        activityDetectionBroadcastReceiver = new ActivityDetectionBroadcastReceiver();
        createGoogleApiClientInstance();
    }

    private PendingIntent getActivityDetectionPendingIntent() {
        Intent intent = new Intent(fragment.getContext(), DetectActivitiesIntentService.class);
        return PendingIntent.getService(fragment.getContext(), 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void unregisterReceiver() {
        LocalBroadcastManager.getInstance(fragment.getContext())
                .unregisterReceiver(activityDetectionBroadcastReceiver);
    }

    private void registerReceiver() {
        LocalBroadcastManager.getInstance(fragment.getContext())
                .registerReceiver(activityDetectionBroadcastReceiver,
                        new IntentFilter(Constants.STRING_ACTION));
    }

    @Override
    public void connectGoogleApiClient() {
        googleApiClientActivityDetector.connect();
    }

    @Override
    public void disconnectGoogleApiClient() {
        if (googleApiClientActivityDetector.isConnected()) {
            googleApiClientActivityDetector.disconnect();
        }
    }

    @Override
    public void createGoogleApiClientInstance() {
        if (googleApiClientActivityDetector == null) {
            googleApiClientActivityDetector = new GoogleApiClient.Builder(fragment.getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(ActivityRecognition.API)
                    .build();
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStop() {
        disconnectGoogleApiClient();
    }

    @Override
    public void onStart() {
        connectGoogleApiClient();
    }

    @Override
    public void onPause() {
        unregisterReceiver();
    }

    @Override
    public void onResume() {
        registerReceiver();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnected");
        ActivityRecognition.ActivityRecognitionApi
                .requestActivityUpdates(googleApiClientActivityDetector, 0,
                        getActivityDetectionPendingIntent()).setResultCallback(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection suspended");
        googleApiClientActivityDetector.connect();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onResult(@NonNull Status status) {
        if (status.isSuccess()) {
            Log.d(TAG, "Successfully added activity detection.");
        } else {
            Log.d(TAG, "Error: " + status.getStatusMessage());
        }
    }
}
