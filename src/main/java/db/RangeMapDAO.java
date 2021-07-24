package db;

import data.RangeMapInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RangeMapDAO {
    private final RangeMapInfo rangeMapInfo;

    public RangeMapDAO(RangeMapInfo rangeMapInfo) {
        this.rangeMapInfo = rangeMapInfo;
    }

    public List<AddressVO> getData() {
        //sql setting (limit 100만)
        String sql = "SELECT longitude, latitude FROM addresstable " +
                "WHERE startLongitude < longitude  AND longitude < endLongitude " +
                "AND startLatitude < latitude  AND latitude< endLatitude " +
                "limit 1000000";
        sql = sql.replace("startLatitude", String.valueOf(rangeMapInfo.getStartLatitude()));
        sql = sql.replace("endLatitude", String.valueOf(rangeMapInfo.getEndLatitude()));
        sql = sql.replace("startLongitude", String.valueOf(rangeMapInfo.getStartLongtitude()));
        sql = sql.replace("endLongitude", String.valueOf(rangeMapInfo.getEndLongtitude()));

        //
        List<AddressVO> mapVoList = new ArrayList<>();

        try (DBConnector dbConnector = new DBConnector();
             Connection connection = dbConnector.getConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                double longitude = resultSet.getDouble("longitude");
                double latitude = resultSet.getDouble("latitude");
                mapVoList.add(new AddressVO(latitude,longitude));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapVoList;
    }
}
