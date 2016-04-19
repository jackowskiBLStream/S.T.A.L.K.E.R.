package com.blstream.stalker.controller.internetConnection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 *  Class listen to internet connection status change;
 */
public class InternetConnectionListener extends BroadcastReceiver{
    public static boolean isOnline;
    private InternetConnectionObserver observer;
    public InternetConnectionListener() {
        observer = InternetConnectionObserver.getInstance();
    }
    /**
     *{@inheritDoc}
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        isOnline =  netInfo != null && netInfo.isConnectedOrConnecting();
        observer.notifyMyObservers();
    }
}
