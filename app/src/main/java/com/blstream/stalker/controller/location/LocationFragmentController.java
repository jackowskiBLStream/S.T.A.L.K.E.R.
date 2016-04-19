package com.blstream.stalker.controller.location;

import android.support.v4.app.Fragment;

import com.blstream.stalker.view.interfaces.IPlaceListView;

/**
 *
 *
 */
public abstract class LocationFragmentController<T extends IPlaceListView> {

    protected Fragment fragment;
    protected T view;

    public LocationFragmentController(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setView(T view) {
        this.view = view;
    }


}
