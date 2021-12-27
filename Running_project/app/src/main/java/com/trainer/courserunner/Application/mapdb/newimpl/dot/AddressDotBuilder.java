package com.trainer.courserunner.Application.mapdb.newimpl.dot;

import com.trainer.courserunner.Application.mapdb.newimpl.data.RangeMapInfo;

public class AddressDotBuilder {
    private RangeMapInfo rangeMapInfo;
    private double latitude;
    private double longitude;

    public AddressDotBuilder(RangeMapInfo rangeMapInfo) {
        this.rangeMapInfo = rangeMapInfo;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    private double getNormalizeX(double longitude) {
        return (longitude - rangeMapInfo.getStartX()) / (rangeMapInfo.getEndX() - rangeMapInfo.getStartX());
    }

    private double getNormalizeY(double latitude) {
        return (latitude - rangeMapInfo.getStartY()) / (rangeMapInfo.getEndY() - rangeMapInfo.getStartY());
    }

    public AddressDot build(double latitude, double longitude) {
        return new AddressDot(longitude, latitude, getNormalizeX(longitude), getNormalizeY(latitude));
    }
}
