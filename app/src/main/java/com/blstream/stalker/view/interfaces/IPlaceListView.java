package com.blstream.stalker.view.interfaces;

import com.blstream.stalker.model.PlaceData;

import java.util.List;

public interface IPlaceListView extends IBasicView {
    /**
     * Updating list of place in Adapter.
     * @param placeDataList List of place
     */
    void updateList(List<PlaceData> placeDataList);
}
