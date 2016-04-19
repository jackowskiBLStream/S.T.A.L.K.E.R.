package com.blstream.stalker;

import android.os.Bundle;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends BaseActivity implements Observer {
//    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void update(Observable observable, Object data) {
    }
}
