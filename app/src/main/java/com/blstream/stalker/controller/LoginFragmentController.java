package com.blstream.stalker.controller;

import android.support.v4.app.Fragment;

import com.blstream.stalker.view.abstractClass.AbstractErrorClass;
import com.blstream.stalker.view.interfaces.IMainFragment;

public abstract class LoginFragmentController<T extends IMainFragment>  {
    protected Fragment fragment;
    protected T view;

    public LoginFragmentController(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setView(T view) {
        this.view = view;
    }
}
