package com.trainer.courserunner.conductor;

import android.content.Context;

import com.google.firebase.database.annotations.NotNull;
import com.trainer.courserunner.component.drawer.CourseDrawer;
import com.trainer.courserunner.component.drawer.CourseDrawerGuideLine;
import com.trainer.courserunner.component.drawer.CourseDrawerGuideMarker;
import com.trainer.courserunner.component.sounder.CourseSounderGuide;
import com.trainer.courserunner.runactivity.papermap.MapDrawer;

public class CourseConductorGuideRunner extends CourseConductorSketchBook {
    Long courseId;
    CourseDrawer drawerGuideLine;
    CourseDrawer drawerGuideMarker;
    CourseSounderGuide courseSounderGuide;


    public CourseConductorGuideRunner(MapDrawer mapDrawer, @NotNull Long courseId, Long userCourseId, Context context) {
        super(mapDrawer, userCourseId, context);
        this.courseId = courseId;

        drawerGuideLine = new CourseDrawerGuideLine(mapDrawer, courseId);
        drawerGuideMarker = new CourseDrawerGuideMarker(mapDrawer, courseId, userCourseId);
        courseSounderGuide = new CourseSounderGuide(courseId, userCourseId, context);

        overseerUserRecord.setFinishEventConsumer((Object o) -> {
            drawerUserRecord.runComponent();
            drawerGuideLine.runComponent();
            drawerGuideMarker.runComponent();
            courseSounderGuide.runComponent();
        });
    }
}
