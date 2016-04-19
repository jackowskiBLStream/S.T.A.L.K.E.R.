package com.blstream.stalker.controller.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.blstream.stalker.controller.database.DatabaseContract.*;

/**
 * A helper class to manage database creation and version management.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "places.db";
    private static final int DATABASE_VERSION = 1;
    /**
     * {@inheritDoc}
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        TableDetails.onCreate(db);
        TablePlaces.onCreate(db);
        TableReviews.onCreate(db);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TableDetails.onUpgrade(db, oldVersion, newVersion);
        TablePlaces.onUpgrade(db, oldVersion, newVersion);
        TableReviews.onUpgrade(db, oldVersion, newVersion);
    }
}
