package com.blstream.stalker.controller.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * SQlite table that contains places data data
 */

//FIXME: BaseColumn
public class TablePlaces {

    //table name
    public static final String TABLE_PLACES = "places";
    //Table columns

    //FIXME: move to conteact
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPES = "types";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_IMG_URL = "img_url";

    // creation SQL statement
    private static final String TABLE_CREATE = "create table "
            + TABLE_PLACES
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_TYPES + " text not null, "
            + COLUMN_IMG_URL + " text, "
            + COLUMN_LATITUDE + " real not null, "
            + COLUMN_LONGITUDE + " real not null "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(TablePlaces.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
        onCreate(database);
    }
}
