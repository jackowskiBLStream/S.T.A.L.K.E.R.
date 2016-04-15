package com.blstream.stalker.controller.volley;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Siwy on 2016-04-14.
 */
public class VolleyImageListener implements ImageLoader.ImageListener {

    private Bitmap image;

    public VolleyImageListener(Bitmap image) {
        this.image = image;
    }

    /**
     * Listens for non-error changes to the loading of the image request.
     *
     * @param response    Holds all information pertaining to the request, as well
     *                    as the bitmap (if it is loaded).
     * @param isImmediate True if this was called during ImageLoader.get() variants.
     *                    This can be used to differentiate between a cached image loading and a network
     *                    image loading in order to, for example, run an animation to fade in network loaded
     */
    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
        image = response.getBitmap();
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
