package com.trainer.courserunner.Application.mapdb.newimpl.supplier;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.trainer.courserunner.Application.mapdb.newimpl.data.CompressSnappyResultBase64;
import com.trainer.courserunner.Application.mapdb.newimpl.data.RangeMap;
import com.trainer.courserunner.Application.mapdb.newimpl.data.RangeMapInfo;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RangeMapAddressSupplier implements Supplier<Future<RangeMap>> {
    //연결 정보
    private static final String URL = "https://5wa55cnfmh.apigw.ntruss.com/queryagent-rmll/v01/json";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private final Request request;
    //정보
    RangeMapInfo rangeMapInfo;
    //gson
    Gson gson;

    public RangeMapAddressSupplier(RangeMapInfo rangeMapInfo) {
        this.rangeMapInfo = rangeMapInfo;
        //
        this.gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("startLatitude", rangeMapInfo.getStartLatitude());
        jsonObject.addProperty("endLatitude", rangeMapInfo.getEndLatitude());
        jsonObject.addProperty("startLongitude", rangeMapInfo.getStartLongitude());
        jsonObject.addProperty("endLongitude", rangeMapInfo.getEndLongitude());

        //필요 타입
        RequestBody requestBody = RequestBody.create(JSON, jsonObject.toString());
        this.request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .addHeader("x-ncp-apigw-api-key", "ScMpxUPyqupCh28jAmS4AOdxrOTBvDH7DseRHoNF")
                .build();
    }

    @Override
    public Future<RangeMap> get() {
        CompletableFuture<RangeMap> future = new CompletableFuture<>();
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            try (Response response = client.newCall(request).execute()) {
                String responseText = Objects.requireNonNull(response.body()).string();
                Log.v("temp", responseText);
                RangeMap rangeMap = Stream.of(responseText)
                        .map(responseTextData -> gson.fromJson(responseText, JsonObject.class))
                        .map(responseJson -> responseJson.getAsJsonPrimitive("compressRangeMapJsonBase64").getAsString())
                        .map(compressRangeMapJsonBase64 -> {
                            try {
                                return CompressSnappyResultBase64.uncompress(compressRangeMapJsonBase64);
                            } catch (IOException e) {
                                return null;
                            }
                        })
                        .map(uncompressRangeMapJson -> gson.fromJson(uncompressRangeMapJson, RangeMap.class))
                        .collect(Collectors.toList()).get(0);
                future.complete(rangeMap);
            } catch (Exception e) {
                future.complete(null);
            }
        }).start();
        return future;
    }
}
