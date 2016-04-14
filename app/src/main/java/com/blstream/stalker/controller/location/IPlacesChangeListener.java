package com.blstream.stalker.controller.location;

import com.blstream.stalker.model.PlaceData;

import java.util.List;

/**
 *
 */
public interface IPlacesChangeListener {

    void onPlacesChangeListener(List<PlaceData> placeDataList);
}
