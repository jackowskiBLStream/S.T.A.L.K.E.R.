package com.blstream.stalker.controller;

import com.blstream.stalker.view.fragments.LoginScreenView;
import com.blstream.stalker.view.interfaces.IBasicView;

public class PlaceListController {

    private final LoginScreenView fragment;

    public PlaceListController(LoginScreenView fragmet){
        this.fragment = fragmet;
    }

    public void doSomething(){
        fragment.showError(IBasicView.NO_INTERNET_CONNECTION_ERROR);
    }
}
