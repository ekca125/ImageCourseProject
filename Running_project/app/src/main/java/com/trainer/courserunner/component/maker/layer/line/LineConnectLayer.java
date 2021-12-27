package com.trainer.courserunner.component.maker.layer.line;

import com.trainer.courserunner.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotsMap;

import java.util.List;
import java.util.function.BiFunction;

public interface LineConnectLayer extends BiFunction<ScopeDotsMap, ScopeDotAddress, List<ScopeDotAddress>> {
}
