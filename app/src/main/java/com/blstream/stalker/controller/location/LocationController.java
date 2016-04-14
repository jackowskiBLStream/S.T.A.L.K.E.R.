package com.blstream.stalker.controller.location;

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
 * //TODO: Google Places
 */
public class LocationController implements ILocationController,
        OnConnectionFailedListener, ConnectionCallbacks, LocationListener {

    private static final String TAG = "LocationController: ";
    private Context context;
    private GoogleApiClient googleApiClient;
    private Location lastLocation;
    private LocationRequest locationRequest;
    private Location currentLocation;

    public LocationController(Context context) {
        this.context = context;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @return true - if GoogleApiClient is connected, false - if not
     */
    public boolean getGoogleApiState(){
        return googleApiClient.isConnected();
    }

    public void createLocationRequest() {
        //TODO: Interval depends on activity method(running, cycling etc)
        locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(500);//works when location has been found earlier
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
        startLocationUpdates();
        Log.d(TAG, "onConnected: ");


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: ");
        currentLocation = location;
    }

    public void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, locationRequest, this);
    }

    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                googleApiClient, this);
    }
}
