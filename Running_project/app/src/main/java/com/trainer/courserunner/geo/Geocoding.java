package com.trainer.courserunner.geo;

import android.content.Context;

import androidx.core.util.Consumer;
import androidx.core.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Geocoding extends BaseGeocoding {
    public Geocoding(Context context, Consumer<String> resultConsumer) {
        super(context, resultConsumer);
    }

    static public Pair<Double, Double> convertJsonToLatitudeLongtitude(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray addresses = jsonObject.getJSONArray("addresses");
            JSONObject address = addresses.getJSONObject(0);
            return new Pair<Double, Double>(address.getDouble("y"), address.getDouble("x"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void requestGeocoding(String address) {
        String url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=ADDRESS&output=json";
        url = url.replace("ADDRESS", address);
        requestUrl(url);
    }
}
