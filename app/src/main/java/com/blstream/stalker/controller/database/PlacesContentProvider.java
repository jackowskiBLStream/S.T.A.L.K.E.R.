package com.blstream.stalker.controller.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.blstream.stalker.controller.database.DatabaseContract.*;

/**
 * Content provider for database
 */
public class PlacesContentProvider extends ContentProvider {


    private static final int PLACES = 0;
    private static final int DETAILS = 2;
    private static final int REVIEWS = 4;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, TablePlaces.TABLE_NAME, PLACES);
        uriMatcher.addURI(AUTHORITY, TableDetails.TABLE_NAME, DETAILS);
        uriMatcher.addURI(AUTHORITY, TableReviews.TABLE_NAME, REVIEWS);

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
                queryBuilder.setTables(TablePlaces.TABLE_NAME);
                break;
            case DETAILS:
                queryBuilder.setTables(TableDetails.TABLE_NAME);
                break;
            case REVIEWS:
                queryBuilder.setTables(TableReviews.TABLE_NAME);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(databse.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        notifyChange(uri);
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
                id = sqLiteDatabase.insert(TablePlaces.TABLE_NAME, null, values);
                tableName = TablePlaces.TABLE_NAME;
                break;
            case DETAILS:
                id = sqLiteDatabase.insert(TableDetails.TABLE_NAME, null, values);
                tableName = TableDetails.TABLE_NAME;
                break;
            case REVIEWS:
                id = sqLiteDatabase.insert(TableReviews.TABLE_NAME, null, values);
                tableName = TableReviews.TABLE_NAME;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        notifyChange(uri);
        return Uri.parse(tableName + "/" + id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = databse.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case PLACES:
                rowsDeleted = sqLiteDatabase.delete(TablePlaces.TABLE_NAME, selection,
                        selectionArgs);
                break;
            case DETAILS:
                rowsDeleted = sqLiteDatabase.delete(TableDetails.TABLE_NAME, selection,
                        selectionArgs);
                break;
            case REVIEWS:
                rowsDeleted = sqLiteDatabase.delete(TableReviews.TABLE_NAME, selection,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        notifyChange(uri);
        return rowsDeleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private void notifyChange(@NonNull Uri uri) {
        if( getContext() != null) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }

}
