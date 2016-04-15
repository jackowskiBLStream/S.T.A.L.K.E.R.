package com.blstream.stalker.view.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.blstream.stalker.R;
import com.blstream.stalker.controller.DatabaseController;
import com.blstream.stalker.controller.LoginScreenFragmetController;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataDetails;
import com.blstream.stalker.model.PlaceDataWithDetails;
import com.blstream.stalker.view.abstractClass.AbstractErrorClass;
import com.blstream.stalker.view.interfaces.ILoginFragment;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;


public class LoginScreenFragment extends AbstractErrorClass implements ILoginFragment {
    SignInButton signInButton;
    Button noThanksButton;
    LoginScreenFragmetController loginScreenController;

    DatabaseController db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseController(getContext());
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.login_screen_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signInButton = (SignInButton) view.findViewById(R.id.sign_in_button);
        noThanksButton = (Button) view.findViewById(R.id.no_thanks_button);
        initLoginController();
        customizeButtons();

    }

    private void initLoginController() {
        loginScreenController = new LoginScreenFragmetController(this);
        loginScreenController.setView(this);
    }

    /**
     * Replace Fragments for a specified fragment in fragmentType;
     *
     * @param fragmentType type of Fragment {LIST_FRAGMENT,DETAIL_FRAGMENT,LOGIN_FRAGMENT}
     */
    @Override
    public void changeFragment(@FragmentType int fragmentType) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (fragmentType) {
            case DETAIL_FRAGMENT:
                break;
            case LOGIN_FRAGMENT:
                break;
            case LIST_FRAGMENT:
            default:
                fragmentManager.beginTransaction().replace(R.id.mainContainer, new PlaceListFragment()).commit();
                break;
        }
    }

    private void customizeButtons() {
        signInButton.setColorScheme(SignInButton.COLOR_AUTO);
        PlaceData placeData = new PlaceData(null, "types", null, "jeden", new Location(""));
        PlaceDataDetails placeDataDetails = new PlaceDataDetails(null, 5, null);
        final PlaceDataWithDetails placeDataWithDetails = new PlaceDataWithDetails(placeData, placeDataDetails);
        final ArrayList<PlaceDataWithDetails> list = new ArrayList<>();
        list.add(placeDataWithDetails);
        signInButton.setOnClickListener(new View.OnClickListener() {
            /**
             *{@inheritDoc}
             */
            @Override
            public void onClick(View v) {
                loginScreenController.googlePlusLogin();
            }
        });
        noThanksButton.setOnClickListener(new View.OnClickListener() {
            /**
             *{@inheritDoc}
             *
             */
            @Override
            public void onClick(View v) {
                loginScreenController.runWithoutLogin();
                hideError();
            }
        });
    }

    public void sentLoginResultToFragment(int requestCode, int responseCode, final int RESULT_OK) {
        loginScreenController.sentLoginResultToController(requestCode, responseCode, RESULT_OK);
    }
}
