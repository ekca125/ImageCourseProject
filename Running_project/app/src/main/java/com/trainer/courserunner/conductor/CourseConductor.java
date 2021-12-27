package com.trainer.courserunner.conductor;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;

import com.trainer.courserunner.runactivity.papermap.MapDrawer;

public abstract class CourseConductor {
    //정보
    final protected MapDrawer mapDrawer;
    final protected Long userCourseId;
    final protected Context context;
    protected Integer currentColor;


    public CourseConductor(MapDrawer mapDrawer, Long userCourseId, Context context) {
        this.mapDrawer = mapDrawer;
        this.userCourseId = userCourseId;
        this.currentColor = Color.RED;
        this.context = context;
    }

    public void setCurrentColor(Integer currentColor) {
        this.currentColor = currentColor;
    }

    abstract public void refreshLocation(Location location);
}
