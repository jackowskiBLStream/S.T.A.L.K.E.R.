package com.blstream.stalker.view.adapters;

import android.content.Context;

import com.blstream.stalker.Data;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataWithDetails;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PlaceListAdapterTest extends Data {
    private static final int PLACE_SIZE = 10;
    @Mock
    Context context;
    PlaceListAdapter  adapter;
    ArrayList<PlaceDataWithDetails> placeDataWithDetailses;
    ArrayList<PlaceData> placeData;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        placeData = new ArrayList<>();
        context = mock(Context.class);
        adapter = new PlaceListAdapter(context);
        placeDataWithDetailses = getData(PLACE_SIZE,3);
    }

    @Test
    public void shouldReplaceList(){
        //given
        for(int i=0;i<PLACE_SIZE;i++) {
            placeData.add(placeDataWithDetailses.get(i).getPlaceData());
        }
        adapter.setPlaceDataList(placeData);
        //when
        int itemCount = adapter.getItemCount();
        //then
        assertEquals(PLACE_SIZE,itemCount);
    }
    @Test
    public void shouldReturn0AsItemCount(){
        //given
        //when
        int itemCount = adapter.getItemCount();
        //then
        assertEquals(0,itemCount);
    }
    @Test
    public void shouldReturnZeroAsCountIfWeReplaceNullArrayToAdapter(){
        //given
        adapter.setPlaceDataList(null);
        //when
        int itemCount = adapter.getItemCount();
        //then
        assertEquals(0,itemCount);
    }
}