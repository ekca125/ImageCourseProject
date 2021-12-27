package supplier;

import data.RangeMap;
import data.RangeMapInfo;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class RangeMapAddressSupplierTest {

    @Test
    void get() {
        RangeMapInfo.Builder builder = new RangeMapInfo.Builder();
        builder.setStartLatitude(37.240826);
        builder.setEndLatitude(37.249826);
        builder.setStartLongitude(126.980255);
        builder.setEndLongitude(126.981255);
        RangeMapInfo rangeMapInfo = builder.build();

        RangeMapAddressSupplier rangeMapAddressSupplier = new RangeMapAddressSupplier(rangeMapInfo);
        try {
            RangeMap rangeMap = rangeMapAddressSupplier.get().get();
            rangeMap.getMapVoList().forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}