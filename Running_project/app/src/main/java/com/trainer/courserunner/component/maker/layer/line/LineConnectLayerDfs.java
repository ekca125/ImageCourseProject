package com.trainer.courserunner.component.maker.layer.line;

import com.trainer.courserunner.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class LineConnectLayerDfs implements LineConnectLayer {
    @Override
    public List<ScopeDotAddress> apply(ScopeDotsMap scopeDotsMap, ScopeDotAddress startAddress) {
        scopeDotsMap = (ScopeDotsMap) scopeDotsMap.clone();
        List<ScopeDotAddress> course = new ArrayList<>();
        ScopeDotAddress currentAddress = startAddress;
        course.add(currentAddress);

        while (scopeDotsMap.size() > 0) {
            currentAddress = (ScopeDotAddress) scopeDotsMap.getClosestDot(currentAddress);
            course.add(currentAddress);
            scopeDotsMap.remove(currentAddress);
        }
        course.add(startAddress);
        return course;
    }
}
