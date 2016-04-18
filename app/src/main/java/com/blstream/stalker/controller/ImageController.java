package com.blstream.stalker.controller;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import com.blstream.stalker.R;

import com.blstream.stalker.controller.volley.VolleySingleton;

/**
 * Manages image downloading using Volley
 */
public class ImageController {

    Context context;
    private int placeHolder;
    private int errorImage;

    public ImageController(Context context, int placeHolder, int errorImage) {
        this.context = context;
        this.placeHolder = placeHolder;
        this.errorImage = errorImage;
    }

    /**
     * @param url image url
     */
    public void getImage(String url,ImageView image) {
        ImageLoader loader;
        loader = VolleySingleton.getInstance(context).getImageLoader();
       loader.get(url, ImageLoader.getImageListener(image, placeHolder, R.drawable.background_image));

    }
}
