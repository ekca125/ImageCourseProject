package com.trainer.courserunner.component.maker;

import android.graphics.Bitmap;

import com.trainer.courserunner.component.CourseComponent;
import com.trainer.courserunner.component.maker.layer.line.LineConnectLayer;
import com.trainer.courserunner.component.maker.layer.quanzation.QuanzationLayer;
import com.trainer.courserunner.component.maker.layer.regist.CourseRegistLayer;
import com.trainer.courserunner.component.maker.layer.selection.MarkerSelectionLayer;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotsMap;
import com.trainer.courserunner.component.maker.scopetype.ScopeMapInfo;

import java.util.List;
import java.util.function.Consumer;

//출력 : Long , CourseId;
public class CourseMaker extends CourseComponent {
    private Bitmap bitmap;
    private ScopeMapInfo scopeMapInfo;
    private ScopeDotAddress startLocation;

    private QuanzationLayer quanzationLayer;
    private LineConnectLayer lineConnectLayer;
    private CourseRegistLayer courseRegistLayer;
    private MarkerSelectionLayer markerSelectionLayer;

    @Override
    protected Object runInWorkThread() {
        ScopeDotsMap scopeDotsImageMap = quanzationLayer.apply(new ScopeDotsImage(bitmap), new ScopeDotsMap(scopeMapInfo));
        List<ScopeDotAddress> course = lineConnectLayer.apply(scopeDotsImageMap, startLocation);
        Long courseId = courseRegistLayer.apply(course);
        markerSelectionLayer.apply(courseId);
        return courseId;
    }

    public static class Builder {
        private Bitmap bitmap;
        private ScopeMapInfo scopeMapInfo;
        private ScopeDotAddress startLocation;

        private QuanzationLayer quanzationLayer;
        private LineConnectLayer lineConnectLayer;
        private CourseRegistLayer courseRegistLayer;
        private Consumer<Object> finishEvent;
        private MarkerSelectionLayer markerSelectionLayer;

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public void setScopeMapInfo(ScopeMapInfo scopeMapInfo) {
            this.scopeMapInfo = scopeMapInfo;
        }

        public void setLineConnectLayer(LineConnectLayer lineConnectLayer) {
            this.lineConnectLayer = lineConnectLayer;
        }

        public void setQuanzationLayer(QuanzationLayer quanzationLayer) {
            this.quanzationLayer = quanzationLayer;
        }

        public void setCourseRegistLayer(CourseRegistLayer courseRegistLayer) {
            this.courseRegistLayer = courseRegistLayer;
        }

        public void setStartLocation(ScopeDotAddress startLocation) {
            this.startLocation = startLocation;
        }

        public void setMarkerSelectionLayer(MarkerSelectionLayer markerSelectionLayer) {
            this.markerSelectionLayer = markerSelectionLayer;
        }

        public void setFinishEvent(Consumer<Object> finishEvent) {
            this.finishEvent = finishEvent;
        }

        public CourseMaker build() {
            CourseMaker courseMaker = new CourseMaker();
            courseMaker.bitmap = bitmap;
            courseMaker.scopeMapInfo = scopeMapInfo;
            courseMaker.startLocation = startLocation;
            courseMaker.quanzationLayer = quanzationLayer;
            courseMaker.lineConnectLayer = lineConnectLayer;
            courseMaker.courseRegistLayer = courseRegistLayer;
            courseMaker.markerSelectionLayer = markerSelectionLayer;
            courseMaker.setFinishEventConsumer(finishEvent);
            return courseMaker;
        }
    }
}
