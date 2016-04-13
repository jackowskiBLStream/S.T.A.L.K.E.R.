package com.blstream.stalker.model;

import android.graphics.Bitmap;
import android.location.Location;

import java.util.List;

/**
 * Stores single place informations
 */
public interface IPlaceData {
    /**
     *
     * @return place icon
     */
    Bitmap getIcon();

    /**
     *
     * @return place name
     */
    String getName();

    /**
     *
     * @return list of place types
     */
    List<String> getTypes();

    /**
     *
     * @param day day number 0 - monday ... 6 - sunday
     * @return hours when place is opened at day specified
     */
    IOpenHours getOpenHours(int day);

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
    double getDistanceFromLocation(Location location);

    /**
     *
     * @return details about place
     */
    IPlaceDataDetails getDetails();
}
