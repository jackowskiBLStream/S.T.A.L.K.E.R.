package com.blstream.stalker.view.interfaces;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import com.blstream.stalker.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//FIXME: nazwa -> to nie zawsze bedzie fragment
public interface IMainFragment {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NO_INTERNET_CONNECTION_ERROR, NO_GPS_CONNECTION_ERROR, NO_INTERNET_AND_GPS_CONNECTION_ERROR})
    public @interface ErrorMode {}
    int NO_INTERNET_CONNECTION_ERROR = R.string.no_internet_connection;
    int NO_GPS_CONNECTION_ERROR = R.string.no_gps_connection;
    int NO_INTERNET_AND_GPS_CONNECTION_ERROR = R.string.no_gps_and_internet_connection_error;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LOGIN_FRAGMENT, DETAIL_FRAGMENT, LIST_FRAGMENT})
    public @interface FragmentType {}
    int LOGIN_FRAGMENT = 1;
    int DETAIL_FRAGMENT = 2;
    int LIST_FRAGMENT = 3;

    void showError(@ErrorMode int errorMode);
    void hideError();
    void changeFragment(@FragmentType int fragmentType);
}
