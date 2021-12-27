package com.trainer.courserunner.component.drawer;

import com.trainer.courserunner.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.runactivity.papermap.MapDrawer;

import java.util.List;

public abstract class CourseDrawerMarker extends CourseDrawer {
    public CourseDrawerMarker(MapDrawer mapDrawer) {
        super(mapDrawer);
    }

    protected void drawOverlay(List<DrawingPath> drawing) {
        for (DrawingPath drawingPath : drawing) {
            if (drawingPath.size() >= 2) {
                List<Object> markers = mapDrawer.drawOverlayMarkers(drawingPath);
                for (Object marker : markers) {
                    overlayObjs.add(marker);
                }
            }
        }
    }
}
