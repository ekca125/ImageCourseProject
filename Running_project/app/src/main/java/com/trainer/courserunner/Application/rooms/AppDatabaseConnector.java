package com.trainer.courserunner.Application.rooms;

import android.content.Context;

import androidx.room.Room;

import com.trainer.courserunner.trainertype.ModeType;

public class AppDatabaseConnector {
    static private AppDatabase appDatabaseConnection = null;
    static private String dbName = "test9.db";

    static public void initappDatabaseConnection(Context applicationContext) {
        appDatabaseConnection = Room.databaseBuilder(applicationContext, AppDatabase.class, dbName).allowMainThreadQueries().build();

        if (AppDatabaseConnector.getAppDatabaseConnection().courseModeDao().getCourseModeCount() <= 0) {
            //앱DB미리채우기
            CourseMode courseMode;
            courseMode = new CourseMode();
            courseMode.courseModeId = (long) ModeType.GUIDERUNNER.ordinal();
            courseMode.courseModeName = ModeType.GUIDERUNNER.name();
            appDatabaseConnection.courseModeDao().insertDto(courseMode);

            courseMode.courseModeId = (long) ModeType.PROJECTRUNNER.ordinal();
            courseMode.courseModeName = ModeType.PROJECTRUNNER.name();
            appDatabaseConnection.courseModeDao().insertDto(courseMode);

            courseMode.courseModeId = (long) ModeType.SKETCHBOOK.ordinal();
            courseMode.courseModeName = ModeType.SKETCHBOOK.name();
            appDatabaseConnection.courseModeDao().insertDto(courseMode);
            //
        }
    }

    static public AppDatabase getAppDatabaseConnection() {
        return appDatabaseConnection;
    }
}
