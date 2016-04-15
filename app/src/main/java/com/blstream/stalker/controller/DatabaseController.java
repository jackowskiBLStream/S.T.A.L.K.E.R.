package com.blstream.stalker.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.blstream.stalker.controller.database.DatabaseHelper;
import com.blstream.stalker.controller.database.PlacesContentProvider;
import com.blstream.stalker.controller.database.TableDetails;
import com.blstream.stalker.controller.database.TablePlaces;
import com.blstream.stalker.controller.interfaces.IDatabaseController;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataWithDetails;

import java.util.ArrayList;
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
        PlaceData placeData = data.getPlaceData();
        ContentValues valuesData = new ContentValues();
        valuesData.put(TablePlaces.COLUMN_NAME, placeData.getName());
        valuesData.put(TablePlaces.COLUMN_IMG_URL, placeData.getIconUrl());
        valuesData.put(TablePlaces.COLUMN_TYPES, placeData.getTypes());
        valuesData.put(TablePlaces.COLUMN_LATITUDE, placeData.getLocation().getLatitude());
        valuesData.put(TablePlaces.COLUMN_LONGITUDE,placeData.getLocation().getLongitude());
        
        Log.d(TAG, "addPlace: "+context.getContentResolver().insert(PlacesContentProvider.URI_PLACES, valuesData));

        ContentValues valuesDetails = new ContentValues();
        valuesDetails.put(TableDetails.COLUMN_RATING, )

        return true;
    }
}
