package db;

import data.RangeMapInfo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RangeMapDAOTest {
    Predicate<AddressVO> getPredicate(RangeMapInfo rangeMapInfo) {
        return a -> rangeMapInfo.getStartLatitude() <= a.getLatitude()
                && a.getLatitude() <= rangeMapInfo.getEndLatitude()
                && rangeMapInfo.getStartLongitude() <= a.getLongitude()
                && a.getLongitude() <= rangeMapInfo.getEndLongitude();
    }

    @Test
    void testDataRange() {
        RangeMapInfo.Builder builder = new RangeMapInfo.Builder();
        builder.setStartLatitude(37.548358);
        builder.setEndLatitude(37.549358);
        builder.setStartLongitude(126.865974);
        builder.setEndLongitude(126.866974);

        RangeMapInfo rangeMapInfo = builder.build();
        RangeMapDAO rangeMapDAO = new RangeMapDAO(rangeMapInfo);
        List<AddressVO> addressVOList = rangeMapDAO.getData();
        boolean check = addressVOList.stream().allMatch(getPredicate(rangeMapInfo));
        assertTrue(check);
    }

    @Test
    void faultTest() throws Exception {
        //Illegal Argument
        RangeMapInfo.Builder builder = new RangeMapInfo.Builder();
        builder.setStartLatitude(37.548358);
        builder.setEndLatitude(37.549358);
        builder.setStartLongitude(126.865974);
        builder.setEndLongitude(126.866974);
        //must be failed
        RangeMapInfo rangeMapInfo = builder.build();
    }
}