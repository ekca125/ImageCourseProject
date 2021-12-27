package com.trainer.courserunner.Application.rooms;

import android.util.Log;

import com.trainer.courserunner.geo.DistanceConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;


public class UserCourseFlagDerived {
    static public CourseFlag[] getUnvistedUserCourseFlags(Long courseId, Long userCourseId) {
        //
        AppDatabase appDatabase = AppDatabaseConnector.getAppDatabaseConnection();
        CourseFlag[] courseFlags = appDatabase.courseFlagDao().getCourseMarkerFlags(courseId);
        UserCourseRecord[] userCourseRecords = appDatabase.userCourseRecordDao().getUserLocationRecords(userCourseId);
        //
        List<CourseFlag> unvisitedFlagList = new ArrayList<>();
        for (CourseFlag courseFlag : courseFlags) {
            DoubleStream.Builder distanceStreamBuilder = DoubleStream.builder();
            for (UserCourseRecord userCourseRecord : userCourseRecords) {
                double distance = DistanceConverter.getDistance(
                        courseFlag.courseFlagLatitude, courseFlag.courseFlagLongitude,
                        userCourseRecord.userCourseRecordLatitude, userCourseRecord.userCourseRecordLongitude
                );
                distanceStreamBuilder.accept(distance);
            }
            double minDistance = distanceStreamBuilder.build().min().orElse(99999999);
            if (minDistance >= 500) {
                unvisitedFlagList.add(courseFlag);
            } else {
                Log.v("MinDISTANCE", String.valueOf(minDistance));
            }
        }

        CourseFlag[] unvisitedCourseFlags = new CourseFlag[unvisitedFlagList.size()];
        for (int i = 0; i < unvisitedFlagList.size(); i++) {
            unvisitedCourseFlags[i] = unvisitedFlagList.get(i);
        }
        return unvisitedCourseFlags;
    }

    static public int getCountUnvistedUserCourseFlags(Long courseId, Long userCourseId) {
        return getUnvistedUserCourseFlags(courseId, userCourseId).length;
    }
}
