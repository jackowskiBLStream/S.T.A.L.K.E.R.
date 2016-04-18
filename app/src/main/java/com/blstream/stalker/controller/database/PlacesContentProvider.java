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

/**
 * Content provider for database
 */
public class PlacesContentProvider extends ContentProvider {


    private static final int PLACES = 0;
    private static final int PLACES_ID = 1;
    private static final int DETAILS = 2;
    private static final int DETAILS_ID = 3;
    private static final int REVIEWS = 4;
    private static final int REVIEWS_ID = 5;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DatabaseContract.AUTHORITY, DatabaseContract.TablePlaces.TABLE_PLACES, PLACES);
        uriMatcher.addURI(DatabaseContract.AUTHORITY, DatabaseContract.TablePlaces.TABLE_PLACES + "/#", PLACES_ID);
        uriMatcher.addURI(DatabaseContract.AUTHORITY, DatabaseContract.TableDetails.TABLE_NAME, DETAILS);
        uriMatcher.addURI(DatabaseContract.AUTHORITY, DatabaseContract.TableDetails.TABLE_NAME + "/#", DETAILS_ID);
        uriMatcher.addURI(DatabaseContract.AUTHORITY, DatabaseContract.TableReviews.TABLE_REVIEWS, REVIEWS);
        uriMatcher.addURI(DatabaseContract.AUTHORITY, DatabaseContract.TableReviews.TABLE_REVIEWS + "/#", REVIEWS_ID);

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
                queryBuilder.setTables(DatabaseContract.TablePlaces.TABLE_PLACES);
                break;
            case DETAILS:
                queryBuilder.setTables(DatabaseContract.TableDetails.TABLE_NAME);
                break;
            case REVIEWS:
                queryBuilder.setTables(DatabaseContract.TableReviews.TABLE_REVIEWS);
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
        //TODO: refactor switch contentes
        switch (uriType) {
            case PLACES:
                id = sqLiteDatabase.insert(DatabaseContract.TablePlaces.TABLE_PLACES, null, values);
                //FIXME: notify observers
                tableName = DatabaseContract.TablePlaces.TABLE_PLACES;
                break;
            case DETAILS:
                id = sqLiteDatabase.insert(DatabaseContract.TableDetails.TABLE_NAME, null, values);
                tableName = DatabaseContract.TableDetails.TABLE_NAME;
                break;
            case REVIEWS:
                id = sqLiteDatabase.insert(DatabaseContract.TableReviews.TABLE_REVIEWS, null, values);
                tableName = DatabaseContract.TableReviews.TABLE_REVIEWS;
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
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int uriType = uriMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = databse.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case PLACES:
                rowsDeleted = sqLiteDatabase.delete(DatabaseContract.TablePlaces.TABLE_PLACES, selection,
                        selectionArgs);
                //FIXME: notify observers
                break;
            case DETAILS:
                rowsDeleted = sqLiteDatabase.delete(DatabaseContract.TableDetails.TABLE_NAME, selection,
                        selectionArgs);
                break;
            case REVIEWS:
                rowsDeleted = sqLiteDatabase.delete(DatabaseContract.TableReviews.TABLE_REVIEWS, selection,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return rowsDeleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private void checkColumns(String[] projection) {
        //TODO implement this
    }

}
