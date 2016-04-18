package com.blstream.stalker.view.interfaces;

import android.support.v4.app.Fragment;

import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.view.interfaces.IMainFragment;

import java.util.List;

public interface IPlaceListFragment extends IMainFragment {
    /**
     * Updating list of place in Adapter.
     * @param placeDataList List of place
     */
    void updateList(List<PlaceData> placeDataList);
}
