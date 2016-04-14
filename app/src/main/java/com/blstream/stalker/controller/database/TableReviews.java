package com.blstream.stalker.controller.database;

/**
 * Created by Patryk Gwiazdowski on 14.04.2016.
 * // Good Job Patryk
 */
public class TableReviews {

    //table name
    public static final String TABLE_DETAILS = "reviews";
    //Table columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_REVIEW = "review";
    public static final String COLUMN_PLACE_ID = "place_id";

    // creation SQL statement
    private static final String TABLE_CREATE = "create table "
            + TABLE_DETAILS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_REVIEW + " text not null, "
            + COLUMN_PLACE_ID + "integer not null "
            + ");";
}
