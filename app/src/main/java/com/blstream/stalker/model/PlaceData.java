package com.blstream.stalker.model;

import android.graphics.Bitmap;
import android.location.Location;

import com.blstream.stalker.model.interfaces.IOpenHours;
import com.blstream.stalker.model.interfaces.IPlaceData;

/**
 * Stores all data about particular place
 */
public class PlaceData implements IPlaceData {

    private Bitmap icon;
    private String name;
    private String types;
    private Location location;
    private IOpenHours todayOpenHours;

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
    public String getTypes() {
        return types;
    }

    /**
     * @return hours when place is opened at day specified
     */
    @Override
    public IOpenHours getTodayOpenHours() {
        return todayOpenHours;
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
    public float getDistanceFromLocation(Location location) {
        return this.location.distanceTo(location);
    }


}
