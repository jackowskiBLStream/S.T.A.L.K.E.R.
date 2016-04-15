package com.blstream.stalker.view.adapters;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blstream.stalker.R;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.view.fragments.DetailItemFragment;

import java.util.ArrayList;
import java.util.List;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.MyViewHolder> {

    private List<PlaceData> placeDataList = new ArrayList<>();
    OnItemClickListener mItemClickListener;

    public void setPlaceDataList(List<PlaceData> placeDataList) {
        this.placeDataList = placeDataList;
        notifyDataSetChanged();
    }

    public List<PlaceData> getPlaceDataList(){
        return this.placeDataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_element_list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (placeDataList.size() != 0) {
            holder.mPubName.setText(placeDataList.get(position).getName());
            holder.mOpenHours.setText(placeDataList.get(position).getTodayOpenHours().getOpenTime());
            holder.mTags.setText(placeDataList.get(position).getTypes());
        }
    }

    @Override
    public int getItemCount() {
        return placeDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            view.setOnClickListener(this);
            initialTransactionName();
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView, getAdapterPosition());
            }
        }
         @TargetApi(Build.VERSION_CODES.LOLLIPOP)
         private void initialTransactionName(){
             if (android.os.Build.VERSION.SDK_INT >= 21) {
                 mPubName.setTransitionName(DetailItemFragment.NAME_TRANSACTION_NAME);
                 mOpenHours.setTransitionName(DetailItemFragment.OPEN_HOURS_TRANSACTION_NAME);
                 mTags.setTransitionName(DetailItemFragment.TAGS_TRANSACTION_NAME);
             }
         }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
