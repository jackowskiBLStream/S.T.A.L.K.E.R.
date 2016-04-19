package com.blstream.stalker.view.fragments;

import android.graphics.Point;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;

import com.blstream.stalker.Data;
import com.blstream.stalker.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowPath;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class PlaceListViewTest extends Data {
    @Mock
    private PlaceListView placeListView;
    @Mock
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mStaggeredLayoutManager = mock(StaggeredGridLayoutManager.class);
        placeListView = mock(PlaceListView.class);
    }


    @Test
    public void shouldSet2ToSpanCountWhenScreenHeightIsBiggerThanWidth(){
        //given
        Point size = new Point();
        doAnswer(new Answer<Point>() {
            public Point answer(InvocationOnMock invocation) {
                return new Point(100,200);
            }
        }).when(placeListView).getActivity().getWindowManager().getDefaultDisplay().getSize(size);
        //when
        placeListView.initialScreenLayout();
        //then
        verify(mStaggeredLayoutManager,timeout(1)).setSpanCount(2);
    }
}