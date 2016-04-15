package com.blstream.stalker.controller.location;

/**
 *
 */
public interface IOperationsController {

    void connectGoogleApiClient();

    void disconnectGoogleApiClient();

    void createGoogleApiClientInstance();

    void onCreate();

    void onStop();

    void onStart();

    void onPause();

    void onResume();





}
