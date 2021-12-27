package com.trainer.courserunner.geo;

import android.content.Context;

import androidx.core.util.Consumer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReverseGeocoding extends BaseGeocoding {
    public ReverseGeocoding(Context context, Consumer<String> resultConsumer) {
        super(context, resultConsumer);
    }

    public static String convertJsonToAddress(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray results = jsonObject.getJSONArray("results");
            JSONObject result = results.getJSONObject(0);
            JSONObject region = result.getJSONObject("region");
            String area0 = region.getJSONObject("area0").getString("name");
            String area1 = region.getJSONObject("area1").getString("name");
            String area2 = region.getJSONObject("area2").getString("name");
            String area3 = region.getJSONObject("area3").getString("name");
            String area4 = region.getJSONObject("area4").getString("name");
            return area0 + " " + area1 + " " + area2 + " " + area3 + " " + area4;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void requestReverseGeocoding(Double latitude, Double longtitude) {
        String url = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?coords=LONGTITUDE,LATITUDE&output=json";
        url = url.replace("LATITUDE", latitude.toString());
        url = url.replace("LONGTITUDE", longtitude.toString());
        requestUrl(url);
    }
}

//https://velog.io/@dlrmwl15/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Volley%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-HTTP-%ED%86%B5%EC%8B%A0
