package com.trainer.courserunner.Application.rooms;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface CourseFlagDao extends BaseDao<CourseFlag> {
    @Query("SELECT * FROM course_flag WHERE course_id=:course_id ORDER BY course_flag_Id")
    CourseFlag[] getCourseFlags(Long course_id);

    @Query("SELECT * FROM course_flag WHERE course_id=:course_id AND marker_flag==1 ORDER BY course_flag_Id")
    CourseFlag[] getCourseMarkerFlags(Long course_id);

    @Query("SELECT COUNT(*) FROM course_flag WHERE course_id=:course_id AND marker_flag==1")
    int getCountCourseMarkerFlags(Long course_id);
}
