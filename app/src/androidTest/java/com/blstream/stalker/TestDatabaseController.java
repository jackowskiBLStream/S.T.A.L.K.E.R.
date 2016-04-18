package com.blstream.stalker;

import android.content.Context;

import com.blstream.stalker.controller.DatabaseController;

/**
 * Class used in tests for access to protected methods
 */
public class TestDatabaseController extends DatabaseController {

    public TestDatabaseController(Context context) {
        super(context);
    }

    /**
     * Clears TableReviews
     *
     * @return number of rows deleted
     */
    @Override
    protected int clearReviewsTable() {
        return super.clearReviewsTable();
    }

    /**
     * Clears TableDetails
     *
     * @return number of rows deleted
     */
    @Override
    protected int clearDetailsTable() {
        return super.clearDetailsTable();
    }

    /**
     * Clears TablePlaces
     *
     * @return number of rows deleted
     */
    @Override
    protected int clearPlacesTable() {
        return super.clearPlacesTable();
    }
}
