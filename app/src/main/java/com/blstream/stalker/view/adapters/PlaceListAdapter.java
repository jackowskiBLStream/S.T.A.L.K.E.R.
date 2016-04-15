package com.blstream.stalker.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blstream.stalker.R;
import com.blstream.stalker.model.PlaceData;

import java.util.ArrayList;
import java.util.List;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.MyViewHolder> {

    private List<PlaceData> placeDataList = new ArrayList<>();

    public void setPlaceDataList(List<PlaceData> placeDataList) {
        this.placeDataList = placeDataList;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_element_list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            if(placeDataList.size() != 0){
                holder.mPubName.setText(placeDataList.get(position).getName());

               // holder.mOpenHours.setText(placeDataList.get(position).getTodayOpenHours().getOpenTime());

//                holder.mOpenHours.setText(Long.toString(placeDataList.get(position).getTodayOpenHours().getOpenTime()));

                holder.mTags.setText(placeDataList.get(position).getTypes());
            }
    }

    @Override
    public int getItemCount() {
        return placeDataList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mPubName;
        public final TextView mOpenHours;
        public final TextView mTags;
        public final ImageView mPubPlaceImage;
        public MyViewHolder(View view) {
            super(view);
            mView = view;
            mPubName = (TextView) view.findViewById(R.id.nameTextView);
            mOpenHours = (TextView) view.findViewById(R.id.openHourTodayTextView);
            mTags = (TextView) view.findViewById(R.id.tagsTextView);
            mPubPlaceImage = (ImageView) view.findViewById(R.id.pubPlaceImageView);
        }
    }
}
