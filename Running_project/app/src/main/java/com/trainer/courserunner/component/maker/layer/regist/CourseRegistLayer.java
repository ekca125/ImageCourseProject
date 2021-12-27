package com.trainer.courserunner.component.maker.layer.regist;

import com.trainer.courserunner.component.maker.scopetype.ScopeDotAddress;

import java.util.List;
import java.util.function.Function;

public interface CourseRegistLayer extends Function<List<ScopeDotAddress>, Long> {
}
