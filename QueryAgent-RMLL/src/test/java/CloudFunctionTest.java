import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.RangeMap;
import data.RangeMapInfo;
import db.AddressVO;
import db.RangeMapDAO;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CloudFunctionTest {

    @Test
    void main() {
        try {
            RangeMapInfo.Builder builder = new RangeMapInfo.Builder();
            builder.setStartLatitude(37.548358);
            builder.setEndLatitude(37.549358);
            builder.setStartLongitude(126.865974);
            builder.setEndLongitude(126.866974);
            RangeMapInfo rangeMapInfo = builder.build();

            //original data
            RangeMapDAO rangeMapDAO = new RangeMapDAO(rangeMapInfo);
            List<AddressVO> addressVOList = rangeMapDAO.getData();

            //test
            Gson gson = new Gson();
            String jsonString = gson.toJson(rangeMapInfo);
            JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
            JsonObject result = CloudFunction.main(jsonObject);

            String compressRangeMapJsonBase64 = result.getAsJsonPrimitive("compressRangeMapJsonBase64").toString();
            String uncompressRangeMapJson = CompressSnappyResultBase64.uncompress(compressRangeMapJsonBase64);
            RangeMap rangeMap = gson.fromJson(uncompressRangeMapJson, RangeMap.class);
            rangeMap.getMapVoList().stream().forEach(System.out::println);

            assertEquals(addressVOList.size(),rangeMap.getMapVoList().size());

            for(int i=0;i<addressVOList.size();i++){
                assertEquals(addressVOList.get(i),rangeMap.getMapVoList().get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}