package com.blstream.stalker.view.abstractClass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blstream.stalker.R;
import com.blstream.stalker.view.fragments.DetailItemFragment;
import com.blstream.stalker.view.fragments.ErrorMessageFragment;
import com.blstream.stalker.view.fragments.LoginScreenFragment;
import com.blstream.stalker.view.fragments.PlaceListFragment;
import com.blstream.stalker.view.interfaces.IMainFragment;

public abstract class AbstracFragmentClass extends Fragment implements IMainFragment {

    ErrorMessageFragment errorFragment = new ErrorMessageFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void showError(@ErrorMode int errorMode) {
        switch (errorMode) {
            case NO_GPS_CONNECTION_ERROR: {
                errorFragment.setErrorMessage(getString(NO_GPS_CONNECTION_ERROR));
                break;
            }
            case NO_INTERNET_CONNECTION_ERROR: {
                errorFragment.setErrorMessage(getString(NO_INTERNET_CONNECTION_ERROR));
                break;
            }
            case NO_INTERNET_AND_GPS_CONNECTION_ERROR: {
                errorFragment.setErrorMessage(getString(NO_INTERNET_AND_GPS_CONNECTION_ERROR));
                break;
            }
            default: {
                break;
            }
        }
        FragmentManager fragmentManager = getFragmentManager();

        if (!errorFragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                    .add(R.id.mainContainer, errorFragment).commit();
        }
    }

    @Override
    public void hideError() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .remove(errorFragment).commit();
    }

    @Override
    public void changeFragment(@FragmentType int fragmentType) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (fragmentType) {
            case LIST_FRAGMENT:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.enter, R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                        .replace(R.id.mainContainer, new PlaceListFragment())
                        .addToBackStack("").commit();
                break;
            case DETAIL_FRAGMENT:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.enter, R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                        .replace(R.id.mainContainer, new DetailItemFragment())
                        .addToBackStack("").commit();
                break;
            case LOGIN_FRAGMENT:
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                        .replace(R.id.mainContainer, new LoginScreenFragment())
                        .addToBackStack("").commit();
                break;
            default:
                break;
        }
    }
}
