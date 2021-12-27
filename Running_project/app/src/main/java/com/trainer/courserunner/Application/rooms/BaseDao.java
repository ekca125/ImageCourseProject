package com.trainer.courserunner.Application.rooms;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface BaseDao<T> {
    @Insert
    Long insertDto(T dto);

    @Update
    void updateDto(T dto);

    @Delete
    void deleteDto(T dto);
}
