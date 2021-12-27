package com.trainer.courserunner.geo;

import android.content.Context;
import android.util.Log;

import androidx.core.util.Consumer;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class BaseGeocoding {
    private final static String clientId = "hzqct6qd8n";
    private final static String secretKey = "h7mPcAg2BDnbJZ2BHLTEe5h3C8QNy98JnpZ4mzuK";
    private RequestQueue requestQueue;
    private Consumer<String> resultConsumer;

    public BaseGeocoding(Context context, Consumer<String> resultConsumer) {
        requestQueue = Volley.newRequestQueue(context);
        this.resultConsumer = resultConsumer;
    }

    public void requestUrl(String url) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() { //응답을 잘 받았을 때 이 메소드가 자동으로 호출
                    @Override
                    public void onResponse(String response) {
                        resultConsumer.accept(response);
                    }
                },
                new Response.ErrorListener() { //에러 발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Network", "Network Error");
                    }
                }
        ) {
            //Header 지정
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> hashMap = new HashMap<>(super.getHeaders());
                hashMap.put("X-NCP-APIGW-API-KEY-ID", clientId);
                hashMap.put("X-NCP-APIGW-API-KEY", secretKey);
                return hashMap;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
    }
}

/*
    https://velog.io/@dlrmwl15/
    %EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Volley
    %EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-HTTP-%ED%86%B5%EC%8B%A0
*/

