package com.blstream.stalker.controller;


import android.support.v4.app.Fragment;

import com.blstream.stalker.view.interfaces.IMainFragment;

public abstract class FragmentController<T extends IMainFragment> {

    protected Fragment fragment;
    protected T view;

    public FragmentController(Fragment fragment) {
        this.fragment = fragment;
    }


    public void setView(T view) {
        this.view = view;
    }

}
