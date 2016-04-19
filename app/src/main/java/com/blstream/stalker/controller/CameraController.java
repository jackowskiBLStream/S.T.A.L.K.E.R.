package com.blstream.stalker.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.blstream.stalker.Constants;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraController {
    private GoogleApiClient googleApiClient;
    Intent cameraIntent;
    Fragment fragment;
    private Uri fileUri;

    public CameraController(Fragment fragment, GoogleApiClient googleApiClient) {
        this.cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        this.fragment = fragment;
        this.googleApiClient = googleApiClient;
    }

    /**
     * Create a File for saving an image or video
     */
    private static Uri getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
        return Uri.fromFile(mediaFile);
    }

    public void onCameraClick() {
        fileUri = getOutputMediaFile();
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
        fragment.startActivityForResult(cameraIntent, Constants.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public void sendCameraResultToController(int requestCode, int responseCode, final int RESULT_OK) {
        if (requestCode == Constants.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && responseCode == RESULT_OK) {
            // Image captured and saved to fileUri specified in the Intent
            Toast.makeText(fragment.getContext(), "Photo taken", Toast.LENGTH_LONG).show();
        }
    }
}
