package com.blstream.stalker.model;

import android.support.annotation.NonNull;

/**
 * Used for storing PlaceData and PlaceDataDetails
 */
public class PlaceDataWithDetails {
    private PlaceData placeData;
    private PlaceDataDetails placeDataDetails;

    /**
     * Creates class instance with given parameters
     *
     * @params to be stored in class instance
     */
    public PlaceDataWithDetails(@NonNull PlaceData placeData, @NonNull PlaceDataDetails placeDataDetails) {
        this.placeData = placeData;
        this.placeDataDetails = placeDataDetails;
    }

    /**
     * @return PlaceData
     */
    public PlaceData getPlaceData() {
        return placeData;
    }

    /**
     * @return PlaceDataDetails
     */
    public PlaceDataDetails getPlaceDataDetails() {
        return placeDataDetails;
    }

}
