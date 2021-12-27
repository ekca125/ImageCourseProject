package com.trainer.courserunner.component.maker.scopetype;

import android.graphics.Color;

public class ScopeDotPixel extends ScopeDot {
    private final int x;
    private final int y;
    private final int rgb;
    private final ScopeImageInfo scopeImageInfo;


    public ScopeDotPixel(ScopeImageInfo scopeImageInfo, int x, int y, int rgb) {
        super(normalizeX(scopeImageInfo, x), normalizeY(scopeImageInfo, y));
        this.x = x;
        this.y = y;
        this.rgb = rgb;
        this.scopeImageInfo = scopeImageInfo;
    }

    private static Double normalizeX(ScopeImageInfo scopeImageInfo, int x) {
        return (double) x / scopeImageInfo.getWidth();
    }

    private static Double normalizeY(ScopeImageInfo scopeImageInfo, int y) {
        return 1 - ((double) y / scopeImageInfo.getHeight());
    }

    public int getRed() {
        return Color.red(rgb);
    }

    public int getGreen() {
        return Color.green(rgb);
    }

    public int getBlue() {
        return Color.blue(rgb);
    }
}