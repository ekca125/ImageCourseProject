package com.trainer.courserunner.Application.rooms;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "course_mode",
        primaryKeys = {
                "course_mode_id"
        },
        indices = {
                @Index("course_mode_id")
        }
)
public class CourseMode {
    @ColumnInfo(name = "course_mode_id")
    @NonNull
    public Long courseModeId;
    @NonNull
    @ColumnInfo(name = "course_mode_name")
    public String courseModeName;
}
