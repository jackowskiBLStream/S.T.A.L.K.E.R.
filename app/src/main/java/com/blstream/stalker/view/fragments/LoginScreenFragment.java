package com.blstream.stalker.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.blstream.stalker.R;
import com.blstream.stalker.controller.ImageController;
import com.blstream.stalker.controller.LoginScreenController;
import com.blstream.stalker.view.abstractClass.AbstractErrorClass;
import com.blstream.stalker.view.interfaces.ILoginFragment;
import com.google.android.gms.common.SignInButton;


public class LoginScreenFragment extends AbstractErrorClass implements ILoginFragment {
    SignInButton signInButton;
    Button noThanksButton;
    LoginScreenController controller;

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.login_screen_layout, container, false);
        signInButton = (SignInButton) view.findViewById(R.id.sign_in_button);
        noThanksButton = (Button) view.findViewById(R.id.no_thanks_button);

        ImageView back = (ImageView) view.findViewById(R.id.imageView);
        ImageController cont = new ImageController(getContext(), R.drawable.background_image, R.drawable.background_image);
        cont.getImage("http://oceanahmt.com/wp-content/uploads/2013/12/gitl.png",back);
        
        controller = new LoginScreenController(this);

        customizeButtons();
        return view;
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
            case LIST_FRAGMENT:
                fragmentManager.beginTransaction().replace(R.id.mainContainer, new PlaceListFragment()).commit();
                break;
            case DETAIL_FRAGMENT:
                break;
            case LOGIN_FRAGMENT:
                break;
            default:
                fragmentManager.beginTransaction().replace(R.id.mainContainer, new PlaceListFragment()).commit();
        }
    }

    private void customizeButtons() {
        signInButton.setColorScheme(SignInButton.COLOR_AUTO);
        signInButton.setOnClickListener(new View.OnClickListener() {
            /**
             *{@inheritDoc}
             */
            @Override
            public void onClick(View v) {

                controller.googlePlusLogin();
            }
        });
        noThanksButton.setOnClickListener(new View.OnClickListener() {
            /**
             *{@inheritDoc}
             *
             */
            @Override
            public void onClick(View v) {
                hideError();
            }
        });
    }

    public void sentLoginResultToFragment(int requestCode, int responseCode, final int RESULT_OK) {
        controller.sentLoginResultToController(requestCode, responseCode, RESULT_OK);
    }
}
