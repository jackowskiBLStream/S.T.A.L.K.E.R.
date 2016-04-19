package com.blstream.stalker.view.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blstream.stalker.BaseActivity;
import com.blstream.stalker.Constants;
import com.blstream.stalker.R;
import com.blstream.stalker.controller.CameraController;
import com.blstream.stalker.controller.GoogleDriveController;
import com.blstream.stalker.view.abstractClass.BasicView;
import com.blstream.stalker.view.interfaces.ILoginView;
import com.google.android.gms.common.api.GoogleApiClient;

public class DetailItemView extends BasicView {

    public final static String POSITION_BUNDLE_KEY = "PositionKey";
    public final static String IMAGE_BUNDLE_KEY = "imageKEY";
    public final static String NAME_BUNDLE_KEY = "NameKey";
    public final static String TAGS_BUNDLE_KEY = "TagsKey";
    public static final String OPEN_HOURS_KEY = "OpenHoursKey";
    public static final String NAME_TRANSACTION_NAME = "nameTransaction";
    public static final String IMAGE_TRANSACTION_NAME = "imageTransaction";
    public static final String OPEN_HOURS_TRANSACTION_NAME = "openHoursTransaction";
    public static final String TAGS_TRANSACTION_NAME = "tagsTransaction";
    private TextView nameTextView;
    private TextView openHoursTextView;
    private TextView tagsTextView;
    private CameraController cameraController;
    private GoogleDriveController googleDriveController;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cameraController.onCameraClick();
        }
    };
    private ImageButton cameraButton;
    private GoogleApiClient googleApiClient;
    private Bitmap bitmapToSave = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.detail_item_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialViewItems(view);
        googleApiClient = ((BaseActivity) getActivity()).getGoogleApiClient();
        cameraController = new CameraController(this, googleApiClient);
        googleDriveController = new GoogleDriveController(this, googleApiClient);
        cameraButton = (ImageButton) view.findViewById(R.id.imageButton);
        cameraButton.setOnClickListener(onClickListener);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initialViewItems(View view) {
        nameTextView = (TextView) view.findViewById(R.id.detailNameTextView);
        openHoursTextView = (TextView) view.findViewById(R.id.detailsOpenHoursTextView);
        tagsTextView = (TextView) view.findViewById(R.id.detailsTagsTextView);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            nameTextView.setTransitionName(NAME_TRANSACTION_NAME);
            openHoursTextView.setTransitionName(OPEN_HOURS_TRANSACTION_NAME);
            tagsTextView.setTransitionName(TAGS_TRANSACTION_NAME);
        }
//        setTextToViewsFromBundle();
    }

    private void setTextToViewsFromBundle() {
        Bundle bundle = getArguments();
        nameTextView.setText(bundle.getString(NAME_BUNDLE_KEY));
        openHoursTextView.setText(bundle.getString(OPEN_HOURS_KEY));
        tagsTextView.setText(bundle.getString(TAGS_BUNDLE_KEY));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.REQUEST_CODE_CAPTURE_IMAGE) {
//            cameraController.sendCameraResultToController(requestCode, resultCode, Activity.RESULT_OK);
            if (resultCode == Activity.RESULT_OK) {
                // Store the image data as a bitmap for writing later.
                bitmapToSave = (Bitmap) data.getExtras().get("data");
                googleDriveController.saveFileToDrive(bitmapToSave);
            }
        }
//        } else if (requestCode == Constants.REQUEST_CODE_CREATOR) {
//            if (resultCode == Activity.RESULT_OK) {
//                // Just start the camera again for another photo.
//                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE),
//                        Constants.REQUEST_CODE_CAPTURE_IMAGE);
//            }
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startIntentSenderForResultFromController(IntentSender intentSender, int requestCode, Intent fillInIntent, int flagsMask, int flagsValue, int extraFlags) {
        try {
            getActivity().startIntentSenderForResult(intentSender, requestCode, fillInIntent, flagsMask, flagsValue, extraFlags);
        } catch (IntentSender.SendIntentException e) {
            Toast.makeText(getContext(), "Failed to launch file chooser.", Toast.LENGTH_SHORT).show();
        }
    }
}
