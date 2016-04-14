package com.blstream.stalker.model;

import android.location.Location;

import com.blstream.stalker.model.interfaces.IOpenHours;
import com.blstream.stalker.model.interfaces.IPlaceData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Stores all data about particular place
 */
public class PlaceData implements IPlaceData {

    private String icon;
    private String name;
    private String types;
    private Location location;
    private double longitude;
    private double latitude;
    private IOpenHours todayOpenHours;


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypes(String types) {
        this.types = types;
    }


    public void setLocation(Location location) {
        this.location = location;
    }

    public void setTodayOpenHours(IOpenHours todayOpenHours) {
        this.todayOpenHours = todayOpenHours;
    }

    /**
     * @return place icon
     */
    @Override
    public String getIcon() {
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

    public static PlaceData jsonToPontoReferencia(JSONObject pontoReferencia) {
        try {
            PlaceData placeData = new PlaceData();
            JSONObject geometry = (JSONObject) pontoReferencia.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            placeData.setLatitude((Double) location.get("lat"));
            placeData.setLongitude((Double) location.get("lng"));
            placeData.setIcon(pontoReferencia.getString("icon"));
            placeData.setName(pontoReferencia.getString("name"));
            placeData.setTypes(pontoReferencia.getString("types"));
            return placeData;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
