package com.trainer.courserunner.component.drawer.drawtype;

import android.location.Location;

import com.trainer.courserunner.Application.rooms.CourseFlag;
import com.trainer.courserunner.Application.rooms.UserCourseRecord;

public class DrawingAddress {
    Double latitude;
    Double longitude;

    public DrawingAddress(Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public DrawingAddress(UserCourseRecord userCourseRecord) {
        this.latitude = userCourseRecord.userCourseRecordLatitude;
        this.longitude = userCourseRecord.userCourseRecordLongitude;
    }

    public DrawingAddress(CourseFlag courseFlag) {
        this.latitude = courseFlag.courseFlagLatitude;
        this.longitude = courseFlag.courseFlagLongitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
