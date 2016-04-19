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
    private PlaceLocation placeLocation;
    private OpenHours todayOpenHours;
    private int id;


    public PlaceData(@NonNull String icon, @NonNull String types,
                     @NonNull String name, @NonNull PlaceLocation placeLocation) {
        this.icon = icon;
        this.types = types;
        this.name = name;
        this.placeLocation = placeLocation;
    }

    protected PlaceData(Parcel in) {
        name = in.readString();
        icon = in.readString();
        types = in.readString();
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
//TODO: WHA THE FUCK IS PONTON?
    public static PlaceData jsonToPontoReferencia(JSONObject pontoReferencia) {
       /* try {
            PlaceData placeData = new PlaceData();
            JSONObject geometry = (JSONObject) pontoReferencia.get("geometry");
            JSONObject location = (JSONObject) geometry.get("location");
            placeData.setPlaceID(pontoReferencia.getString("place_id"));
            placeData.setLatitude((Double) location.get("lat"));
            placeData.setLongitude((Double) location.get("lng"));
            placeData.setIcon(pontoReferencia.getString("icon"));
            placeData.setName(pontoReferencia.getString("name"));
            placeData.setTypes(pontoReferencia.getString("types"));
            return placeData;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }*/
        return null;
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

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(icon);
        dest.writeString(types);
        dest.writeInt(id);
    }
}