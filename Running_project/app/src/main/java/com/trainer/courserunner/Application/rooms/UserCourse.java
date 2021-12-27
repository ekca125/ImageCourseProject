package com.trainer.courserunner.Application.rooms;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "user_course",
        primaryKeys = {
                "user_course_id"
        },
        foreignKeys = {
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "course_id",
                        childColumns = "course_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = CourseMode.class,
                        parentColumns = "course_mode_id",
                        childColumns = "course_mode_id",
                        onDelete = ForeignKey.CASCADE)
        },
        indices = {
                @Index("course_id"),
                @Index("course_mode_id")
        }
)
public class UserCourse {
    @ColumnInfo(name = "user_course_id")
    public Long userCourseId;
    @ColumnInfo(name = "user_course_name")
    public Long userCourseName;
    @ColumnInfo(name = "course_mode_id")
    public Long courseModeId;
    @ColumnInfo(name = "course_id")
    public Long courseId;
}
