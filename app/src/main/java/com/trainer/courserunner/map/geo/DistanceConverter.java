package com.trainer.courserunner.map.geo;

public class DistanceConverter {
    // result = m
    static public Double getDistance(Double latitude1, Double longitude1,
                                     Double latitude2, Double longitude2) {
        //위도 1도 = 111.195km = 111195m
        //경도 1도 = 88.804km = 88804m
        Double costLatitude = Math.abs(latitude1 - latitude2) * 111195;
        Double costLongtitude = Math.abs(longitude1 - longitude2) * 88804;
        return Math.sqrt(costLatitude * costLatitude + costLongtitude * costLongtitude);
    }

    static public Double convertMeterToLatitude(Double meter) {
        return (double) meter / 111195;
    }

    static public Double convertMeterToLongitude(Double meter) {
        return (double) meter / 88804;
    }

    static public Double convertKiloMeterToLatitude(Double kilometer) {
        return convertMeterToLatitude(kilometer * 1000);
    }

    static public Double convertKiloMeterToLongitude(Double kilometer) {
        return convertMeterToLongitude(kilometer * 1000);
    }
}
