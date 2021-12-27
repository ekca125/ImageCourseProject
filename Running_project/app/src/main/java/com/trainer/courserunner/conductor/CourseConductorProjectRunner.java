package com.trainer.courserunner.conductor;

import android.content.Context;

import com.trainer.courserunner.runactivity.papermap.MapDrawer;

public class CourseConductorProjectRunner extends CourseConductorGuideRunner {
    public CourseConductorProjectRunner(MapDrawer mapDrawer, Long courseId, Long userCourseId, Context context) {
        super(mapDrawer, courseId, userCourseId, context);
    }
}
