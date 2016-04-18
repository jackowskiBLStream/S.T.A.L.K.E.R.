package com.blstream.stalker.controller.interfaces;

public interface ILoginScreenController {

    /**
     * Method signs in user with google plus account.
     * Called after click on button "Login" from LoginScreenFragment.
     */
    void googlePlusLogin();

    /**
     * Method changes fragment to fragment with lists of places.
     * Called when button "No Thanks" clicked from LoginScreenFragment.
     */
    void runWithoutLogin();

    /**
     * Method handles the results from onActivityResult callback.
     *
     * @param requestCode  int
     * @param responseCode int
     * @param RESULT_OK    int
     */
    void sendLoginResultToController(int requestCode, int responseCode, final int RESULT_OK);
}
