package com.trainer.courserunner.component.maker.layer.regist;

import com.trainer.courserunner.Application.rooms.AppDatabase;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.rooms.Course;
import com.trainer.courserunner.Application.rooms.CourseFlag;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotAddress;

import java.util.List;

public class CourseRegistLayerAll implements CourseRegistLayer {
    @Override
    public Long apply(List<ScopeDotAddress> course) {
        AppDatabase appDatabase = AppDatabaseConnector.getAppDatabaseConnection();
        Long courseId = appDatabase.courseDao().insertDto(new Course());
        for (int i = 0; i < course.size(); i++) {
            CourseFlag courseFlag = new CourseFlag();
            courseFlag.courseId = courseId;
            courseFlag.courseFlagId = (long) i;
            courseFlag.courseFlagLatitude = course.get(i).getLatitude();
            courseFlag.courseFlagLongitude = course.get(i).getLongitude();
            courseFlag.markerFlag = false;
            appDatabase.courseFlagDao().insertDto(courseFlag);
        }
        return courseId;
    }
}
