package com.blstream.stalker.controller;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;

import static com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

/**
 * //TODO: http://developer.android.com/training/location/receive-location-updates.html
 */
public class LocationController implements ILocationController,
        OnConnectionFailedListener, ConnectionCallbacks, LocationListener {

    private static final String TAG = "LocationController: ";
    private Context context;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private LocationRequest locationRequest;

    public LocationController(Context context) {
        this.context = context;
        createLocationRequest();


    }

    public void createLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(5000); //Interval depends on activity method(running, cycling etc)
        locationRequest.setFastestInterval(500);//TODO: Read more 'bout it
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
    }

    @Override
    public void connectGoogleApiClient() {
        googleApiClient.connect();
    }

    @Override
    public void disconnectGoogleApiClient() {
        googleApiClient.disconnect();
    }

    @Override
    public void createGoogleApiClientInstance() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        //TODO: Check if sth is not null)
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, locationRequest, this);


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: Running");
    }
}
