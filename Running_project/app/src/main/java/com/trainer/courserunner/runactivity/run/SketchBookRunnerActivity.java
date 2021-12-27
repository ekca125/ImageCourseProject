package com.trainer.courserunner.runactivity.run;

import com.trainer.courserunner.conductor.CourseConductor;
import com.trainer.courserunner.conductor.CourseConductorBuilder;
import com.trainer.courserunner.trainertype.ModeType;

public class SketchBookRunnerActivity extends CourseConductorActivity {
    @Override
    protected CourseConductor createCourseConductor() {
        CourseConductorBuilder courseConductorBuilder = getDefaultCourseConductorBuilder();
        courseConductorBuilder.setModeType(ModeType.SKETCHBOOK);
        return courseConductorBuilder.build();
    }
}
