package com.blstream.stalker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.view.View;

import com.blstream.stalker.R;
import com.blstream.stalker.view.interfaces.ILoginFragment;
import com.google.android.gms.common.SignInButton;


public class LoginScreenFragment extends Fragment implements ILoginFragment {
    SignInButton signInButton ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.login_screen_layout,container,false);
        signInButton = (SignInButton) view.findViewById(R.id.sign_in_button);
        customizeSignInButton();
        return view;
    }

    @Override
    public void showError(@ErrorMode int errorMode) {
        switch (errorMode){
            case  NO_GPS_CONNECTION_ERROR :{
                break;
            }
            case  NO_INTERNET_CONNECTION_ERROR :{
                break;
            }
            case  NO_INTERNET_AND_GPS_CONNECTION_ERROR :{
                break;
            }
            default:{
                break;
            }
        }
    }

    @Override
    public void changeFragment(@FragmentType int fragmentType) {

    }

    private void customizeSignInButton(){
        signInButton.setColorScheme(SignInButton.COLOR_AUTO);
    }
}
