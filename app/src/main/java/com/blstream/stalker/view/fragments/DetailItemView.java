package com.blstream.stalker.view.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blstream.stalker.R;

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

        setTextToViewsFromBundle();
    }

    private void setTextToViewsFromBundle(){
        Bundle bundle = getArguments();
        nameTextView.setText(bundle.getString(NAME_BUNDLE_KEY));
        openHoursTextView.setText(bundle.getString(OPEN_HOURS_KEY));
        tagsTextView.setText(bundle.getString(TAGS_BUNDLE_KEY));
    }
}