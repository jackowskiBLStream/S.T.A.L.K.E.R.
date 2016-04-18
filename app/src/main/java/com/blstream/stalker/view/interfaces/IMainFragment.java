package com.blstream.stalker.view.interfaces;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import com.blstream.stalker.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Observer;

public interface IMainFragment {


    /**
     * Specifies three kinds of errors
     */

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({NO_INTERNET_CONNECTION_ERROR, NO_GPS_CONNECTION_ERROR, NO_INTERNET_AND_GPS_CONNECTION_ERROR})
    @interface ErrorMode {}
    int NO_INTERNET_CONNECTION_ERROR = R.string.no_internet_connection;
    int NO_GPS_CONNECTION_ERROR = R.string.no_gps_connection;
    int NO_INTERNET_AND_GPS_CONNECTION_ERROR = R.string.no_gps_and_internet_connection_error;

    /**
     * Specifies three kinds of fragments
     */
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LOGIN_FRAGMENT, DETAIL_FRAGMENT, LIST_FRAGMENT})
    @interface FragmentType {}
    int LOGIN_FRAGMENT = 1;
    int DETAIL_FRAGMENT = 2;
    int LIST_FRAGMENT = 3;

    /**
     *  Shows error selecgted in the argument
     * @param errorMode {@link com.blstream.stalker.view.interfaces.IMainFragment.ErrorMode}
     */
    void showError(@ErrorMode int errorMode);

    /**
     * hide shown error
     */
    void hideError();

    /**
     *  Changs fragment to a selected fragment in the argument
     * @param fragmentType {@link com.blstream.stalker.view.interfaces.IMainFragment.FragmentType}
     */
    void changeFragment(@FragmentType int fragmentType);


}
