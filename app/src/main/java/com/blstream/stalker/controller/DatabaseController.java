package com.blstream.stalker.controller;

import com.blstream.stalker.controller.interfaces.IDatabaseController;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataWithDetails;

import java.util.List;

/**
 * Created by Patryk Gwiazdowski on 13.04.2016.
 * // Good Job Patryk
 */
public class DatabaseController implements IDatabaseController {


    /**
     * clears all rows in Database
     */
    @Override
    public void clearDB() {
        DatabaseController controller = new DatabaseController();
        controller.clearDB();
    }

    /**
     * @return all stroed places data
     */
    @Override
    public List<PlaceData> getAllPlacesData() {
        return null;
    }

    /**
     * add specified data to database
     *
     * @param data to be set
     * @return true when successfully added, false when error occurred during adding
     */
    @Override
    public boolean addPlacesToDB(List<PlaceDataWithDetails> data) {
        return false;
    }
}
