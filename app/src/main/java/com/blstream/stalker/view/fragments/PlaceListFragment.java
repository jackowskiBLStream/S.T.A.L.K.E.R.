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
import com.blstream.stalker.model.OpenHours;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.view.abstractClass.AbstractErrorClass;
import com.blstream.stalker.view.adapters.PlaceListAdapter;
import com.blstream.stalker.view.interfaces.IPlaceListFragment;

import java.util.ArrayList;
import java.util.List;

public class PlaceListFragment extends AbstractErrorClass implements IPlaceListFragment {

    View view;
    PlaceListAdapter adapter = new PlaceListAdapter();
    private StaggeredGridLayoutManager mStaggeredLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.place_list_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.allTasks);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredLayoutManager);
        recyclerView.setAdapter(adapter);
        uploadList(new ArrayList<PlaceData>());
        initialOnItemClickListener();
        initialScreenLayout();
        return view;
    }
    @Override
    public void uploadList(List<PlaceData> placeDataList){
        placeDataList = new ArrayList<>();
        placeDataList.add(new PlaceData("", "BAR LENKA HEHESZKI", new OpenHours("11:00", "19:00"), "BarLenka", new Location("Tmp")));
        placeDataList.add(new PlaceData("", "BAR SRENKA HEHESZKI", new OpenHours("11:00", "19:00"), "BarSrenka", new Location("Tmp")));
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
        bundle.putString(DetailItemFragment.OPEN_HOURS_KEY,placeDataList.get(position).getTodayOpenHours().getOpenTime());
        detailItemFragment.setArguments(bundle);
    }
}
