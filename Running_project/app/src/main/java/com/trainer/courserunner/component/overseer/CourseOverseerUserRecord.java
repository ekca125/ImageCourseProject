package com.trainer.courserunner.component.overseer;

import android.graphics.Color;
import android.location.Location;

import com.trainer.courserunner.Application.rooms.AppDatabase;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.rooms.UserCourseRecord;
import com.trainer.courserunner.component.CourseComponent;
import com.trainer.courserunner.geo.DistanceConverter;

import java.util.Date;

//결과값 : Boolean 변경되었을시 : true, 변경되지 않은 경우 : false
public class CourseOverseerUserRecord extends CourseComponent {
    private final Long usercourseId;
    Location currentLocation;
    private Integer currentLineColor;

    public CourseOverseerUserRecord(Long userCourseId) {
        this.usercourseId = userCourseId;
        this.currentLineColor = Color.RED;
    }

    public void setCurrentLineColor(Integer currentLineColor) {
        this.currentLineColor = currentLineColor;
    }

    @Override
    protected Object runInWorkThread() {

        AppDatabase appDatabase = AppDatabaseConnector.getAppDatabaseConnection();

        UserCourseRecord userCourseRecord = new UserCourseRecord();
        userCourseRecord.userCourseId = usercourseId;
        userCourseRecord.userCourseRecordId = appDatabase.userCourseRecordDao().getNextUserLocationOrder(usercourseId);
        userCourseRecord.userCourseRecordLatitude = currentLocation.getLatitude();
        userCourseRecord.userCourseRecordLongitude = currentLocation.getLongitude();
        userCourseRecord.userCourseRecordColor = currentLineColor;
        userCourseRecord.userCourseRecordDate = new Date();

        return appDatabase.userCourseRecordDao().insertDto(userCourseRecord);

    }

    public void refreshLocation(Location location) {
        if (currentLocation == null || checkUpdateDistance(currentLocation, location)) {
            currentLocation = location;
            runComponent();
        }
    }


    private boolean checkUpdateDistance(Location location1, Location location2) {
        final double UPDATE_DISTANCE = 10.0;
        return DistanceConverter.getDistance(location1.getLatitude(), location1.getLongitude(),
                location2.getLatitude(), location2.getLongitude()) >= UPDATE_DISTANCE;
    }
}
