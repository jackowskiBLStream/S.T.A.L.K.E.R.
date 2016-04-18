package com.blstream.stalker;

import android.support.annotation.NonNull;
import android.support.test.rule.ActivityTestRule;

import com.blstream.stalker.model.OpenHours;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataDetails;
import com.blstream.stalker.model.PlaceDataWithDetails;
import com.blstream.stalker.model.PlaceLocation;
import com.blstream.stalker.model.Review;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class DatabaseTest {
    private static final int DAYS_IN_WEEK = 7;


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    DatabaseControllerForTests dbController;

    @Before
    public void before() {
        dbController = new DatabaseControllerForTests(mActivityRule.getActivity());
        dbController.clearDB();
    }

    @Test
    public void shouldDeleteOneRowFromEachTable() {
        //Given
        dbController.addPlacesToDB(getData(1, 1));
        //When
        int deletedPlaces = dbController.clearPlacesTable();
        int deletedReviews = dbController.clearReviewsTable();
        int deletedDetails = dbController.clearDetailsTable();
        //Then
        Assert.assertEquals("After adding one place to db, one should be deleted", 1, deletedPlaces);
        Assert.assertEquals("After adding one detail to db, one should be deleted", 1, deletedReviews);
        Assert.assertEquals("After adding one review to db, one should be deleted", 1, deletedDetails);
    }

    @Test
    public void shouldDeleteZeroRows() {
        //Given

        //When
        int deletedPlaces = dbController.clearPlacesTable();
        int deletedReviews = dbController.clearReviewsTable();
        int deletedDetails = dbController.clearDetailsTable();

        //Then
        Assert.assertEquals("Nothing was added to db. zero roz should be deleted", 0, deletedPlaces);
        Assert.assertEquals("Nothing was added to db. zero roz should be deleted", 0, deletedDetails);
        Assert.assertEquals("Nothing was added to db. zero roz should be deleted", 0, deletedReviews);
    }

    @Test
    public void shouldDeleteTenReviewsAndFivePlaces() {
        //Given
        dbController.addPlacesToDB(getData(5, 2));
        //When
        int deletedReviews = dbController.clearReviewsTable();
        int deletedPlaces = dbController.clearPlacesTable();
        //Then
        Assert.assertEquals("Should delete 10 reviews. 5 places where added, and 2 reviews for each plaze", 10, deletedReviews);
        Assert.assertEquals("Should delete 5 places. 5 places where added", 5, deletedPlaces);
    }

    /**
     * Returns array of PlaceDataWithDetails of given size, ready for adding to Database
     *
     * @param size of data to return
     * @return list of data
     */
    private ArrayList<PlaceDataWithDetails> getData(int size, int reviewsCount) {
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

    @NonNull
    private PlaceDataDetails getPlaceDataDetails(int reviewsCount) {
        return new PlaceDataDetails(
                getOpenHours("8:00", "16:00"),
                4.5,
                getReviews(reviewsCount)
        );
    }

    private PlaceData getPlaceData(int name) {
        return new PlaceData(
                "http:/icon",
                "Store, Bank",
                "Name " + name,
                new PlaceLocation(0, 0)
        );
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

    private OpenHours[] getOpenHours(String opened, String closed) {
        OpenHours[] openHours = new OpenHours[DAYS_IN_WEEK];
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            openHours[i] = new OpenHours(opened, closed);
        }
        return openHours;
    }

}