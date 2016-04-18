package com.blstream.stalker;

import android.location.Location;
import android.support.annotation.NonNull;

import com.blstream.stalker.model.PlaceData;

/**
 * Created by Patryk Gwiazdowski on 18.04.2016.
 * // Good Job Patryk
 */
public class PlaceDataTest extends PlaceData {

    public PlaceDataTest(@NonNull String icon, @NonNull String types, @NonNull String name, @NonNull Location location) {
        super(icon, types, name, location);
    }

    public void setName(String name){
        this.name = name;
    }
}
