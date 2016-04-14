package com.blstream.stalker.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blstream.stalker.R;
import com.blstream.stalker.model.PlaceData;

import java.util.ArrayList;
import java.util.List;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.MyViewHolder> {

    private List<PlaceData> placeDataList = new ArrayList<>();

    public void setPlaceDataList(List<PlaceData> placeDataList) {
        this.placeDataList = placeDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_element_list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return placeDataList.size();

    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
