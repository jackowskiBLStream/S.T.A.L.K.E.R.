package com.blstream.stalker.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blstream.stalker.R;
import com.blstream.stalker.controller.location.DetectActivityController;
import com.blstream.stalker.controller.location.LocationController;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.view.abstractClass.AbstractErrorClass;
import com.blstream.stalker.view.adapters.PlaceListAdapter;
import com.blstream.stalker.view.interfaces.IPlaceListFragment;

import java.util.List;

public class PlaceListFragment extends AbstractErrorClass implements IPlaceListFragment {

    private View view;
    private PlaceListAdapter adapter = new PlaceListAdapter();
    private LocationController locationController;
    private DetectActivityController detectActivityController;
    PlacesListFragmentController {

        observe content change w bazie danych
                // -> pobrac content
                // -> updateview with places list

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.place_list_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.allTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        locationController = new LocationController(this);
        detectActivityController = new DetectActivityController(this);
        return view;
    }

    @Override
    public void uploadList(List<PlaceData> placeDataList) {
        adapter.setPlaceDataList(placeDataList);
    }

    //FIXME: przeniesc do klasy bazowej zeby nie duplikowac kodu (generic)
    @Override
    public void changeFragment(@FragmentType int fragmentType) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (fragmentType) {
            case LIST_FRAGMENT:
                break;
            case DETAIL_FRAGMENT:
                fragmentManager.beginTransaction().replace(R.id.mainContainer, new DetailListFragment()).commit();
                break;
            case LOGIN_FRAGMENT:
                break;
            default:
                break;
        }
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
    }

    @Override
    public void onPause() {
        super.onPause();
        locationController.onPause();
        detectActivityController.onPause();
    }
}
