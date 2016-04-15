package com.blstream.stalker.model;

/**
 * Used for storing PlaceData and PlaceDataDetails
 */
public class PlaceDataWithDetails {
    private PlaceData placeData;
    private PlaceDataDetails placeDataDetails;

    /**
     * Creates class instance with given parameters
     * @params  to be stored in class instance
     */
    public PlaceDataWithDetails(PlaceData placeData, PlaceDataDetails placeDataDetails) {
        this.placeData = placeData;
        this.placeDataDetails = placeDataDetails;
    }

    /**
     *
     * @return PlaceData
     */
    public PlaceData getPlaceData() {
        return placeData;
    }

    /**
     *
     * @return PlaceDataDetails
     */
    public PlaceDataDetails getPlaceDataDetails() {
        return placeDataDetails;
    }

}
