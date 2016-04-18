package com.blstream.stalker.controller.location;

import com.blstream.stalker.model.PlaceLocation;

/**
 * Listener for arrow control
 */
public interface LocationChangeListener {
    void onLocationChanged(PlaceLocation location);
}
