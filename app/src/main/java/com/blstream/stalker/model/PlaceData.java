package com.blstream.stalker.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Stores all data about particular place
 */
public class PlaceData implements Parcelable {


    private String name;
    private String icon;
    private String types;
    private String place_id;
    private PlaceLocation placeLocation;
    private OpenHours todayOpenHours;
    private int id;

    public PlaceData(@NonNull String icon, @NonNull String types,
                     @NonNull String name, @NonNull PlaceLocation placeLocation, String place_id) {
        this.icon = icon;
        this.types = types;
        this.name = name;
        this.placeLocation = placeLocation;
        this.place_id = place_id;
    }


    protected PlaceData(Parcel in) {
        name = in.readString();
        icon = in.readString();
        types = in.readString();
        place_id = in.readString();
        id = in.readInt();
    }

    public static final Creator<PlaceData> CREATOR = new Creator<PlaceData>() {
        @Override
        public PlaceData createFromParcel(Parcel in) {
            return new PlaceData(in);
        }

        @Override
        public PlaceData[] newArray(int size) {
            return new PlaceData[size];
        }
    };

    public static PlaceData parseJsonObjects(JSONObject jsonObject) {
        try {
            JSONObject geometry = (JSONObject) jsonObject.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            return new PlaceData(jsonObject.getString("icon"),
                    jsonObject.getString("types"),
                    jsonObject.getString("name"),
                    new PlaceLocation((Double) location.get("lng"),
                            (Double) location.get("lng")),
                    jsonObject.getString("place_id"));
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;

    }


    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
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
     * @return place placeLocation
     */
    public PlaceLocation getPlaceLocation() {
        return placeLocation;
    }


    /**
     * @param placeLocation placeLocation to which distance will be calculated
     * @return distance to placeLocation specified in param
     */
    public double getDistanceFromLocation(PlaceLocation placeLocation) {
        return this.placeLocation.getDistance(placeLocation);
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
                ", placeLocation=" + placeLocation +
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeString(types);
        dest.writeString(place_id);
        dest.writeInt(id);
    }
}