package com.blstream.stalker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.blstream.stalker.controller.LoginScreenController;
import com.blstream.stalker.view.fragments.LoginScreenFragment;


public abstract class BaseActivity extends AppCompatActivity {
    LoginScreenFragment loginScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginScreenFragment = new LoginScreenFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.mainContainer, loginScreenFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LoginScreenController.RC_SIGN_IN) {
            loginScreenFragment.sentLoginResultToFragment(requestCode, resultCode, RESULT_OK);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

///**
// * Activity to demonstrate basic retrieval of the Google user's ID, email address, and basic
// * profile.
// */
//
//public class BaseActivity extends AppCompatActivity implements OnClickListener,
//        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
//
//    private static final String TAG = "SignInActivity";
//    private static final int RC_SIGN_IN = 0;
//
//    // Profile pic image size in pixels
//    private static final int PROFILE_PIC_SIZE = 400;
//    SignInButton signInButton;
//    Button signOutButton;
//    //    Button disconnectButton;
//    private boolean mIntentInProgress;
//    private boolean signedInUser;
//    private ConnectionResult mConnectionResult;
//    private GoogleApiClient mGoogleApiClient;
//    private ImageView imgProfilePic;
//    private TextView txtName, txtEmail;
//    private LinearLayout llProfileLayout;
//    private TextView mStatusTextView;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
//
////        // Views
//        txtName = (TextView) findViewById(R.id.txtName);
//        txtEmail = (TextView) findViewById(R.id.txtEmail);
//        llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
//        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
//        mStatusTextView = (TextView) findViewById(R.id.status);
//        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
//        signOutButton = (Button) findViewById(R.id.sign_out_button);
////        disconnectButton = (Button) findViewById(R.id.disconnect_button);
//        // Button listeners
//        signInButton.setOnClickListener(this);
//        signOutButton.setOnClickListener(this);
////        disconnectButton.setOnClickListener(this);
//
//        // Initializing google plus api client
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this).addApi(Plus.API)
//                .addScope(Plus.SCOPE_PLUS_LOGIN).build();
//
//
//    }
//
//    public void onResume() {
//        super.onResume();
//        mGoogleApiClient.connect();
//    }
//
//    public void onPause() {
//        super.onPause();
//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
//    }
//
//    /**
//     * Sign-in into google
//     */
//    private void signIn() {
////        if (!mGoogleApiClient.isConnecting()) {
////            signedInUser = true;
////            resolveSignInError();
////        }
//        googlePlusLogin();
//    }
//
//    /**
//     * Method to resolve any signin errors
//     */
//    private void resolveSignInError() {
//        try {
//            boolean resolution = mConnectionResult.hasResolution();
//            if (resolution) {
//                mIntentInProgress = true;
//                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
//            }
//        } catch (IntentSender.SendIntentException | NullPointerException e) {
//            mIntentInProgress = false;
//            mGoogleApiClient.connect();
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        Log.d(TAG, "onClick: " + v.getId());
//        switch (v.getId()) {
//            case R.id.sign_in_button:
//                signIn();
//                break;
//            case R.id.sign_out_button:
//                signOut();
//                break;
////            case R.id.disconnect_button:
//////                revokeAccess();
////                break;
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//        if (!result.hasResolution()) {
//            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
//                    0).show();
//            return;
//        }
//
//        if (!mIntentInProgress) {
//            // Store the ConnectionResult for later usage
//            mConnectionResult = result;
//
//            if (signedInUser) {
//                // The user has already clicked 'sign-in' so we attempt to
//                // resolve all
//                // errors until the user is signed in, or they cancel.
//                resolveSignInError();
//            }
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
//        if (requestCode == RC_SIGN_IN) {
//            if (responseCode == RESULT_OK) {
//                signedInUser = false;
//            }
//            mIntentInProgress = false;
//            if (!mGoogleApiClient.isConnecting()) {
//                mGoogleApiClient.connect();
//            }
//        }
//    }
//
//    @Override
//    public void onConnected(Bundle arg0) {
//        signedInUser = false;
////        Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
//        // Get user's information
//        getProfileInformation();
//        // Update the UI after signin
//        updateUI(true);
//    }
//
//    @Override
//    public void onConnectionSuspended(int arg0) {
//        mGoogleApiClient.connect();
//        updateUI(false);
//    }
//
//    /**
//     * Updating the UI, showing/hiding buttons and profile layout
//     */
//    private void updateUI(boolean isSignedIn) {
//        if (isSignedIn) {
//            signInButton.setVisibility(View.GONE);
//            signOutButton.setVisibility(View.VISIBLE);
////            disconnectButton.setVisibility(View.VISIBLE);
//            llProfileLayout.setVisibility(View.VISIBLE);
//        } else {
//            signInButton.setVisibility(View.VISIBLE);
//            signOutButton.setVisibility(View.GONE);
////            disconnectButton.setVisibility(View.GONE);
//            llProfileLayout.setVisibility(View.GONE);
//        }
//    }
//
//    /**
//     * Fetching user's information name, email, profile pic
//     */
//    private void getProfileInformation() {
////        Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
//        try {
//            if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
//                Person currentPerson = Plus.PeopleApi
//                        .getCurrentPerson(mGoogleApiClient);
//                String personName = currentPerson.getDisplayName();
//                String personPhotoUrl = currentPerson.getImage().getUrl();
//                String personGooglePlusProfile = currentPerson.getUrl();
//                String email = Plus.AccountApi.getAccountName(mGoogleApiClient);
//
////                Log.e(TAG, "Name: " + personName + ", plusProfile: "
////                        + personGooglePlusProfile + ", email: " + email
////                        + ", Image: " + personPhotoUrl);
//
//                txtName.setText(personName);
//                txtEmail.setText(email);
//
//                // by default the profile url gives 50x50 px image only
//                // we can replace the value with whatever dimension we want by
//                // replacing sz=X
//                personPhotoUrl = personPhotoUrl.substring(0,
//                        personPhotoUrl.length() - 2)
//                        + PROFILE_PIC_SIZE;
//
//                new LoadProfileImage(imgProfilePic).execute(personPhotoUrl);
//
//            }
////            else {
////                Toast.makeText(getApplicationContext(),
////                        "Person information is null", Toast.LENGTH_SHORT).show();
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void signOut() {
//        googlePlusLogout();
////        if (mGoogleApiClient.isConnected()) {
////            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
////            mGoogleApiClient.disconnect();
////            mGoogleApiClient.connect();
////            updateUI(false);
////        }
//    }
//
//
////    /**
////     * Revoking access from google
////     */
////    private void revokeAccess() {
////        if (mGoogleApiClient.isConnected()) {
////            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
////            Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient)
////                    .setResultCallback(new ResultCallback<Status>() {
////                        @Override
////                        public void onResult(Status arg0) {
////                            Log.e(TAG, "User access revoked!");
////                            mStatusTextView.setText(arg0.toString());
////                            mGoogleApiClient.connect();
////                            updateUI(false);
////                        }
////
////                    });
////        }
////    }
//
//    private void googlePlusLogin() {
//        if (!mGoogleApiClient.isConnecting()) {
//            signedInUser = true;
//            resolveSignInError();
//        }
//    }
//
//    private void googlePlusLogout() {
//        if (mGoogleApiClient.isConnected()) {
////            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
//            mGoogleApiClient.clearDefaultAccountAndReconnect();
////            mGoogleApiClient.disconnect();
////            mGoogleApiClient.connect();
//            updateUI(false);
////            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    /**
//     * Background Async task to load user profile picture from url
//     */
//    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;
//
//        public LoadProfileImage(ImageView bmImage) {
//            this.bmImage = bmImage;
//        }
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error", e.getMessage());
//                e.printStackTrace();
//            }
//            return mIcon11;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//        }
//    }
//}
