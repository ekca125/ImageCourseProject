package com.trainer.courserunner.component.maker.layer.selection;

import com.trainer.courserunner.Application.rooms.AppDatabase;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.rooms.CourseFlag;

//아무 마커도 생성하고 싶지 않은 경우
public class MarkerSelectionNone implements MarkerSelectionLayer {
    @Override
    public Long apply(Long courseId) {
        AppDatabase appDatabase = AppDatabaseConnector.getAppDatabaseConnection();
        CourseFlag[] courseFlags = appDatabase.courseFlagDao().getCourseFlags(courseId);
        for (CourseFlag courseFlag : courseFlags) {
            courseFlag.markerFlag = false;
            appDatabase.courseFlagDao().updateDto(courseFlag);
        }
        return courseId;
    }
}
