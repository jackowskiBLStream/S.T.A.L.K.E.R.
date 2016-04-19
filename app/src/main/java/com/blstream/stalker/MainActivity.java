package com.blstream.stalker;

import android.database.ContentObserver;
import android.os.Bundle;

import com.blstream.stalker.controller.database.DatabaseContract;
import com.blstream.stalker.controller.database.DatabaseHelper;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends BaseActivity implements Observer {
    //    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void update(Observable observable, Object data) {

    }


}
