package com.trainer.courserunner.Application.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserCourseDetailDao {
    @Query("SELECT * FROM usercoursedetail")
    UserCourseDetail[] getAllUserCourseDetail();

    @Query("SELECT * FROM usercoursedetail WHERE user_course_id=:user_course_id")
    UserCourseDetail[] getUserCourseDetailWhereUserCourseId(long user_course_id);

    @Query("SELECT * FROM usercoursedetail WHERE user_course_id=:user_course_id ORDER BY user_course_record_id ASC")
    UserCourseDetail[] getUserCourseDetailWhereUserCourseIdOrder(long user_course_id);
}
