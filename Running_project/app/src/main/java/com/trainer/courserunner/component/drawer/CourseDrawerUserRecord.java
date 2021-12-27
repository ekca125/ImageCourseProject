package com.trainer.courserunner.component.drawer;

import com.trainer.courserunner.Application.rooms.AppDatabase;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.rooms.UserCourseRecord;
import com.trainer.courserunner.component.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.runactivity.papermap.MapDrawer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseDrawerUserRecord extends CourseDrawerPolyline {
    protected Long userCourseId;

    public CourseDrawerUserRecord(MapDrawer mapDrawer, Long userCourseId) {
        super(mapDrawer);
        this.userCourseId = userCourseId;
    }

    @Override
    protected List<DrawingPath> makeDrawing() {
        //불러오기
        AppDatabase appDatabase = AppDatabaseConnector.getAppDatabaseConnection();
        UserCourseRecord[] userLocationRecords = appDatabase.userCourseRecordDao().getUserLocationRecords(userCourseId);
        //생성
        List<DrawingPath> drawingPathList = new ArrayList<>();
        int i = 0;
        while (i < userLocationRecords.length) {
            Integer currentDrawingColor = userLocationRecords[i].userCourseRecordColor;
            //빌더
            DrawingPath.Builder drawingPathBuilder = new DrawingPath.Builder();
            drawingPathBuilder.setColor(currentDrawingColor);
            drawingPathBuilder.setWidth(15);

            if (i > 0) {
                drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[i - 1]));
            }
            //컬러 경로 만들기
            int j = i;
            while (j < userLocationRecords.length && Objects.equals(currentDrawingColor, userLocationRecords[j].userCourseRecordColor)) {
                drawingPathBuilder.accept(new DrawingAddress(userLocationRecords[j]));
                j++;
            }
            drawingPathList.add(drawingPathBuilder.build());
            i++;
        }
        return drawingPathList;
    }
}
