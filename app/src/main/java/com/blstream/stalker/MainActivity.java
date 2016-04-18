package com.blstream.stalker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends BaseActivity implements Observer {
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void update(Observable observable, Object data) {

    }
}
