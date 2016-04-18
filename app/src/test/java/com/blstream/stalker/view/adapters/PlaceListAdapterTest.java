package com.blstream.stalker.view.adapters;

import android.content.Context;

import com.blstream.stalker.model.PlaceData;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PlaceListAdapterTest  {
    Context context = mock(Context.class);
    @Before
    public void setUp() throws Exception {
        PlaceListAdapter  adapter = new PlaceListAdapter(context);
    }

    @Test
    public void shouldReplaceListOfPlace(){
        //given
        ArrayList<PlaceData> dataPlaceList = new ArrayList<>();
        dataPlaceList.add(new PlaceData());
    }

}