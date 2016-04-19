package com.blstream.stalker.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.blstream.stalker.BaseActivity;
import com.blstream.stalker.Constants;
import com.blstream.stalker.view.fragments.DetailItemView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.MetadataChangeSet;

public class GoogleDriveController extends FragmentController<DetailItemView>{

private GoogleApiClient googleApiClient;

    private IntentSender intentSender;

    public GoogleDriveController(Fragment fragment, GoogleApiClient googleApiClient) {
        super(fragment);
        this.googleApiClient = googleApiClient;
    }

   public void saveFileToDrive(Bitmap bitmapToSave) {
        // Start by creating a new contents, and setting a callback.

        final Bitmap image = bitmapToSave;
        Drive.DriveApi.newDriveContents(googleApiClient)
                .setResultCallback(new ResultCallback<DriveApi.DriveContentsResult>() {

                    @Override
                    public void onResult(DriveApi.DriveContentsResult result) {
                        // If the operation was not successful, we cannot do anything
                        // and must fail
                        if (!result.getStatus().isSuccess()) {
                            Toast.makeText(fragment.getContext(), "Failed to create new contents.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // Otherwise, we can write our data to the new contents.
                        // Get an output stream for the contents.
                        OutputStream outputStream = result.getDriveContents().getOutputStream();
                        // Write the bitmap data from it.
                        ByteArrayOutputStream bitmapStream = new ByteArrayOutputStream();
                        image.compress(Bitmap.CompressFormat.PNG, 100, bitmapStream);
                        try {
                            outputStream.write(bitmapStream.toByteArray());
                        } catch (IOException e1) {
                            Toast.makeText(fragment.getContext(), "Unable to write file contents.", Toast.LENGTH_SHORT).show();
                        }
                        // Create the initial metadata - MIME type and title.
                        // Note that the user will be able to change the title later.
                        MetadataChangeSet metadataChangeSet = new MetadataChangeSet.Builder()
                                .setMimeType("image/jpeg").setTitle("Android Photo.png").build();
                        // Create an intent for the file chooser, and start it.
                        intentSender = Drive.DriveApi
                                .newCreateFileActivityBuilder()
                                .setInitialMetadata(metadataChangeSet)
                                .setInitialDriveContents(result.getDriveContents())
                                .build(googleApiClient);
                        view.startIntentSenderForResultFromController(
                                intentSender, Constants.REQUEST_CODE_CREATOR, null, 0, 0, 0);
                    }
                });
    }
}
