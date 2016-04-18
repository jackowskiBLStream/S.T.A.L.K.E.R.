package com.blstream.stalker.controller.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * SQLite table that contains detailed data about places
 */
public class TableDetails {

    //Table name
    public static final String TABLE_DETAILS = "details";
    //Table columns
    public static final String COLUMN_ID = "_id";
    public static final String[] COLUMN_OPEN_DAY = {"open_mon", "open_tue", "open_wed", "open_thu", "open_fri", "open_sat", "open_sun"};
    public static final String[] COLUMN_CLOSE_DAY = {"close_mon", "close_tue", "close_wed", "close_thu", "close_fri", "close_sat", "close_sun"};
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_PLACE_ID = "place_id";


    // creation SQL statement
    private static final String TABLE_CREATE = "create table "
            + TABLE_DETAILS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_OPEN_DAY[0] + " text not null, "
            + COLUMN_CLOSE_DAY[0] + " text not null, "
            + COLUMN_OPEN_DAY[1] + " text not null, "
            + COLUMN_CLOSE_DAY[1] + " text not null, "
            + COLUMN_OPEN_DAY[2] + " text not null, "
            + COLUMN_CLOSE_DAY[2] + " text not null, "
            + COLUMN_OPEN_DAY[3] + " text not null, "
            + COLUMN_CLOSE_DAY[3] + " text not null, "
            + COLUMN_OPEN_DAY[4] + " text not null, "
            + COLUMN_CLOSE_DAY[4] + " text not null, "
            + COLUMN_OPEN_DAY[5] + " text not null, "
            + COLUMN_CLOSE_DAY[5] + " text not null, "
            + COLUMN_OPEN_DAY[6] + " text not null, "
            + COLUMN_CLOSE_DAY[6] + " text not null, "
            + COLUMN_RATING + " real not null, "
            + COLUMN_PLACE_ID + " integer not null "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(TablePlaces.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
        onCreate(database);
    }
}
