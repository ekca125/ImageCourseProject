package com.trainer.courserunner.rooms;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface CourseDao {
    @Insert
    public long insertCourse(Course course);
}
