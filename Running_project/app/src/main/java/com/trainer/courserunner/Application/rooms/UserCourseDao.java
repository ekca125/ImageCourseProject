package com.trainer.courserunner.Application.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserCourseDao extends BaseDao<UserCourse> {
    @Query("SELECT * FROM user_course")
    UserCourse[] getAllUserCourse();

    @Query("SELECT * FROM user_course WHERE course_mode_id=:course_mode_id")
    UserCourse[] getUserCourse(long course_mode_id);
}
