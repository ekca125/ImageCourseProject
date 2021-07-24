import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.RangeMap;
import data.RangeMapInfo;
import db.AddressVO;
import org.junit.jupiter.api.Test;
import org.xerial.snappy.Snappy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

class CloudFunctionTest {

    @Test
    void main() {
        RangeMapInfo.Builder builder = new RangeMapInfo.Builder();
        builder.setStartLatitude(37.548358);
        builder.setEndLatitude(37.549358);
        builder.setStartLongtitude(126.865974);
        builder.setEndLongtitude(126.866974);

        RangeMapInfo rangeMapInfo = builder.build();
        Gson gson = new Gson();
        String jsonString = gson.toJson(rangeMapInfo);
        JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
        JsonObject result = CloudFunction.main(jsonObject);
        System.out.println(result);

        try {
            String compressRangeMapJsonBase64 = result.getAsJsonPrimitive("compressRangeMapJsonBase64").toString();
            String uncompressRangeMapJson = CompressSnappyResultBase64.uncompress(compressRangeMapJsonBase64);
            RangeMap rangeMap = gson.fromJson(uncompressRangeMapJson,RangeMap.class);
            rangeMap.getMapVoList().stream().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}