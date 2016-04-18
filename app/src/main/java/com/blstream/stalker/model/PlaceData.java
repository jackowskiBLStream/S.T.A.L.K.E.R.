package com.blstream.stalker.model;

import android.support.annotation.NonNull;

import org.json.JSONException;
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
    private String place_id;

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public PlaceData(@NonNull String icon, @NonNull String types,
                     @NonNull String name, @NonNull Location location, String place_id) {
        this.icon = icon;
        this.types = types;
        this.name = name;
        this.location = location;
        this.place_id = place_id;
    }

    public PlaceData(@NonNull String icon, @NonNull String types,
                     @NonNull String name, @NonNull Location location) {
        this.icon = icon;
        this.types = types;
        this.name = name;
        this.location = location;
    }


    public static PlaceData parseJsonObjects(JSONObject jsonObject) {
        try {
            JSONObject geometry = (JSONObject) jsonObject.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            return new PlaceData(jsonObject.getString("icon"),
                    jsonObject.getString("types"),
                    jsonObject.getString("name"),
                    new Location((Double) location.get("lng"),
                            (Double) location.get("lng")),
                    jsonObject.getString("place_id"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;

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


}