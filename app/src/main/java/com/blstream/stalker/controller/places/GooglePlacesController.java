package com.blstream.stalker.controller.places;

import android.util.Log;

import com.blstream.stalker.model.PlaceData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class GooglePlacesController {

    // Google API Key
    private static final String API_KEY = "AIzaSyBsvTBbnekAk_vhVm9hcZR8xy3HRo4KbRo";

    public List<PlaceData> findPlaces(double latitude, double longitude,
                                      String placeSpecification) {

        String urlString = makeUrl(latitude, longitude, placeSpecification);

        try {
            String json = getJSON(urlString);

            System.out.println(json);
            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("results");

            List<PlaceData> arrayList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                try {
                    PlaceData place = PlaceData
                            .jsonToPontoReferencia((JSONObject) array.get(i));
                    Log.v("Places Services ", "" + place);
                    arrayList.add(place);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return arrayList;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    // https://maps.googleapis.com/maps/api/place/search/json?location=28.632808,77.218276&radius=500&types=atm&sensor=false&key=apikey
    private String makeUrl(double latitude, double longitude, String place) {
        StringBuilder urlString = new StringBuilder(
                "https://maps.googleapis.com/maps/api/place/search/json?");

        if (place.equals("")) {
            urlString.append("&location=");
            urlString.append(Double.toString(latitude));
            urlString.append(",");
            urlString.append(Double.toString(longitude));
            urlString.append("&radius=50000");
            // urlString.append("&types="+place);
            urlString.append("&sensor=false&key=" + API_KEY);
        } else {//it's only if you want to search sth
            urlString.append("&location=");
            urlString.append(Double.toString(latitude));
            urlString.append(",");
            urlString.append(Double.toString(longitude));
            urlString.append("&radius=1000");
            urlString.append("&types=").append(place);
            urlString.append("&sensor=false&key=" + API_KEY);
        }
        return urlString.toString();
    }
    protected String getJSON(String url) {
        return getUrlContents(url);
    }

    private String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()), 8);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
