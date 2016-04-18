package com.blstream.stalker.view.interfaces;

import android.support.v4.app.Fragment;

import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataWithDetails;
import com.blstream.stalker.view.interfaces.IMainFragment;

import java.util.List;

public interface IPlaceListFragment extends IMainFragment {
    void uploadList(List<PlaceData> placeDataList);
}
