package com.blstream.stalker.model;

import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;

import java.util.List;

/**
 * Stores all data about particular place
 */
public class PlaceData implements IPlaceData {

    Bitmap icon;
    String name;
    List<String> types;
    Location location;
    IPlaceDataDetails details;

    /**
     * @return place icon
     */
    @Override
    public Bitmap getIcon() {
        return icon;
    }

    /**
     * @return place name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return list of place types
     */
    @Override
    public List<String> getTypes() {
        return types;
    }

    /**
     * @param day day number 0 - monday ... 6 - sunday
     * @return hours when place is opened at day specified
     */
    @Override
    public IOpenHours getOpenHours(int day) {
        return details.getOpenHours(day);
    }

    /**
     * @return place location
     */
    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * @param location location to which distance will be calculated
     * @return distance to location specified in param
     */
    @Override
    public double getDistanceFromLocation(Location location) {
        //TODO implement this!
        return 0;
    }

    /**
     * @return details about place
     */
    @Override
    public IPlaceDataDetails getDetails() {
        return details;
    }
}
