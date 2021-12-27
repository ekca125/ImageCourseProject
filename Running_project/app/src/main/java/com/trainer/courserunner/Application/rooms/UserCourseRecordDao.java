package com.trainer.courserunner.Application.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface UserCourseRecordDao extends BaseDao<UserCourseRecord> {
    @Query("SELECT IFNULL(MAX(user_course_record_id),0)+1 FROM user_course_record WHERE user_course_id=:user_course_id")
    Long getNextUserLocationOrder(Long user_course_id);

    @Query("SELECT * FROM user_course_record WHERE user_course_id=:user_course_id ORDER BY user_course_record_id")
    UserCourseRecord[] getUserLocationRecords(Long user_course_id);

    @Query("SELECT * FROM user_course_record WHERE user_course_id=:user_course_id limit :limit")
    UserCourseRecord[] getUserLocationRecordsLimit(Long user_course_id, Long limit);

    @Query("SELECT COUNT(*) FROM user_course_record WHERE user_course_id=:user_course_id")
    Long getUserLocationRecordCount(Long user_course_id);
}
