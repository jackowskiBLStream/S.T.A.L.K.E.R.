package com.blstream.stalker.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blstream.stalker.R;

public class TempActivity extends AppCompatActivity {

    LocationController locationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        locationController = new LocationController(this);
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
}
