import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.RangeMap;
import data.RangeMapInfo;
import db.AddressVO;
import db.RangeMapDAO;
import exception.NotFoundException;

import java.io.IOException;
import java.util.List;

public class CloudFunction {
    public static JsonObject main(JsonObject args) {
        try {
            if (!(args.has("startLatitude")
                    && args.has("endLatitude")
                    && args.has("startLongitude")
                    && args.has("endLongitude"))) {
                throw new IndexOutOfBoundsException();
            }
            double startLatitude = args.getAsJsonPrimitive("startLatitude").getAsDouble();
            double endLatitude = args.getAsJsonPrimitive("endLatitude").getAsDouble();
            double startLongitude = args.getAsJsonPrimitive("startLongitude").getAsDouble();
            double endLongitude = args.getAsJsonPrimitive("endLongitude").getAsDouble();

            RangeMapInfo.Builder builder = new RangeMapInfo.Builder();
            builder.setStartLatitude(startLatitude);
            builder.setEndLatitude(endLatitude);
            builder.setStartLongitude(startLongitude);
            builder.setEndLongitude(endLongitude);

            RangeMapInfo rangeMapInfo = builder.build();
            RangeMapDAO rangeMapDAO = new RangeMapDAO(rangeMapInfo);

            List<AddressVO> mapVoList = rangeMapDAO.getData();
            if (mapVoList.size() <= 0) {
                throw new NotFoundException();
            }

            RangeMap rangeMap = new RangeMap(mapVoList);

            Gson gson = new Gson();
            String rangeMapJson = gson.toJson(rangeMap);
            String compressRangeMapJsonBase64 = CompressSnappyResultBase64.compress(rangeMapJson);

            //응답
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("result", 200);
            jsonObject.addProperty("compressRangeMapJsonBase64", compressRangeMapJsonBase64);
            return jsonObject;
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("result", 400);
            jsonObject.addProperty("message", "Not Enough Arguments");
            return jsonObject;
        } catch (IllegalArgumentException illegalArgumentException) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("result", 400);
            jsonObject.addProperty("message", "Latitude Input or Longitude Input is Illegal");
            return jsonObject;
        } catch (NotFoundException notFoundException) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("result", 404);
            jsonObject.addProperty("message", "Not Found");
            return jsonObject;
        } catch (IOException e) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("result", 500);
            jsonObject.addProperty("message", "Server Error");
            return jsonObject;
        } catch (Exception e) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("result", 500);
            jsonObject.addProperty("message", "Unknown Error");
            return jsonObject;
        }
    }
}
