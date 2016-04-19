package com.blstream.stalker.view.fragments;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.blstream.stalker.R;
import com.blstream.stalker.controller.DatabaseController;
import com.blstream.stalker.controller.database.DatabaseContract;
import com.blstream.stalker.controller.location.DetectActivityController;
import com.blstream.stalker.controller.location.LocationController;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.view.MyContentObserver;
import com.blstream.stalker.view.abstractClass.BasicView;
import com.blstream.stalker.view.adapters.PlaceListAdapter;
import com.blstream.stalker.view.interfaces.ContentObserverCallback;
import com.blstream.stalker.view.interfaces.IPlaceListView;
import java.util.ArrayList;
import java.util.List;

public class PlaceListView extends BasicView implements IPlaceListView, ContentObserverCallback {
    private LocationController locationController;
    private DetectActivityController detectActivityController;
    private PlaceListAdapter adapter;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private MyContentObserver myContentObserver;
    private DatabaseController databaseController;
    private DetailItemView detailItemView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.place_list_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new PlaceListAdapter(getContext());
        initialControllers();
        initialRecyclerView(view);
        updateList(new ArrayList<PlaceData>());
        initialOnItemClickListener();
        initialScreenLayout();
    }



    @Override
    public void onStart() {
        locationController.onStart();
        detectActivityController.onStart();
        super.onStart();
    }

    @Override
    public void onStop() {
        locationController.onStop();
        detectActivityController.onStop();
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        locationController.onResume();
        detectActivityController.onResume();
        if (myContentObserver == null) {
            myContentObserver = new MyContentObserver(this);
        }
        getActivity().getContentResolver()
                .registerContentObserver(
                        DatabaseContract.URI_PLACES,
                        true,
                        myContentObserver);
    }

    @Override
    public void onPause() {
        super.onPause();
        locationController.onPause();
        detectActivityController.onPause();
        getActivity().getContentResolver().unregisterContentObserver(myContentObserver);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void updateList(List<PlaceData> placeDataList) {
        adapter.setPlaceDataList(placeDataList);
    }

    /**
     * this is a callback method for a content observer
     * runs NOT in UI thread
     */
    @Override
    public void updateAfterDatabaseChanges() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO add here any needed logic
                updateList(databaseController.getAllPlacesData());
            }
        });
    }

    private void initialControllers() {
        databaseController = new DatabaseController(getContext());
        locationController = new LocationController(this);
        detectActivityController = new DetectActivityController(this);
    }

    private void initialRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.allTasks);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initialOnItemClickListener() {
        PlaceListAdapter.OnItemClickListener onItemClickListener = new PlaceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                detailItemView = new DetailItemView();
                createBundleForDetailsFragment(detailItemView, position);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, detailItemView).addToBackStack("")
                        .commit();
            }
        };
        adapter.setOnItemClickListener(onItemClickListener);
    }

    private void initialScreenLayout() {
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        if (size.x > size.y) {
            mStaggeredLayoutManager.setSpanCount(2);
        } else {
            mStaggeredLayoutManager.setSpanCount(1);
        }
    }

    private void createBundleForDetailsFragment(DetailItemView detailItemView, int position) {
        List<PlaceData> placeDataList = adapter.getPlaceDataList();
        Bundle bundle = new Bundle();
        bundle.putString(DetailItemView.NAME_BUNDLE_KEY, placeDataList.get(position).getName());
        bundle.putString(DetailItemView.TAGS_BUNDLE_KEY, placeDataList.get(position).getTypes());
        bundle.putString(DetailItemView.OPEN_HOURS_KEY, "11:00 - 22:00");
        detailItemView.setArguments(bundle);
    }
}

//    public void sendCameraResultToFragmentList(int requestCode, int responseCode, final int RESULT_OK) {
//        detailItemView.sendCameraResultToFragmentDetailFragment(requestCode, responseCode, RESULT_OK);
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

