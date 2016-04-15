package com.blstream.stalker.view.interfaces;

import com.blstream.stalker.model.PlaceData;

import java.util.List;

//FIXME: nazwa -> to nie zawsze bedzie fragment
public interface IPlaceListFragment extends IMainFragment {
    void uploadList(List<PlaceData> placeDataList);
}
