package com.trainer.courserunner.component.maker.scopetype;

public class ScopeImageInfo implements ScopeInfo {
    int height;
    int width;

    public ScopeImageInfo(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double getWidth() {
        return (double) width;
    }

    @Override
    public double getHeight() {
        return (double) height;
    }

    @Override
    public double getStartX() {
        return 0;
    }

    @Override
    public double getStartY() {
        return 0;
    }

    @Override
    public double getEndX() {
        return (double) width;
    }

    @Override
    public double getEndY() {
        return (double) height;
    }
}
