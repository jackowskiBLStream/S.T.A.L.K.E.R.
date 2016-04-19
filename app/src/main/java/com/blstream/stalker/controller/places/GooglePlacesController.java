package com.blstream.stalker.controller.places;

import android.support.annotation.NonNull;
import android.util.Log;

import com.blstream.stalker.model.PlaceData;
import com.blstream.stalker.model.PlaceDataDetails;
import com.blstream.stalker.model.PlaceDataWithDetails;

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

            JSONObject object = new JSONObject(json);
            JSONArray array = object.getJSONArray("results");

            List<PlaceData> arrayList = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                try {
                    PlaceData place = PlaceData
                            .parseJsonObjects((JSONObject) array.get(i));
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
   public List<PlaceDataWithDetails> findPlacesWithDetails(double latitude, double longitude,
                                                String placeSpecification) {

       String urlPlaceString = makeUrl(latitude, longitude, placeSpecification);

       try {
           String json = getJSON(urlPlaceString);

           JSONObject object = new JSONObject(json);
           JSONArray array = object.getJSONArray("results");

           List<PlaceDataWithDetails> arrayList = new ArrayList<>();
           for (int i = 0; i < array.length(); i++) {
               try {
                   PlaceData place = PlaceData
                           .parseJsonObjects((JSONObject) array.get(i));
                   PlaceDataDetails placeDataDetails;
                   if (place != null) {
                       placeDataDetails = findPlaceDetails(place.getPlace_id());
                       arrayList.add(new PlaceDataWithDetails(place, placeDataDetails));
                   }
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

    public PlaceDataDetails findPlaceDetails(String place_id) throws JSONException {
        String urlString = makeUrl(place_id);
        String json = getJSON(urlString);
        JSONObject object = new JSONObject(json);
        JSONObject result = (JSONObject) object.get("result");
        PlaceDataDetails placeDetails = PlaceDataDetails
                .parseJsonObject(result);
        return placeDetails;
    }

    // https://maps.googleapis.com/maps/api/place/search/json?location=28.632808,77.218276&radius=500&types=atm&sensor=false&key=apikey
    @NonNull
    private String makeUrl(double latitude, double longitude, String place) {
        StringBuilder searchPlaceUrl = new StringBuilder(
                "https://maps.googleapis.com/maps/api/place/search/json?");
        if (place.equals("")) {
            searchPlaceUrl.append("&location=");
            searchPlaceUrl.append(Double.toString(latitude));
            searchPlaceUrl.append(",");
            searchPlaceUrl.append(Double.toString(longitude));
            searchPlaceUrl.append("&radius=50000");
            searchPlaceUrl.append("&sensor=false&key=" + API_KEY);
        } else {//it's only if you want to search sth
            searchPlaceUrl.append("&location=");
            searchPlaceUrl.append(Double.toString(latitude));
            searchPlaceUrl.append(",");
            searchPlaceUrl.append(Double.toString(longitude));
            searchPlaceUrl.append("&radius=1000");
            searchPlaceUrl.append("&types=").append(place);
            searchPlaceUrl.append("&sensor=false&key=" + API_KEY);
        }
        return searchPlaceUrl.toString();
    }

    // https://maps.googleapis.com/maps/api/place/search/json?placeid=dasdasgwe&key=apikey
    private String makeUrl(String place_id) {
        StringBuilder detailsPlaceUrl = new StringBuilder(
                "https://maps.googleapis.com/maps/api/place/details/json?");
        detailsPlaceUrl.append("placeid=");
        detailsPlaceUrl.append(place_id);
        detailsPlaceUrl.append("&key=" + API_KEY);

        return detailsPlaceUrl.toString();
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
