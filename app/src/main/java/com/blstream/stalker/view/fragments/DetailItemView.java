package com.blstream.stalker.view.fragments;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blstream.stalker.BaseActivity;
import com.blstream.stalker.Constants;
import com.blstream.stalker.R;
import com.blstream.stalker.controller.CameraController;
import com.google.android.gms.common.api.GoogleApiClient;

public class DetailItemView extends Fragment {

    public final static String POSITION_BUNDLE_KEY = "PositionKey";
    public final static String IMAGE_BUNDLE_KEY = "imageKEY";
    public final static String NAME_BUNDLE_KEY = "NameKey";
    public final static String TAGS_BUNDLE_KEY = "TagsKey";
    public static final String OPEN_HOURS_KEY = "OpenHoursKey";
    public static final String NAME_TRANSACTION_NAME ="nameTransaction";
    public static final String IMAGE_TRANSACTION_NAME ="imageTransaction";
    public static final String OPEN_HOURS_TRANSACTION_NAME ="openHoursTransaction";
    public static final String TAGS_TRANSACTION_NAME ="tagsTransaction";
    private TextView nameTextView;
    private TextView openHoursTextView;
    private TextView tagsTextView;
    private CameraController cameraController;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cameraController.onCameraClick();
        }
    };
    private ImageButton cameraButton;
    private GoogleApiClient googleApiClient;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        googleApiClient = ((BaseActivity)getActivity()).getGoogleApiClient();
        cameraController = new CameraController(this, googleApiClient);
        return inflater.inflate(R.layout.detail_item_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialViewItems(view);
        cameraButton = (ImageButton) view.findViewById(R.id.imageButton);
        cameraButton.setOnClickListener(onClickListener);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initialViewItems(View view){
        nameTextView = (TextView)view.findViewById(R.id.detailNameTextView);
        openHoursTextView = (TextView)view.findViewById(R.id.detailsOpenHoursTextView);
        tagsTextView = (TextView)view.findViewById(R.id.detailsTagsTextView);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            nameTextView.setTransitionName(NAME_TRANSACTION_NAME);
            openHoursTextView.setTransitionName(OPEN_HOURS_TRANSACTION_NAME);
            tagsTextView.setTransitionName(TAGS_TRANSACTION_NAME);
        }
//        setTextToViewsFromBundle();
    }

    private void setTextToViewsFromBundle(){
        Bundle bundle = getArguments();
        nameTextView.setText(bundle.getString(NAME_BUNDLE_KEY));
        openHoursTextView.setText(bundle.getString(OPEN_HOURS_KEY));
        tagsTextView.setText(bundle.getString(TAGS_BUNDLE_KEY));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            cameraController.sendCameraResultToController(requestCode, resultCode, getActivity().RESULT_OK);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

//    public void sendCameraResultToFragmentDetailFragment(int requestCode, int responseCode, final int RESULT_OK) {
//        cameraController.sendCameraResultToController(requestCode, responseCode, RESULT_OK);
//    }
}
