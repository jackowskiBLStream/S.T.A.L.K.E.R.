package com.blstream.stalker;

import android.support.annotation.NonNull;

import com.blstream.stalker.model.OpenHours;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataDetails;
import com.blstream.stalker.model.PlaceDataWithDetails;
import com.blstream.stalker.model.PlaceLocation;
import com.blstream.stalker.model.Review;

import java.util.ArrayList;


/**
 * class uses to generate Data for placeData and placeDataDetails.
 */
public abstract class Data {
    private static final int DAYS_IN_WEEK = 7;
    /**
     * Returns array of PlaceDataWithDetails of given size, ready for adding to Database
     *
     * @param size of data to return
     * @return list of data
     */
    public ArrayList<PlaceDataWithDetails> getData(int size, int reviewsCount) {
        ArrayList<PlaceDataWithDetails> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            PlaceData placeData = getPlaceData(i);
            PlaceDataDetails placeDataDetails = getPlaceDataDetails(reviewsCount);
            PlaceDataWithDetails placeDataWithDetails = new PlaceDataWithDetails(
                    placeData,
                    placeDataDetails
            );
            data.add(placeDataWithDetails);
        }
        return data;
    }

    private PlaceData getPlaceData(int name) {
        return new PlaceData(
                "http:/icon",
                "Store, Bank",
                "Name " + name,
                new PlaceLocation(0, 0)
        );
    }
    @NonNull
    private PlaceDataDetails getPlaceDataDetails(int reviewsCount) {
        return new PlaceDataDetails(
                getOpenHours("8:00", "16:00"),
                4.5,
                getReviews(reviewsCount)
        );
    }
    private OpenHours[] getOpenHours(String opened, String closed) {
        OpenHours[] openHours = new OpenHours[DAYS_IN_WEEK];
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            openHours[i] = new OpenHours(opened, closed);
        }
        return openHours;
    }
    private ArrayList<Review> getReviews(int size) {
        ArrayList<Review> reviews = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Review review = new Review(
                    "Author" + i,
                    "Review" + i,
                    i
            );
            reviews.add(review);
        }
        return reviews;
    }

}
