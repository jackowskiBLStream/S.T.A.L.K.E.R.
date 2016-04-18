package com.blstream.stalker.view.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blstream.stalker.R;
import com.blstream.stalker.controller.ImageController;
import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.view.fragments.DetailItemFragment;

import java.util.ArrayList;
import java.util.List;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.MyViewHolder> {
    private List<PlaceData> placeDataList = new ArrayList<>();
    private ImageController imageController;
    OnItemClickListener mItemClickListener;

    public PlaceListAdapter(Context context) {
        this.imageController = new ImageController(context,R.id.pubPlaceImageView,R.drawable.icon_camera);
    }

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

        if (placeDataList.size() != 0){
//             imageController.getImage(placeDataList.get(position).getIconUrl(),holder.mPubPlaceImage);
//            holder.mPubName.setText(placeDataList.get(position).getName());
//            holder.mOpenHours.setText(placeDataList.get(position).getTodayOpenHours().getOpenTime());
//            holder.mTags.setText(placeDataList.get(position).getTypes());
            generateAndSetMutedColorToCardViewBackground(holder);
        }
    }

    private void generateAndSetMutedColorToCardViewBackground(final MyViewHolder holder){
        Drawable drawable = holder.mPubPlaceImage.getDrawable();
        if(drawable != null) {
            Bitmap photo = ((BitmapDrawable)drawable).getBitmap();
            Palette.generateAsync(photo, new Palette.PaletteAsyncListener() {
                public void onGenerated(Palette palette) {
                    int bgColor = palette.getMutedColor(holder.mView.getContext().getResources().getColor(android.R.color.black));
                    holder.mLinearLayout.setBackgroundColor(bgColor);
                }
            });
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
        public final LinearLayout mLinearLayout;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            mPubName = (TextView) view.findViewById(R.id.nameTextView);
            mLinearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutCardView);
            mOpenHours = (TextView) view.findViewById(R.id.openHourTodayTextView);
            mTags = (TextView) view.findViewById(R.id.tagsTextView);
            mPubPlaceImage = (ImageView) view.findViewById(R.id.pubPlaceImageView);
            mPubName.setBackgroundColor(view.getContext().getResources().getColor(R.color.transparent));
            mOpenHours.setBackgroundColor(view.getContext().getResources().getColor(R.color.transparent));
            mTags.setBackgroundColor(view.getContext().getResources().getColor(R.color.transparent));
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
