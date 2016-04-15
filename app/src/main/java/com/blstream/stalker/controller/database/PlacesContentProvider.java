package com.blstream.stalker.controller.database;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Content provider for database
 */
public class PlacesContentProvider extends ContentProvider {

    //FIXME: a gdzie contact?

    public static final String AUTHORITY = "com.blstream.stalker.controller.database.PlacesContentProvider";
    public static final Uri URI_PLACES =
            Uri.parse("content://" + AUTHORITY + "/" + TablePlaces.TABLE_PLACES);
    public static final Uri URI_DETAILS =
            Uri.parse("content://" + AUTHORITY + "/" + TableDetails.TABLE_DETAILS);
    public static final Uri URI_REVIEWS =
            Uri.parse("content://" + AUTHORITY + "/" + TableReviews.TABLE_REVIEWS);
    private static final int PLACES = 0;
    private static final int PLACES_ID = 1;
    private static final int DETAILS = 2;
    private static final int DETAILS_ID = 3;
    private static final int REVIEWS = 4;
    private static final int REVIEWS_ID = 5;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, TablePlaces.TABLE_PLACES, PLACES);
        uriMatcher.addURI(AUTHORITY, TablePlaces.TABLE_PLACES + "/#", PLACES_ID);
        uriMatcher.addURI(AUTHORITY, TableDetails.TABLE_DETAILS, DETAILS);
        uriMatcher.addURI(AUTHORITY, TableDetails.TABLE_DETAILS + "/#", DETAILS_ID);
        uriMatcher.addURI(AUTHORITY, TableReviews.TABLE_REVIEWS, REVIEWS);
        uriMatcher.addURI(AUTHORITY, TableReviews.TABLE_REVIEWS + "/#", REVIEWS_ID);

    }

    private DatabaseHelper databse;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreate() {
        databse = new DatabaseHelper(getContext());
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case PLACES:
                queryBuilder.setTables(TablePlaces.TABLE_PLACES);
                break;
            case DETAILS:
                queryBuilder.setTables(TableDetails.TABLE_DETAILS);
                break;
            case REVIEWS:
                queryBuilder.setTables(TableReviews.TABLE_REVIEWS);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(databse.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        if (getContext() != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        int uriType = uriMatcher.match(uri);

        SQLiteDatabase sqLiteDatabase = databse.getWritableDatabase();
        long id;
        String tableName;
        switch (uriType) {
            case PLACES:
                id = sqLiteDatabase.insert(TablePlaces.TABLE_PLACES, null, values);
                tableName = TablePlaces.TABLE_PLACES;
                break;
            case DETAILS:
                id = sqLiteDatabase.insert(TableDetails.TABLE_DETAILS, null, values);
                tableName = TableDetails.TABLE_DETAILS;
                break;
            case REVIEWS:
                id = sqLiteDatabase.insert(TableReviews.TABLE_REVIEWS, null, values);
                tableName = TableReviews.TABLE_REVIEWS;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return Uri.parse(tableName + "/" + id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private void checkColumns(String[] projection) {
        //TODO implement this
    }

}
