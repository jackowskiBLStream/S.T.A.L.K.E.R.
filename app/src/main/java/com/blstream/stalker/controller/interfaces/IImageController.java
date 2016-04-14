package com.blstream.stalker.controller.interfaces;

import android.widget.ImageView;

/**
 * Manages image downloading
 */
public interface IImageController {


    void getImage(String url, ImageView image);
}
