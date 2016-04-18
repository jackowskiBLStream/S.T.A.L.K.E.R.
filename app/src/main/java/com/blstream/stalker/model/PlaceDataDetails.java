package com.blstream.stalker.model;

import com.blstream.stalker.model.interfaces.IOpenHours;
import com.blstream.stalker.model.interfaces.IPlaceDataDetails;
import com.blstream.stalker.model.interfaces.IReviews;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Patryk Gwiazdowski on 13.04.2016.
 * // Good Job Patryk
 */
public class PlaceDataDetails implements IPlaceDataDetails {
    private IOpenHours[] openHours;
    private double rating;
    private ArrayList<IReviews> reviews;

    public PlaceDataDetails(){}
    public PlaceDataDetails(IOpenHours[] openHours, int rating, ArrayList<IReviews> reviews) {
        this.openHours = openHours;
        this.rating = rating;
        this.reviews = reviews;
    }

    /**
     * @return open hours for all days in week
     */
    @Override
    public IOpenHours[] getAllOpenHours() {
        return openHours;
    }

    /**
     * @param day day number 0 - monday ... 6 - sunday
     * @return hours when place is opened at day specified
     */
    @Override
    public IOpenHours getOpenHours(int day) {
        return openHours[day];
    }

    /**
     * @return place rating
     */
    @Override
    public double getRating() {
        return rating;
    }

    /**
     * @return three last place reviews
     */
    @Override
    public Collection<IReviews> getReviews() {
        return reviews;
    }

    public static PlaceDataDetails jsonToPontoReferencia(JSONObject pontoReferencia) {
        try {
            JSONObject geometry = (JSONObject) pontoReferencia.get("geometry");

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
