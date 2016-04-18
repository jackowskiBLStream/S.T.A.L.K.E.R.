package com.blstream.stalker.model;

/**
 * Contains information about place location.
 */
public class PlaceLocation {
    private double longitude;
    private double latitude;

    public PlaceLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

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

    public double getDistance(PlaceLocation loc) {
        return Math.sqrt(((loc.getLatitude() - latitude) * (loc.getLatitude() - latitude)) +
                ((loc.getLongitude() - longitude) * (loc.getLongitude() - longitude)));
    }
}
