package com.blstream.stalker.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Used for storing Details about each place
 */
public class PlaceDataDetails {
    private OpenHours[] openHours;
    private double rating;

    private ArrayList<Review> reviews;

    /**
     * Constructor that sets Details to ones given as parameters
     *
     * @param openHours 7 element table with open hours in all week
     * @param rating    Place rating
     * @param reviews   List of 3 first place reviews
     */
    public PlaceDataDetails(OpenHours[] openHours, double rating, ArrayList<Review> reviews) {
        this.openHours = openHours;
        this.rating = rating;
        this.reviews = reviews;
    }

    public static PlaceDataDetails parseJsonObject(JSONObject jsonObject) throws JSONException {
        OpenHours[] openHours = new OpenHours[7];
        OpenHours.fillWithNoInfo(openHours);
        //TODO: if not found(e.x. rating, do sth but NOT NULL
        JSONObject openingHours = (JSONObject) jsonObject.get("opening_hours");
        JSONArray periods = openingHours.getJSONArray("periods");
        for (int i = 0; i < periods.length(); i++) {
            JSONObject close = (JSONObject) ((JSONObject) periods.get(i)).get("close");
            openHours[i].setTimeClosed(String.valueOf(close.get("time")));
            JSONObject open = (JSONObject) ((JSONObject) periods.get(i)).get("open");
            openHours[i].setTimeOpened(String.valueOf(open.get("time")));

        }
        return new PlaceDataDetails(openHours,
                jsonObject.getDouble("rating"),
                new ArrayList<Review>());
    }

    /**
     * @return open hours for all days in week
     */
    public OpenHours[] getAllOpenHours() {
        return openHours;
    }

    /**
     * @param day day number 0 - monday ... 6 - sunday
     * @return hours when place is opened at day specified
     */
    public OpenHours getOpeningHours(int day) {
        return openHours[day];
    }

    /**
     * @return place rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @return three last place reviews
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
