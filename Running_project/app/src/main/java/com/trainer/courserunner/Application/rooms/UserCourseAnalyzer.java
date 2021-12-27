package com.trainer.courserunner.Application.rooms;

import androidx.core.util.Pair;

import com.trainer.courserunner.geo.DistanceConverter;

import java.util.Date;

public class UserCourseAnalyzer {
    //m기준
    static public double getDistance(long userCourseId) {
        UserCourseDetail[] userCourseDetails = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDetailDao()
                .getUserCourseDetailWhereUserCourseIdOrder(userCourseId);
        if (userCourseDetails.length == 0) {
            return -1;
        }

        double fullDistance = 0;
        for (int i = 1; i < userCourseDetails.length; i++) {
            double latitude1 = userCourseDetails[i - 1].userCourseRecordLatitude;
            double longitude1 = userCourseDetails[i - 1].userCourseRecordLongitude;
            double latitude2 = userCourseDetails[i].userCourseRecordLatitude;
            double longitude2 = userCourseDetails[i].userCourseRecordLongitude;
            fullDistance += DistanceConverter.getDistance(latitude1, longitude1, latitude2, longitude2);
        }
        return fullDistance;
    }

    static public Pair<Date, Date> getStartEndTime(long userCourseId) {
        UserCourseDetail[] userCourseDetails = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDetailDao()
                .getUserCourseDetailWhereUserCourseIdOrder(userCourseId);
        if (userCourseDetails.length == 0) {
            return null;
        }

        Date startDate = userCourseDetails[0].userCourseRecordDate;
        Date endDate = userCourseDetails[userCourseDetails.length - 1].userCourseRecordDate;
        return new Pair<>(startDate, endDate);
    }

    static public Double allExerciseDistance() {
        UserCourse[] userCourses = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDao()
                .getAllUserCourse();
        double distances = 0;
        for (int i = 0; i < userCourses.length; i++) {
            distances += getDistance(userCourses[i].userCourseId);
        }
        return distances;
    }

    static public Long allExersiceTime() {
        UserCourse[] userCourses = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDao()
                .getAllUserCourse();
        long exerciseTimes = 0;
        for (int i = 0; i < userCourses.length; i++) {
            Pair<Date, Date> dateStartEndPair = getStartEndTime(userCourses[i].userCourseId);
            long exerciseTime = DateConverters.dateToTimestamp(dateStartEndPair.second) - DateConverters.dateToTimestamp(dateStartEndPair.first);
            exerciseTimes += exerciseTime;
        }
        return exerciseTimes;

    }

    static public Double getStartEndTimeDistance(Date startDate, Date endDate) {
        UserCourse[] userCourses = AppDatabaseConnector.getAppDatabaseConnection()
                .userCourseDao()
                .getAllUserCourse();

        double allDistance = 0;
        for (UserCourse userCourse : userCourses) {
            UserCourseRecord[] userCourseRecords = AppDatabaseConnector.getAppDatabaseConnection()
                    .userCourseRecordDao()
                    .getUserLocationRecords(userCourse.userCourseId);
            for (int i = 1; i < userCourseRecords.length; i++) {
                UserCourseRecord userCourseRecord1 = userCourseRecords[i - 1];
                UserCourseRecord userCourseRecord2 = userCourseRecords[i];

                if (userCourseRecord2.userCourseRecordDate.after(startDate) && userCourseRecord2.userCourseRecordDate.before(endDate)) {
                    allDistance += DistanceConverter.getDistance(
                            userCourseRecord1.userCourseRecordLatitude,
                            userCourseRecord1.userCourseRecordLongitude,
                            userCourseRecord2.userCourseRecordLatitude,
                            userCourseRecord2.userCourseRecordLongitude);
                }
            }
        }
        return allDistance;
    }
}
