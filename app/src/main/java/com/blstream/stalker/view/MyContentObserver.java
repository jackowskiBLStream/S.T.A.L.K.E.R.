package com.blstream.stalker.view;

import android.annotation.TargetApi;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import com.blstream.stalker.view.interfaces.ContentObserverCallback;

public class MyContentObserver extends ContentObserver {
    private static final String LOG_TAG = "MyContentObserver";
    private ContentObserverCallback contentObserverCallback;

    public MyContentObserver(ContentObserverCallback contentObserverCallback) {
        super(null);
        this.contentObserverCallback = contentObserverCallback;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onChange(boolean selfChange) {
        this.onChange(selfChange, null);
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        // this is NOT UI thread, this is a BACKGROUND thread
        Log.i(LOG_TAG, "Received onChange");
        contentObserverCallback.updateAfterDatabaseChanges();
    }

}
