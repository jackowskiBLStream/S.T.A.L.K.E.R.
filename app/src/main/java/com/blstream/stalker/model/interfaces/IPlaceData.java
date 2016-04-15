package com.blstream.stalker.model.interfaces;

import android.graphics.Bitmap;
import android.location.Location;

import java.util.List;

/**
 * Stores single place information
 */
public interface IPlaceData {
    /**
     *
     * @return place icon
     */

    String getIconUrl();

    /**
     *
     * @return place name
     */
    String getName();

    /**
     *
     * @return list of place types
     */
    String getTypes();

    /**
     *
     * @return hours when place is opened at day specified
     */
    IOpenHours getTodayOpenHours();

    /**
     *
     * @return place location
     */
    Location getLocation();

    /**
     *
     * @param location location to which distance will be calculated
     * @return distance to location specified in param
     */
    float getDistanceFromLocation(Location location);

}
