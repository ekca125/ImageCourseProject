package com.trainer.courserunner.runactivity.papermap;

import com.trainer.courserunner.component.drawer.drawtype.DrawingPath;

import java.util.List;

public interface MapDrawer {
    //draw
    List<Object> drawOverlayMarkers(DrawingPath drawingPath);

    Object drawOverlayPolyline(DrawingPath drawingPath);

    Object drawOverlayPathline(DrawingPath drawingPath);

    //clear
    void clearDraw(Object drawObject);
}