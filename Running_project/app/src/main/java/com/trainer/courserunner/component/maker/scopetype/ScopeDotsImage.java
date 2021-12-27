package com.trainer.courserunner.component.maker.scopetype;

import android.graphics.Bitmap;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//병목현상 발생-> 멀티쓰레드로 최적화

public class ScopeDotsImage extends ScopeDots {
    public ScopeDotsImage(Bitmap bitmap) {
        List<Pair<Integer, Integer>> pixelLocations = new ArrayList<>();
        for (int i = 0; i < bitmap.getWidth(); i++) {
            for (int j = 0; j < bitmap.getHeight(); j++) {
                pixelLocations.add(new Pair<>(i, j));
            }
        }

        final ScopeImageInfo scopeImageInfo = new ScopeImageInfo(bitmap.getHeight(), bitmap.getWidth());
        List<ScopeDot> scopeDotList = pixelLocations.parallelStream().map((coordinate) -> {
            int x = coordinate.first;
            int y = coordinate.second;
            int rgb = bitmap.getPixel(x, y);
            return new ScopeDotPixel(scopeImageInfo, x, y, rgb);
        })
                .filter(ScopeDotsImage::filterColorPixel)
                .map((ScopeDotPixel scopeDotPixel) -> (ScopeDot) scopeDotPixel)
                .collect(Collectors.toList());
        this.addAll(scopeDotList);
    }

    public ScopeDotsImage(Bitmap bitmap, int resizeWidth, int resizeHeight) {
        this(Bitmap.createScaledBitmap(bitmap, resizeWidth, resizeHeight, true));
    }

    static private boolean filterColorPixel(ScopeDotPixel scopeDotPixel) {
        final int colorLimit = 100;
        return scopeDotPixel.getRed() <= colorLimit ||
                scopeDotPixel.getBlue() <= colorLimit ||
                scopeDotPixel.getGreen() <= colorLimit;
    }

}
