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
    public static final String COLUMN_OPEN_MON = "open_mon";
    public static final String COLUMN_CLOSE_MON = "close_mon";
    public static final String COLUMN_OPEN_TUE = "open_tue";
    public static final String COLUMN_CLOSE_TUE = "close_tue";
    public static final String COLUMN_OPEN_WED = "open_wed";
    public static final String COLUMN_CLOSE_WED = "close_wed";
    public static final String COLUMN_OPEN_THU= "open_thu";
    public static final String COLUMN_CLOSE_THU = "close_thu";
    public static final String COLUMN_OPEN_FRI = "open_fri";
    public static final String COLUMN_CLOSE_FRI = "close_fri";
    public static final String COLUMN_OPEN_SAT = "open_sat";
    public static final String COLUMN_CLOSE_SAT = "close_sat";
    public static final String COLUMN_OPEN_SUN = "open_sun";
    public static final String COLUMN_CLOSE_SUN = "close_sun";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_PLACE_ID = "place_id";


    // creation SQL statement
    private static final String TABLE_CREATE = "create table "
            + TABLE_DETAILS
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_OPEN_MON + " real not null, "
            + COLUMN_CLOSE_MON + " real not null, "
            + COLUMN_OPEN_TUE + " real not null, "
            + COLUMN_CLOSE_TUE + " real not null, "
            + COLUMN_OPEN_WED + " real not null, "
            + COLUMN_CLOSE_WED + " real not null, "
            + COLUMN_OPEN_THU + " real not null, "
            + COLUMN_CLOSE_THU + " real not null, "
            + COLUMN_OPEN_FRI + " real not null, "
            + COLUMN_CLOSE_FRI + " real not null, "
            + COLUMN_OPEN_SAT + " real not null, "
            + COLUMN_CLOSE_SAT + " real not null, "
            + COLUMN_OPEN_SUN + " real not null, "
            + COLUMN_CLOSE_SUN + " real not null, "
            + COLUMN_RATING + " real not null, "
            + COLUMN_PLACE_ID + "integer not null "
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
