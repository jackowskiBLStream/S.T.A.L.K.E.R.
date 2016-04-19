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
import com.blstream.stalker.model.PlaceDataWithDetails;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

/**
 *
 */
public class LocationController extends LocationFragmentController implements IOperationsController,
        OnConnectionFailedListener,
        ConnectionCallbacks, LocationListener {

    private static final String TAG = "LocationController: ";
    private GoogleApiClient googleApiClientLocation;
    private LocationRequest locationRequest;
    private GooglePlacesController googlePlacesController = new GooglePlacesController();
    private List<PlaceDataWithDetails> placeDataWithDetails;

    public LocationController(Fragment fragment) {
        super(fragment);
        createGoogleApiClientInstance();
        createLocationRequest();
        placeDataWithDetails = new ArrayList<>();
    }

    /**
     * @return true -  GoogleApiClient connected, false - if not
     */
    public boolean getGoogleApiState() {
        return googleApiClientLocation.isConnected();
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
        googleApiClientLocation.connect();
    }

    @Override
    public void disconnectGoogleApiClient() {
        if (googleApiClientLocation.isConnected()) {
            googleApiClientLocation.disconnect();
        }

    }

    @Override
    public void createGoogleApiClientInstance() {
        if (googleApiClientLocation == null) {
            googleApiClientLocation = new GoogleApiClient.Builder(fragment.getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

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

        if (getGoogleApiState()) {
            stopLocationUpdates();
        }
    }

    @Override
    public void onResume() {
        if (getGoogleApiState()) {
            startLocationUpdates();
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
        Log.d(TAG, "Connection Suspended");
        googleApiClientLocation.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: ");
        new GetPlaces().execute(location.getLatitude(), location.getLongitude());
    }

    public void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClientLocation, locationRequest, this);
    }

    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                googleApiClientLocation, this);
    }


    private class GetPlaces extends AsyncTask<Object, Object, List<PlaceData>> {

        @Override
        protected List<PlaceData> doInBackground(Object... params) {
            /*placeDataWithDetails = new ArrayList<>(googlePlacesController
                    .findPlacesWithDetails((Double) params[0],
                            (Double) params[1],
                            ""));*/
            return googlePlacesController.findPlaces((Double) params[0],
                    (Double) params[1],
                    "");
        }

        @Override
        protected void onPostExecute(List<PlaceData> placeDataList) {
            super.onPostExecute(placeDataList);
            view.uploadList(placeDataList);
        }
    }
}
