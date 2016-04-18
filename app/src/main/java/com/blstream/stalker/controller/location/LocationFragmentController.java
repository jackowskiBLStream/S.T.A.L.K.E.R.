package com.blstream.stalker.controller.location;

import android.support.v4.app.Fragment;

import com.blstream.stalker.view.interfaces.IPlaceListFragment;

/**
 *
 *
 */
public abstract class LocationFragmentController<T extends IPlaceListFragment> {

    protected Fragment fragment;
    protected T view;

    public LocationFragmentController(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setView(T view) {
        this.view = view;
    }


}
