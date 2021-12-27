package com.trainer.courserunner.component.maker.scopetype;

import com.trainer.courserunner.geo.DistanceConverter;

public class ScopeMapInfo implements ScopeInfo {
    private final double startLatitude;
    private final double startLongtitude;
    private final double endLatitude;
    private final double endLongtitude;

    private ScopeMapInfo(double startLatitude,
                         double startLongtitude,
                         double endLatitude,
                         double endLongtitude) {
        this.startLatitude = startLatitude;
        this.startLongtitude = startLongtitude;
        this.endLatitude = endLatitude;
        this.endLongtitude = endLongtitude;
    }

    //m단위
    static public ScopeMapInfo makeScopeMapInfoOriginLeftDown(double latitude, double longitude, double distance) {
        double endLatitude = latitude + DistanceConverter.convertMeterToLatitude(distance);
        double endLongitude = longitude + DistanceConverter.convertMeterToLongitude(distance);
        return new ScopeMapInfo(latitude, longitude, endLatitude, endLongitude);
    }

    public double getEndLatitude() {
        return endLatitude;
    }

    public double getEndLongtitude() {
        return endLongtitude;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public double getStartLongtitude() {
        return startLongtitude;
    }

    @Override
    public double getStartX() {
        return startLongtitude;
    }

    @Override
    public double getStartY() {
        return startLatitude;
    }

    @Override
    public double getEndX() {
        return endLongtitude;
    }

    @Override
    public double getEndY() {
        return endLatitude;
    }

    @Override
    public double getWidth() {
        return getEndX() - getStartX();
    }

    @Override
    public double getHeight() {
        return getEndY() - getStartY();
    }
}
