package com.blstream.stalker.controller.location;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.blstream.stalker.controller.places.GooglePlacesController;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.view.fragments.PlaceListFragment;
import com.blstream.stalker.view.interfaces.IPlaceListFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;

import java.util.List;

import static com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

/**
 *
 */
public class LocationController implements ILocationController, OnConnectionFailedListener,
        ConnectionCallbacks, LocationListener {

    private static final String TAG = "LocationController: ";
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private GooglePlacesController googlePlacesController = new GooglePlacesController();
    private PlaceListFragment fragment;

    public LocationController(PlaceListFragment fragment) {
        this.fragment = fragment;
        createGoogleApiClientInstance();
        createLocationRequest();
    }

    /**
     * @return true -  GoogleApiClient connected, false - if not
     */
    public boolean getGoogleApiState() {
        return googleApiClient.isConnected();
    }

    public void createLocationRequest() {
        //TODO: Interval depends on activity method(running, cycling etc) somewhere else it should be
        locationRequest = new LocationRequest();
        locationRequest.setInterval(20000);
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
            googleApiClient = new GoogleApiClient.Builder(fragment.getContext())
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
        new GetPlaces().execute(location.getLatitude(), location.getLongitude());
    }

    public void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, locationRequest, this);
    }

    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                googleApiClient, this);
    }

    private class GetPlaces extends AsyncTask<Object, Object, List<PlaceData>> {


        @Override
        protected List<PlaceData> doInBackground(Object... params) {
            return googlePlacesController.findPlaces((Double) params[0],
                    (Double) params[1], "");
        }

        @Override
        protected void onPostExecute(List<PlaceData> placeDataList) {
            super.onPostExecute(placeDataList);
            fragment.uploadList(placeDataList);
        }
    }
}
