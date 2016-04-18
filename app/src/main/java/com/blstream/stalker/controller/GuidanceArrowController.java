package com.blstream.stalker.controller;

import android.util.Log;

import com.blstream.stalker.controller.location.LocationChangeListener;
import com.blstream.stalker.model.PlaceLocation;

/**
 * Created by Patryk Gwiazdowski on 18.04.2016.
 * // Good Job Patryk
 */
public class GuidanceArrowController implements LocationChangeListener {
    private static final String TAG = GuidanceArrowController.class.getSimpleName();
    PlaceLocation placeLocation;

    public GuidanceArrowController(PlaceLocation placeLocation) {
        this.placeLocation = placeLocation;
    }

    @Override
    public void onLocationChanged(PlaceLocation location) {
        Log.d(TAG, "onLocationChanged: distance to point: " + placeLocation.getDistance(location));
    }
}
