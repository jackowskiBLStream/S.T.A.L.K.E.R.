package com.blstream.stalker.controller.internetConnection;

import java.util.Observable;


/**
 * class used to notify observers abaut internet connection status change.
 */
public class InternetConnectionObserver extends Observable {

    private static InternetConnectionObserver instance = null;

    private InternetConnectionObserver() {
    }

    /**
     *
     * @return singleton instance of InternetConnection observer
     */
    public static InternetConnectionObserver getInstance() {
        if (instance == null) {
            return instance = new InternetConnectionObserver();
        } else {
            return instance;
        }

    }

    /**
     * Notifing observers when internet connection status has changed.
     */
    public void notifyMyObservers(){
        this.setChanged();
        this.notifyObservers();
    }
}
