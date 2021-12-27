package com.trainer.courserunner.component.drawer;

import android.graphics.Color;

import com.trainer.courserunner.Application.rooms.AppDatabase;
import com.trainer.courserunner.Application.rooms.AppDatabaseConnector;
import com.trainer.courserunner.Application.rooms.CourseFlag;
import com.trainer.courserunner.component.drawer.drawtype.DrawingAddress;
import com.trainer.courserunner.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.runactivity.papermap.MapDrawer;

import java.util.ArrayList;
import java.util.List;

public class CourseDrawerGuideLine extends CourseDrawerPolyline {
    Long courseId;

    public CourseDrawerGuideLine(MapDrawer mapDrawer, Long courseId) {
        super(mapDrawer);
        this.courseId = courseId;
    }

    @Override
    protected List<DrawingPath> makeDrawing() {
        //불러오기
        AppDatabase appDatabase = AppDatabaseConnector.getAppDatabaseConnection();
        CourseFlag[] courseFlags = appDatabase.courseFlagDao().getCourseFlags(courseId);
        //생성
        List<DrawingPath> drawingPathList = new ArrayList<>();
        int i = 0;
        while (i < courseFlags.length) {
            Integer currentDrawingColor = Color.BLUE;
            //빌더
            DrawingPath.Builder drawingPathBuilder = new DrawingPath.Builder();
            drawingPathBuilder.setColor(currentDrawingColor);
            drawingPathBuilder.setWidth(10);

            if (i > 0) {
                drawingPathBuilder.accept(new DrawingAddress(courseFlags[i - 1]));
            }
            //컬러 경로 만들기
            int j = i;
            while (j < courseFlags.length) {
                drawingPathBuilder.accept(new DrawingAddress(courseFlags[j]));
                j++;
            }
            drawingPathList.add(drawingPathBuilder.build());
            i++;
        }
        return drawingPathList;
    }
}
