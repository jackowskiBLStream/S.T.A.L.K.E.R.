package com.blstream.stalker.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blstream.stalker.R;
import com.blstream.stalker.view.abstractClass.AbstractErrorClass;
import com.blstream.stalker.view.interfaces.IPlaceListFragment;

public class PlaceListFragment extends AbstractErrorClass implements IPlaceListFragment {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void uploadList() {

    }
    @Override
    public void changeFragment(@FragmentType int fragmentType) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (fragmentType) {
            case LIST_FRAGMENT:
                break;
            case DETAIL_FRAGMENT:
                fragmentManager.beginTransaction().replace(R.id.mainContainer, new DetailListFragment()).commit();
                break;
            case LOGIN_FRAGMENT:
                break;
            default:
                break;
        }
    }
}
