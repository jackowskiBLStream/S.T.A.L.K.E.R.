package com.blstream.stalker.controller;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.blstream.stalker.controller.database.DatabaseHelper;
import com.blstream.stalker.controller.database.PlacesContentProvider;
import com.blstream.stalker.controller.database.TableDetails;
import com.blstream.stalker.controller.database.TablePlaces;
import com.blstream.stalker.controller.database.TableReviews;
import com.blstream.stalker.controller.interfaces.IDatabaseController;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataDetails;
import com.blstream.stalker.model.PlaceDataWithDetails;
import com.blstream.stalker.model.interfaces.IReviews;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Patryk Gwiazdowski on 13.04.2016.
 * // Good Job Patryk
 */
public class DatabaseController implements IDatabaseController {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private Context context;

    public DatabaseController(Context context) {
        this.context = context;
    }

    /**
     * clears all rows in Database
     */
    @Override
    public void clearDB() {
    }

    /**
     * @return all stroed places data
     */
    @Override
    public List<PlaceData> getAllPlacesData() {
        ArrayList<PlaceData> list = new ArrayList<>();

        return list;
    }

    /**
     * add specified data to database
     *
     * @param data to be set
     * @return true when successfully added, false when error occurred during adding
     */
    @Override
    public boolean addPlacesToDB(List<PlaceDataWithDetails> data) {
        for (PlaceDataWithDetails place : data){
            addPlace(place);
        }
        return true;
    }

    private boolean addPlace(PlaceDataWithDetails data) {

        int placeDataId = addPlaceData(data.getPlaceData());
        Log.d(TAG, "addPlace: Added row: " + placeDataId);
        if (placeDataId == -1){
            return false;
        }
        addDataDetails(data.getPlaceDataDetails(),placeDataId);
        return true;
    }

    /**
     *
     * @param placeData Data to add
     * @return row id
     */
    private int addPlaceData(PlaceData placeData){
        ContentValues valuesData = new ContentValues();
        valuesData.put(TablePlaces.COLUMN_NAME, placeData.getName());
        valuesData.put(TablePlaces.COLUMN_IMG_URL, placeData.getIconUrl());
        valuesData.put(TablePlaces.COLUMN_TYPES, placeData.getTypes());
        valuesData.put(TablePlaces.COLUMN_LATITUDE, placeData.getLocation().getLatitude());
        valuesData.put(TablePlaces.COLUMN_LONGITUDE,placeData.getLocation().getLongitude());
        Uri uri = context.getContentResolver().insert(PlacesContentProvider.URI_PLACES, valuesData);
        String rowNumber;
        if (uri != null) {
            rowNumber = uri.getLastPathSegment();
            return Integer.valueOf(rowNumber);
        }
        return -1;

    }

    private void addDataDetails(PlaceDataDetails placeDataDetails, int placeDataId) {
        ContentValues values = new ContentValues();
        values.put(TableDetails.COLUMN_RATING, placeDataDetails.getRating());
        values.put(TableDetails.COLUMN_PLACE_ID, placeDataId);
        for (int i=0; i<7; i++){
            values.put(TableDetails.COLUMN_OPEN_DAY[i], placeDataDetails.getOpenHours(i).getCloseTime());
            values.put(TableDetails.COLUMN_CLOSE_DAY[i], placeDataDetails.getOpenHours(i).getCloseTime());
        }
        context.getContentResolver().insert(PlacesContentProvider.URI_DETAILS, values);
        addDataReviews(placeDataDetails.getReviews(), placeDataId);
    }

    private void addDataReviews(Collection<IReviews> reviews, int placeDataId) {
        ContentValues values;
        for (IReviews review : reviews){
            values = new ContentValues();
            values.put(TableReviews.COLUMN_REVIEW, review.getReview());
            values.put(TableReviews.COLUMN_AUTHOR, review.getAuthor());
            values.put(TableReviews.COLUMN_RATING, review.getRating());
            values.put(TableReviews.COLUMN_PLACE_ID, placeDataId);
            context.getContentResolver().insert(PlacesContentProvider.URI_REVIEWS, values);
        }
    }
}
