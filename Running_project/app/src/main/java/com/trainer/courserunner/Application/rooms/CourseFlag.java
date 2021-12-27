package com.trainer.courserunner.Application.rooms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "course_flag",
        primaryKeys = {
                "course_id",
                "course_flag_Id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE)
        }
)
public class CourseFlag {
    @NonNull
    @ColumnInfo(name = "course_id")
    public Long courseId;
    @NonNull
    @ColumnInfo(name = "course_flag_Id")
    public Long courseFlagId;
    @NonNull
    @ColumnInfo(name = "marker_flag")
    public Boolean markerFlag;
    @ColumnInfo(name = "course_flag_latitude")
    public Double courseFlagLatitude;
    @ColumnInfo(name = "course_flag_longitude")
    public Double courseFlagLongitude;
}
