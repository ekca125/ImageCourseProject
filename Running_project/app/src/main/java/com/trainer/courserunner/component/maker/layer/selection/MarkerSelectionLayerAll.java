package com.trainer.courserunner.component.maker.layer.selection;

import com.trainer.courserunner.Application.rooms.AppDatabase;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.rooms.CourseFlag;

public class MarkerSelectionLayerAll implements MarkerSelectionLayer {
    @Override
    public Long apply(Long courseId) {
        AppDatabase appDatabase = AppDatabaseConnector.getAppDatabaseConnection();
        CourseFlag[] courseFlags = appDatabase.courseFlagDao().getCourseFlags(courseId);
        for (CourseFlag courseFlag : courseFlags) {
            courseFlag.markerFlag = true;
            appDatabase.courseFlagDao().updateDto(courseFlag);
        }
        return courseId;
    }
}
