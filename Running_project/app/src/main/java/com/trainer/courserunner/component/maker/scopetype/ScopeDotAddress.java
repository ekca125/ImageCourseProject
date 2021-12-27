package com.trainer.courserunner.component.maker.scopetype;

public class ScopeDotAddress extends ScopeDot {
    private final double longitude; //경도(x)
    private final double latitude; //위도(y)
    private final ScopeMapInfo scopeImageInfo;

    public ScopeDotAddress(ScopeMapInfo scopeMapInfo,
                           double x, double y) {
        super(normalizeX(scopeMapInfo, x),
                normalizeY(scopeMapInfo, y));
        this.longitude = x;
        this.latitude = y;
        this.scopeImageInfo = scopeMapInfo;
    }

    private static double normalizeX(ScopeMapInfo scopeMapInfo, double x) {
        return (x - scopeMapInfo.getStartX()) / scopeMapInfo.getWidth();
    }

    private static double normalizeY(ScopeMapInfo scopeMapInfo, double y) {
        return (y - scopeMapInfo.getStartY()) / scopeMapInfo.getHeight();
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
