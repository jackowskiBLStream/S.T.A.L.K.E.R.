package com.blstream.stalker.model;

/**
 * Contains information about place location.
 */
public class Location {
    private double longitude;
    private double latitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance(Location loc) {
        return Math.sqrt(((loc.getLatitude() - latitude) * (loc.getLatitude() - latitude)) +
                ((loc.getLongitude() - longitude) * (loc.getLongitude() - longitude)));
    }
}
