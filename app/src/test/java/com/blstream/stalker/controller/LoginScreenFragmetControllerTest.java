package com.blstream.stalker.controller;

import android.app.Activity;
import android.app.Fragment;

import com.blstream.stalker.view.interfaces.ILoginFragment;
import com.blstream.stalker.view.interfaces.IMainFragment;
import com.google.android.gms.common.api.GoogleApiClient;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginScreenFragmetControllerTest {

    @Mock
    Fragment fragment;

    @Mock
    ILoginFragment view;

    @Mock
    Activity activity;

    @Mock
    GoogleApiClient client;

    LoginScreenFragmetController controller;


    @Before
    public void setUp() {
        //init mocks
        //when fragment.getActivity() then return mock activity
        controller = new LoginScreenFragmetController(fragment);
        controller.setView(view);
        controller.setGACLient(client);

    }


    @Test
    public void shouldruntwithoutlogin() {

        //given

        //when
        controller.runWithoutLogin();

        //then
//        verify view.changeFragment(IMainFragment.LIST_FRAGMENT) called

    }

    @Test
    public void shouldretruntTrueWhenUserIsLoggedIn() {

        //given
//        when client.isConnected() then return true

        //when
        boolean loggedIn = controller.isLoggedIn();

        //then
        assertTrue(loggedIn);

    }

    @Test
    public void shouldSentLogin() {

        //given
        int requestCode= 0;
        int result= 0;
        //when client.isConnecting() then return false

        //when
        controller.sentLoginResultToController(requestCode, requestCode, 0);

        //then
        //verify client.connect();

    }


    public void shouldSentLogin2() {

        //given
        int requestCode= 0;
        int result= 0;
        //when client.isConnecting() then return true

        //when
        controller.sentLoginResultToController(requestCode, requestCode, 0);

        //then
        //verify client.connect() never called

    }

}