package com.blstream.stalker.model;

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
     * @param openHours 7 element table with open hours in all week
     * @param rating Place rating
     * @param reviews   List of 3 first place reviews
     */
    public PlaceDataDetails(OpenHours[] openHours, double rating, ArrayList<Review> reviews) {
        this.openHours = openHours;
        this.rating = rating;
        this.reviews = reviews;
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
