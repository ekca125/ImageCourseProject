package com.trainer.courserunner.component.drawer;

import com.trainer.courserunner.component.CourseComponent;
import com.trainer.courserunner.component.drawer.drawtype.DrawingPath;
import com.trainer.courserunner.runactivity.papermap.MapDrawer;

import java.util.ArrayList;
import java.util.List;

//결과물 없음, 이벤트가 들어오면 다시 그리기만 진행
//consumer의 정의때 changed문을 참조해서 그릴지 그리지 않을지를 판단한다.
public abstract class CourseDrawer extends CourseComponent {
    protected MapDrawer mapDrawer;
    protected List<Object> overlayObjs;

    public CourseDrawer(MapDrawer mapDrawer) {
        this.mapDrawer = mapDrawer;
        this.overlayObjs = new ArrayList<>();
    }

    @Override
    protected Object runInWorkThread() {
        return makeDrawing();
    }

    @Override
    protected void runInUiThread(Object object) {
        super.runInUiThread(object);
        clearOverlay();
        drawOverlay((List<DrawingPath>) object);
    }

    abstract protected List<DrawingPath> makeDrawing();

    abstract protected void drawOverlay(List<DrawingPath> drawing);

    final public void clearOverlay() {
        if (overlayObjs != null) {
            for (Object overlayObj : overlayObjs) {
                mapDrawer.clearDraw(overlayObj);
            }
            overlayObjs.clear();
        }
    }
}
