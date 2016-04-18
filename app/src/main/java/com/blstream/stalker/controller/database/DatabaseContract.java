package com.blstream.stalker.controller.database;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Patryk Gwiazdowski on 18.04.2016.
 * // Good Job Patryk
 */
public class DatabaseContract {

    public static final String AUTHORITY = "com.blstream.stalker.controller.database.PlacesContentProvider";
    public static final String URI_PREFIX = "content://" + AUTHORITY + "/";
    public static final Uri URI_PLACES = Uri.parse(URI_PREFIX + TablePlaces.TABLE_NAME);
    public static final Uri URI_DETAILS = Uri.parse(URI_PREFIX + TableDetails.TABLE_NAME);
    public static final Uri URI_REVIEWS = Uri.parse(URI_PREFIX + TableReviews.TABLE_NAME);

    /**
     * SQLite table that contains detailed data about places
     */
    public static class TableDetails implements BaseColumns {
        //Table name
        public static final String TABLE_NAME = "details";
        //Table columns
        public static final String[] COLUMN_OPEN_DAY = {"open_mon", "open_tue", "open_wed", "open_thu", "open_fri", "open_sat", "open_sun"};
        public static final String[] COLUMN_CLOSE_DAY = {"close_mon", "close_tue", "close_wed", "close_thu", "close_fri", "close_sat", "close_sun"};
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_PLACE_ID = "place_id";
        public static final String TEXT_NOT_NULL = " text not null, ";
        // creation SQL statement
        private static final String TABLE_CREATE = "create table "
                + TABLE_NAME
                + "("
                + _ID + " integer primary key autoincrement, "
                + COLUMN_OPEN_DAY[0] + TEXT_NOT_NULL
                + COLUMN_CLOSE_DAY[0] + TEXT_NOT_NULL
                + COLUMN_OPEN_DAY[1] + TEXT_NOT_NULL
                + COLUMN_CLOSE_DAY[1] + TEXT_NOT_NULL
                + COLUMN_OPEN_DAY[2] + TEXT_NOT_NULL
                + COLUMN_CLOSE_DAY[2] + TEXT_NOT_NULL
                + COLUMN_OPEN_DAY[3] + TEXT_NOT_NULL
                + COLUMN_CLOSE_DAY[3] + TEXT_NOT_NULL
                + COLUMN_OPEN_DAY[4] + TEXT_NOT_NULL
                + COLUMN_CLOSE_DAY[4] + TEXT_NOT_NULL
                + COLUMN_OPEN_DAY[5] + TEXT_NOT_NULL
                + COLUMN_CLOSE_DAY[5] + TEXT_NOT_NULL
                + COLUMN_OPEN_DAY[6] + TEXT_NOT_NULL
                + COLUMN_CLOSE_DAY[6] + TEXT_NOT_NULL
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
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(database);
        }
    }

    /**
     * SQlite table that contains places data
     */
    public static class TablePlaces implements BaseColumns {

        //table name
        public static final String TABLE_NAME = "places";
        //Table columns
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPES = "types";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_IMG_URL = "img_url";

        // creation SQL statement
        private static final String TABLE_CREATE = "create table "
                + TABLE_NAME
                + "("
                + _ID + " integer primary key autoincrement, "
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
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(database);
        }
    }

    /**
     * SQlite table that contains places Reviews
     */
    public static class TableReviews implements BaseColumns {

        //table name
        public static final String TABLE_NAME = "reviews";
        //Table columns
        public static final String COLUMN_REVIEW = "review";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_PLACE_ID = "place_id";

        // creation SQL statement
        private static final String TABLE_CREATE = "create table "
                + TABLE_NAME
                + "("
                + _ID + " integer primary key autoincrement, "
                + COLUMN_REVIEW + " text not null, "
                + COLUMN_AUTHOR + " text not null, "
                + COLUMN_RATING + " real not null, "
                + COLUMN_PLACE_ID + " integer not null "
                + ");";

        public static void onCreate(SQLiteDatabase database) {
            database.execSQL(TABLE_CREATE);
        }

        public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                     int newVersion) {
            Log.w(TableReviews.class.getName(), "Upgrading database from version "
                    + oldVersion + " to " + newVersion
                    + ", which will destroy all old data");
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(database);
        }
    }
}
