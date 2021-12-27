package com.trainer.courserunner.Application.rooms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import java.util.Date;

@Entity(tableName = "user_course_record",
        primaryKeys = {
                "user_course_record_id",
                "user_course_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = UserCourse.class,
                        parentColumns = "user_course_id",
                        childColumns = "user_course_id",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index("user_course_id")
        }
)
public class UserCourseRecord {
    @NonNull
    @ColumnInfo(name = "user_course_id")
    public Long userCourseId;
    @NonNull
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
