package com.blstream.stalker.controller;

import com.blstream.stalker.view.fragments.LoginScreenFragment;
import com.blstream.stalker.view.interfaces.IMainFragment;

public class PlaceListController {

    private final LoginScreenFragment fragment;

    public PlaceListController(LoginScreenFragment fragmet){
        this.fragment = fragmet;
    }

    public void doSomething(){
        fragment.showError(IMainFragment.NO_INTERNET_CONNECTION_ERROR);
    }

}
