package com.blstream.stalker.controller.location;

import android.support.v4.app.Fragment;

/**
 *
 */
public abstract class DetectFragmentController {
    protected Fragment fragment;

    public DetectFragmentController(Fragment fragment){
        this.fragment = fragment;
    }
}
