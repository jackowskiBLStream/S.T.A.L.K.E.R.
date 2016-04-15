package com.blstream.stalker.controller.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Patryk Gwiazdowski on 14.04.2016.
 * // Good Job Patryk
 */
public class TableReviews {

    //table name
    public static final String TABLE_REVIEWS = "reviews";
    //Table columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_REVIEW = "review";
    public static final String COLUMN_PLACE_ID = "place_id";

    // creation SQL statement
    private static final String TABLE_CREATE = "create table "
            + TABLE_REVIEWS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_REVIEW + " text not null, "
            + COLUMN_PLACE_ID + "integer not null "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(TableReviews.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
        onCreate(database);
    }
}
