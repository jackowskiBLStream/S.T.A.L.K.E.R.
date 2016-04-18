package com.blstream.stalker.view.fragments;

import android.graphics.Point;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blstream.stalker.R;

import com.blstream.stalker.controller.location.DetectActivityController;
import com.blstream.stalker.controller.location.LocationController;

import com.blstream.stalker.model.OpenHours;

import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.view.abstractClass.AbstractErrorClass;
import com.blstream.stalker.view.adapters.PlaceListAdapter;
import com.blstream.stalker.view.interfaces.IPlaceListFragment;

import java.util.ArrayList;
import java.util.List;

public class PlaceListFragment extends AbstractErrorClass implements IPlaceListFragment {
    private final static String ADAPTER_PLACE_LIST = "AdapterPlaceList";
    private View view;
    private PlaceListAdapter adapter;
    private LocationController locationController;
    private DetectActivityController detectActivityController;


    private StaggeredGridLayoutManager mStaggeredLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if(savedInstanceState!= null){
            ArrayList<PlaceData> placeDataList = savedInstanceState.getParcelableArrayList(ADAPTER_PLACE_LIST);
            adapter.setPlaceDataList(placeDataList);
        }
        else{
            adapter = new PlaceListAdapter();
        }
        view = inflater.inflate(R.layout.place_list_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.allTasks);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setAdapter(adapter);
        locationController = new LocationController(this);
        detectActivityController = new DetectActivityController(this);
        locationController.setView(this);
        uploadList(new ArrayList<PlaceData>());
        initialOnItemClickListener();
        initialScreenLayout();
        return view;
    }


    public void uploadList(List<PlaceData> placeDataList){
        adapter.setPlaceDataList(placeDataList);
    }

    @Override
    public void changeFragment(@FragmentType int fragmentType) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (fragmentType) {
            case LIST_FRAGMENT:
                break;
            case DETAIL_FRAGMENT:
                fragmentManager.beginTransaction().replace(R.id.mainContainer, new DetailItemFragment()).commit();
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
    private void initialOnItemClickListener(){
        PlaceListAdapter.OnItemClickListener onItemClickListener = new PlaceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                DetailItemFragment detailItemFragment = new DetailItemFragment();
                createBundleForDetailsFragment(detailItemFragment,position);
                createSharedElementTransaction(detailItemFragment);
                getFragmentManager().beginTransaction()
                        .replace(R.id.mainContainer, detailItemFragment).addToBackStack("")
                        .commit();
            }
        };
        adapter.setOnItemClickListener(onItemClickListener);
    }
    private void initialScreenLayout(){
        Point size = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        if(size.x > size.y){
            mStaggeredLayoutManager.setSpanCount(2);
        }else{
            mStaggeredLayoutManager.setSpanCount(1);
        }
    }
    private void createSharedElementTransaction(DetailItemFragment fragment){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementReturnTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image_trans));
            setExitTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(android.R.transition.fade));

            fragment.setSharedElementEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image_trans));
            fragment.setEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(android.R.transition.fade));
        }
    }

    private void createBundleForDetailsFragment(DetailItemFragment detailItemFragment,int position){

        List<PlaceData> placeDataList = adapter.getPlaceDataList();
        Bundle bundle = new Bundle();
        bundle.putString(DetailItemFragment.NAME_BUNDLE_KEY,placeDataList.get(position).getName());
        bundle.putString(DetailItemFragment.TAGS_BUNDLE_KEY,placeDataList.get(position).getTypes());
        bundle.putString(DetailItemFragment.OPEN_HOURS_KEY,"11:00 - 22:00");
        detailItemFragment.setArguments(bundle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ArrayList<PlaceData> adapterPlaceDataList = new ArrayList();
        List<PlaceData> adapterList = adapter.getPlaceDataList();
        adapterPlaceDataList.addAll(adapterList);
        outState.putParcelableArrayList(ADAPTER_PLACE_LIST,adapterPlaceDataList);

    }
}
