package com.blstream.stalker.model;

import android.support.annotation.NonNull;

import org.json.JSONObject;

/**
 * Stores all data about particular place
 */
public class PlaceData {

    private String name;
    private String icon;
    private String types;
    private Location location;
    private OpenHours todayOpenHours;
    private int id;


    public PlaceData(@NonNull String icon, @NonNull String types,
                     @NonNull String name, @NonNull Location location) {
        this.icon = icon;
        this.types = types;
        this.name = name;
        this.location = location;
    }

    /**
     * @return place icon
     */
    public String getIconUrl() {
        return icon;
    }

    /**
     * @return place name
     */
    public String getName() {
        return name;
    }

    /**
     * @return list of place types
     */
    public String getTypes() {
        return types;
    }

    /**
     * @return hours when place is opened at day specified
     */
    public OpenHours getTodayOpenHours() {
        return todayOpenHours;
    }

    /**
     * @return place location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location location to which distance will be calculated
     * @return distance to location specified in param
     */
    public double getDistanceFromLocation(Location location) {
        return this.location.getDistance(location);
    }

    /**
     * Overrided toString() method
     *
     * @return String that contains some basic information about Place
     */
    @Override
    public String toString() {
        return "PlaceData{" +
                ", name='" + name + '\'' +
                ", types='" + types + '\'' +
                ", location=" + location +
                ", todayOpenHours=" + todayOpenHours +
                ", id=" + id +
                '}';
    }

    /**
     * @return Place Id in database
     */
    public int getId() {
        return id;
    }

    /**
     * @param id Sets Place id to
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets Today Opening hours to given in parameter. Used in Database Controller to retrieve and set
     * hours for current day of week
     *
     * @param hours to be set as today open hours
     */
    public void setTodayOpeningHours(OpenHours hours) {
        this.todayOpenHours = hours;
    }


    public static PlaceData jsonToPontoReferencia(JSONObject jsonObject) {
        return null;
    }
}