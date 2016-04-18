package com.blstream.stalker.controller;

import android.support.test.runner.AndroidJUnit4;

import com.blstream.stalker.view.fragments.LoginScreenView;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginScreenControllerTest extends TestCase {
    LoginScreenController loginScreenController;
    LoginScreenView loginScreenView;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        loginScreenView = new LoginScreenView();
        loginScreenController = new LoginScreenController(loginScreenView);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGooglePlusLoginWhenSuccess() throws Exception {
        // given
        // when
        loginScreenController.googlePlusLogin();
        boolean isConnected = loginScreenController.isLoggedIn();
        // then
        assertTrue(isConnected);
    }

    @Test
    public void testRunWithoutLogin() throws Exception {
        // given
        // when
        loginScreenController.runWithoutLogin();
        boolean isConnected = loginScreenController.isLoggedIn();
        // then
        assertFalse(isConnected);
    }

    @Test
    public void testIsLoggedIn() throws Exception {
        // given
        // when
        boolean isConnected = loginScreenController.isLoggedIn();
        // then
        assertFalse(isConnected);
    }
}