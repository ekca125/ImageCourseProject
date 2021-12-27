package com.trainer.courserunner.Application.rooms;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

import java.util.Date;

@DatabaseView("SELECT user_course.user_course_id, user_course.user_course_name, " +
        " user_course.course_mode_id, user_course.course_id, " +
        " user_course_record_id, user_course_record_color, " +
        " user_course_record_latitude, user_course_record_longitude," +
        " user_course_record_date" +
        " FROM user_course JOIN user_course_record USING(user_course_id)")
public class UserCourseDetail {
    @ColumnInfo(name = "user_course_id")
    public Long userCourseId;
    @ColumnInfo(name = "user_course_name")
    public Long userCourseName;
    @ColumnInfo(name = "course_mode_id")
    public Long courseModeId;
    @ColumnInfo(name = "course_id")
    public Long courseId;
    @ColumnInfo(name = "user_course_record_id")
    public Long userCourseRecordId;
    @ColumnInfo(name = "user_course_record_color")
    public Integer userCourseRecordColor;
    @ColumnInfo(name = "user_course_record_latitude")
    public Double userCourseRecordLatitude;
    @ColumnInfo(name = "user_course_record_longitude")
    public Double userCourseRecordLongitude;
    @ColumnInfo(name = "user_course_record_date")
    public Date userCourseRecordDate;
}
