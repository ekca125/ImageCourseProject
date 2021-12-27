package com.trainer.courserunner.geo;

public class DistanceConverter {
    // result = m
    static public double getDistance(double latitude1, double longitude1,
                                     double latitude2, double longitude2) {
        //위도 1도 = 111.195km = 111195m
        //경도 1도 = 88.804km = 88804m
        double costLatitude = Math.abs(latitude1 - latitude2) * 111195;
        double costLongtitude = Math.abs(longitude1 - longitude2) * 88804;
        return Math.sqrt(costLatitude * costLatitude + costLongtitude * costLongtitude);
    }

    static public double convertMeterToLatitude(Double meter) {
        return (double) meter / 111195;
    }

    static public double convertMeterToLongitude(Double meter) {
        return (double) meter / 88804;
    }

    static public double convertKiloMeterToLatitude(Double kilometer) {
        return convertMeterToLatitude(kilometer * 1000);
    }

    static public double convertKiloMeterToLongitude(Double kilometer) {
        return convertMeterToLongitude(kilometer * 1000);
    }
}
