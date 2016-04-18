package com.blstream.stalker.controller.interfaces;

import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataDetails;
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
     * Gets PlaceDataDetails for PlaceData given as parameter
     * @param place for which data will be returned
     * @return  details for place given in parameter
     */
    PlaceDataDetails getPlaceDetails(PlaceData place);

    /**
     * add specified data to database
     * @param data to be set
     * @return true when successfully added, false when error occurred during adding
     */
    boolean addPlacesToDB(List<PlaceDataWithDetails> data);

}
