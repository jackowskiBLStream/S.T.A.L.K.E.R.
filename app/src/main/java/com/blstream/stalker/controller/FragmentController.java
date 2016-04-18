package com.blstream.stalker.controller;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.blstream.stalker.controller.internetConnection.InternetConnectionListener;
import com.blstream.stalker.controller.internetConnection.InternetConnectionObserver;
import com.blstream.stalker.view.interfaces.IMainFragment;

import java.util.Observable;
import java.util.Observer;

public abstract class FragmentController<T extends IMainFragment> implements Observer {

    protected Fragment fragment;
    protected T view;

    public FragmentController(Fragment fragment) {
        this.fragment = fragment;
        InternetConnectionObserver observer = InternetConnectionObserver.getInstance();
        observer.addObserver(this);
    }

    public void setView(T view) {
        this.view = view;
    }

    @Override
    public void update(Observable observable, Object data) {
        if(InternetConnectionListener.isOnline){
            view.hideError();
        }else{
            view.showError(IMainFragment.NO_INTERNET_CONNECTION_ERROR);
        }
    }
}