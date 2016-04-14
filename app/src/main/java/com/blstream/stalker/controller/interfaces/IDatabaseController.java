package com.blstream.stalker.controller.interfaces;

import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataWithDetails;

import java.util.List;

/**
 * Used for controlling database
 */
public interface IDatabaseController {

    /**
     * clears all rows in Database
     */
    void clearDB();

    /**
     *
     * @return all stroed places data
     */
    List<PlaceData> getAllPlacesData();

    /**
     * add specified data to database
     * @param data to be set
     * @return true when successfully added, false when error occurred during adding
     */
    boolean addPlacesToDB(List<PlaceDataWithDetails> data);

}
