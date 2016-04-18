package com.blstream.stalker.controller;

import com.blstream.stalker.view.fragments.LoginScreenView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginScreenControllerTest {

    LoginScreenController loginScreenController;
    LoginScreenView loginScreenView;

    @Before
    public void setUp() throws Exception {
//        loginScreenFragment = new LoginScreenView();
//        loginScreenController = new LoginScreenController(loginScreenFragment);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGooglePlusLoginWhenSuccess() throws Exception {
        // given
        loginScreenFragment = new LoginScreenView();
        loginScreenController = new LoginScreenController(loginScreenFragment);
        // when
        loginScreenController.googlePlusLogin();
        boolean isConnected = loginScreenController.isLoggedIn();
        // then
        assertTrue(isConnected);
    }

    @Test
    public void testRunWithoutLogin() throws Exception {
        // given
        loginScreenFragment = new LoginScreenView();
        loginScreenController = new LoginScreenController(loginScreenFragment);
        // when
        loginScreenController.runWithoutLogin();
        boolean isConnected = loginScreenController.isLoggedIn();
        // then
        assertFalse(isConnected);
    }
}