package com.blstream.stalker.controller.location;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.blstream.stalker.R;
import com.blstream.stalker.model.PlaceData;

import java.util.List;

public class TempActivity extends AppCompatActivity implements IPlacesChangeListener {

    private static final String TAG = "TempActivity: ";
    private LocationController locationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        locationController = new LocationController(this);
        locationController.setPlacesChangeListener(this);
        locationController.createGoogleApiClientInstance();
        locationController.createLocationRequest();
    }

    @Override
    protected void onStart() {
        locationController.connectGoogleApiClient();
        super.onStart();
    }

    @Override
    protected void onStop() {
        locationController.disconnectGoogleApiClient();
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(locationController.getGoogleApiState()){
            locationController.stopLocationUpdates();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(locationController.getGoogleApiState()){
            locationController.startLocationUpdates();
        }
    }

    @Override
    public void onPlacesChangeListener(List<PlaceData> placeDataList) {
        Log.d(TAG, "onPlacesChangeListener: " + placeDataList.get(0).getName());
    }
}
