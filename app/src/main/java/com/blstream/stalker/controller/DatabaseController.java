package com.blstream.stalker.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.blstream.stalker.controller.database.DatabaseContract;
import com.blstream.stalker.controller.database.DatabaseHelper;
import com.blstream.stalker.controller.interfaces.IDatabaseController;
import com.blstream.stalker.model.Location;
import com.blstream.stalker.model.OpenHours;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataDetails;
import com.blstream.stalker.model.PlaceDataWithDetails;
import com.blstream.stalker.model.Review;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Patryk Gwiazdowski on 13.04.2016.
 * // Good Job Patryk
 */
public class DatabaseController implements IDatabaseController {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final int DAYS_IN_WEEK = 7;
    private Context context;

    public DatabaseController(Context context) {
        this.context = context;
    }

    /**
     * clears all rows in all tables of Database
     */
    @Override
    public void clearDB() {
        Log.d(TAG, "clearDB: rows Deleted from Places:" + clearPlacesTable());
        Log.d(TAG, "clearDB: rows Deleted from Details:" + clearDetailsTable());
        Log.d(TAG, "clearDB: rows Deleted from Reviews:" + clearReviewsTable());
    }

    /**
     * @return all stroed places data
     */
    @Override
    public List<PlaceData> getAllPlacesData() {
        ArrayList<PlaceData> list = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(DatabaseContract.URI_PLACES, null, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Log.d(TAG, "getAllPlacesData: " + i);
            Location location = new Location();  //Creates new Location for current PlaceData class instance
            location.setLatitude(cursor.getLong(cursor.getColumnIndex(DatabaseContract.TablePlaces.COLUMN_LATITUDE))); //Sets location parameters
            location.setLongitude(cursor.getLong(cursor.getColumnIndex(DatabaseContract.TablePlaces.COLUMN_LONGITUDE)));
            PlaceData data = new PlaceData(  //Creates new PlaceData instance, and fills it fields by Ones Retrieved from Cursor
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.TablePlaces.COLUMN_IMG_URL)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.TablePlaces.COLUMN_TYPES)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.TablePlaces.COLUMN_NAME)),
                    location);
            data.setId(cursor.getInt(cursor.getColumnIndex(DatabaseContract.TablePlaces._ID)));    //Sets PlaceData id to one Row id fromd atabase
            int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 2;
            if (day == -1) {
                day = 6;
            }
            data.setTodayOpeningHours(getPlaceDetails(data).getOpeningHours(day));
            list.add(data);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Clears TableReviews
     *
     * @return number of rows deleted
     */
    protected int clearReviewsTable() {
        return context.getContentResolver().delete(DatabaseContract.URI_REVIEWS, null, null);
    }

    /**
     * Clears TableDetails
     *
     * @return number of rows deleted
     */
    protected int clearDetailsTable() {
        return context.getContentResolver().delete(DatabaseContract.URI_DETAILS, null, null);
    }

    /**
     * Clears TablePlaces
     *
     * @return number of rows deleted
     */
    protected int clearPlacesTable() {
        return context.getContentResolver().delete(DatabaseContract.URI_PLACES, null, null);
    }

    /**
     * Gets PlaceDataDetails for PlaceData given as parameter
     *
     * @param place for which data will be returned
     * @return details for place given in parameter
     */
    @Override
    public PlaceDataDetails getPlaceDetails(PlaceData place) {
        PlaceDataDetails details;
        String where = DatabaseContract.TableDetails.COLUMN_PLACE_ID + " = ?";
        String[] selection = {String.valueOf(place.getId())};
        Cursor cursor = context.getContentResolver().query(DatabaseContract.URI_DETAILS, null, where, selection, null);
        assert cursor != null;
        cursor.moveToFirst();
        OpenHours[] openHours = new OpenHours[7];
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            openHours[i] = new OpenHours(cursor.getString(cursor.getColumnIndex(DatabaseContract.TableDetails.COLUMN_OPEN_DAY[i])),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.TableDetails.COLUMN_CLOSE_DAY[i])));
        }
        ArrayList<Review> reviews = getReviews(place.getId());
        details = new PlaceDataDetails(
                openHours,
                cursor.getDouble(cursor.getColumnIndex(DatabaseContract.TableDetails.COLUMN_RATING)),
                reviews);
        cursor.close();
        return details;
    }

    /**
     * add specified data to database
     *
     * @param data to be set
     * @return true when successfully added, false when error occurred during adding
     */
    @Override
    public boolean addPlacesToDB(List<PlaceDataWithDetails> data) {
        for (PlaceDataWithDetails place : data) {
            addPlace(place);
        }
        return true;
    }


    /**
     * @param id of Place
     * @return list of reviews of place with id specified in parameter
     */
    private ArrayList<Review> getReviews(int id) {
        String where = DatabaseContract.TableReviews.COLUMN_PLACE_ID + "= ?";
        String[] selection = {String.valueOf(id)};
        Cursor cursor = context.getContentResolver().query(DatabaseContract.URI_REVIEWS, null, where, selection, null);
        ArrayList<Review> list = new ArrayList<>();
        assert cursor != null;
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Review review = new Review(
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.TableReviews.COLUMN_AUTHOR)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.TableReviews.COLUMN_REVIEW)),
                    cursor.getDouble(cursor.getColumnIndex(DatabaseContract.TableReviews.COLUMN_RATING))
            );
            list.add(review);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    private boolean addPlace(PlaceDataWithDetails data) {

        int placeDataId = addPlaceData(data.getPlaceData());
        Log.d(TAG, "addPlace: Added row: " + placeDataId);
        if (placeDataId == -1) {
            return false;
        }
        addDataDetails(data.getPlaceDataDetails(), placeDataId);
        return true;
    }

    /**
     * @param placeData Data to add
     * @return row id
     */
    private int addPlaceData(PlaceData placeData) {
        ContentValues valuesData = new ContentValues();
        valuesData.put(DatabaseContract.TablePlaces.COLUMN_NAME, placeData.getName());
        valuesData.put(DatabaseContract.TablePlaces.COLUMN_IMG_URL, placeData.getIconUrl());
        valuesData.put(DatabaseContract.TablePlaces.COLUMN_TYPES, placeData.getTypes());
        valuesData.put(DatabaseContract.TablePlaces.COLUMN_LATITUDE, placeData.getLocation().getLatitude());
        valuesData.put(DatabaseContract.TablePlaces.COLUMN_LONGITUDE, placeData.getLocation().getLongitude());
        Uri uri = context.getContentResolver().insert(DatabaseContract.URI_PLACES, valuesData);
        String rowNumber;
        if (uri != null) {
            rowNumber = uri.getLastPathSegment();
            return Integer.valueOf(rowNumber);
        }
        return -1;

    }

    private void addDataDetails(PlaceDataDetails placeDataDetails, int placeDataId) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TableDetails.COLUMN_RATING, placeDataDetails.getRating());
        values.put(DatabaseContract.TableDetails.COLUMN_PLACE_ID, placeDataId);
        for (int i = 0; i < 7; i++) {
            values.put(DatabaseContract.TableDetails.COLUMN_OPEN_DAY[i], placeDataDetails.getOpeningHours(i).getOpenTime());
            values.put(DatabaseContract.TableDetails.COLUMN_CLOSE_DAY[i], placeDataDetails.getOpeningHours(i).getCloseTime());
        }
        context.getContentResolver().insert(DatabaseContract.URI_DETAILS, values);
        addDataReviews(placeDataDetails.getReviews(), placeDataId);
    }

    private void addDataReviews(ArrayList<Review> reviews, int placeDataId) {
        ContentValues values;
        for (Review review : reviews) {
            values = new ContentValues();
            values.put(DatabaseContract.TableReviews.COLUMN_REVIEW, review.getReview());
            values.put(DatabaseContract.TableReviews.COLUMN_AUTHOR, review.getAuthor());
            values.put(DatabaseContract.TableReviews.COLUMN_RATING, review.getRating());
            values.put(DatabaseContract.TableReviews.COLUMN_PLACE_ID, placeDataId);
            context.getContentResolver().insert(DatabaseContract.URI_REVIEWS, values);
        }
    }
}
