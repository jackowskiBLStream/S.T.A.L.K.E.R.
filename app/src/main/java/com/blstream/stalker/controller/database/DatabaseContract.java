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
    public static final Uri URI_PLACES =
            Uri.parse("content://" + AUTHORITY + "/" + TablePlaces.TABLE_PLACES);
    public static final Uri URI_DETAILS =
            Uri.parse("content://" + AUTHORITY + "/" + TableDetails.TABLE_DETAILS);
    public static final Uri URI_REVIEWS =
            Uri.parse("content://" + AUTHORITY + "/" + TableReviews.TABLE_REVIEWS);


    public DatabaseContract(){

    }


    /**
     * SQLite table that contains detailed data about places
     */
    public static class TableDetails implements BaseColumns {

        //Table name
        public static final String TABLE_DETAILS = "details";
        //Table columns
        public static final String[] COLUMN_OPEN_DAY = {"open_mon", "open_tue", "open_wed", "open_thu", "open_fri", "open_sat", "open_sun"};
        public static final String[] COLUMN_CLOSE_DAY = {"close_mon", "close_tue", "close_wed", "close_thu", "close_fri", "close_sat", "close_sun"};
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_PLACE_ID = "place_id";


        // creation SQL statement
        private static final String TABLE_CREATE = "create table "
                + TABLE_DETAILS
                + "("
                + _ID + " integer primary key autoincrement, "
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

    /**
     * SQlite table that contains places data data
     */
    public static class TablePlaces implements BaseColumns {

        //table name
        public static final String TABLE_PLACES = "places";
        //Table columns
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TYPES = "types";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_IMG_URL = "img_url";

        // creation SQL statement
        private static final String TABLE_CREATE = "create table "
                + TABLE_PLACES
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
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);
            onCreate(database);
        }
    }

    /**
     * Created by Patryk Gwiazdowski on 14.04.2016.
     * // Good Job Patryk
     */
    public static class TableReviews implements BaseColumns{

        //table name
        public static final String TABLE_REVIEWS = "reviews";
        //Table columns
        public static final String COLUMN_REVIEW = "review";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_RATING = "rating";
        public static final String COLUMN_PLACE_ID = "place_id";

        // creation SQL statement
        private static final String TABLE_CREATE = "create table "
                + TABLE_REVIEWS
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
            database.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
            onCreate(database);
        }
    }
}
