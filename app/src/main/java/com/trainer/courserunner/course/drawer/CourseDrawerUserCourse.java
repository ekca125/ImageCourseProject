package com.trainer.courserunner.course.drawer;

import android.os.AsyncTask;

import com.trainer.courserunner.Application.AppDatabaseLoader;
import com.trainer.courserunner.course.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.course.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.map.drawer.MapDrawer;
import com.trainer.courserunner.rooms.AppDatabase;
import com.trainer.courserunner.rooms.UserCourseRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

//맵에 그려주는 기능 수행
public class CourseDrawerUserCourse extends CourseDrawer {
    Long userCourseId;
    List<Object> overlayUserLocationPaths;

    public CourseDrawerUserCourse(MapDrawer mapDrawer, Long userCourseId) {
        super(mapDrawer);
        this.userCourseId = userCourseId;
        this.overlayUserLocationPaths = new ArrayList<>();
    }

    @Override
    protected List<DrawingPath> makeDrawing() {
        //불러오기
        AppDatabase appDatabase = AppDatabaseLoader.getAppDatabase();
        UserCourseRecord[] userLocationRecords = appDatabase.userCourseRecordDao().getUserLocationRecords(userCourseId);
        //생성
        List<DrawingPath> drawingPathList = new ArrayList<>();
        int i = 0;
        while (i < userLocationRecords.length) {
            Integer currentDrawingColor = userLocationRecords[i].userCourseRecordColor;
            //빌더
            DrawingPath.Builder drawingPathBuilder = new DrawingPath.Builder();
            drawingPathBuilder.setColor(currentDrawingColor);
            drawingPathBuilder.setWidth(10);

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

    @Override
    protected void drawOverlay(List<DrawingPath> drawing) {
        for (DrawingPath drawingPath : drawing) {
            if (drawingPath.size() >= 2) {
                overlayUserLocationPaths.add(mapDrawer.drawOverlayPolyline(drawingPath));
            }
        }
    }

    @Override
    protected void clearOverlay() {
        if (overlayUserLocationPaths != null) {
            for (Object overlayUserLocationPath : overlayUserLocationPaths) {
                mapDrawer.clearDraw(overlayUserLocationPath);
            }
            overlayUserLocationPaths.clear();
        }
    }
}
