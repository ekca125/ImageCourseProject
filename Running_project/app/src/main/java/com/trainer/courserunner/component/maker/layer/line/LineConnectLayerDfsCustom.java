package com.trainer.courserunner.component.maker.layer.line;

import com.trainer.courserunner.component.maker.scopetype.ScopeDot;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.List;

public class LineConnectLayerDfsCustom implements LineConnectLayer {
    double costLimit;

    public LineConnectLayerDfsCustom(double costLimit) {
        this.costLimit = costLimit;
    }

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

            List<ScopeDot> deleteAddresses = new ArrayList<>();
            for (ScopeDot innerLoopAddress : scopeDotsMap) {
                double cost = innerLoopAddress.getCost(currentAddress);
                if (cost < costLimit) {
                    deleteAddresses.add(innerLoopAddress);
                }
            }
            scopeDotsMap.removeAll(deleteAddresses);
        }
        course.add(startAddress);
        return course;
    }
    /*
    @Override
    public List<ScopeDotAddress> apply(ScopeDotsMap scopeDots, ScopeDotAddress startAddress) {
        List<ScopeDotAddress> preprocessCourse = super.apply(scopeDots, startAddress);
        List<ScopeDotAddress> course = new Vector<>();

        while (preprocessCourse.size() > 0) {
            ScopeDotAddress chosenOne = preprocessCourse.get(0);
            course.add(chosenOne);
            preprocessCourse = preprocessCourse.parallelStream()
                    .filter(scopeDotAddress -> scopeDotAddress.getCost(chosenOne) > costLimit)
                    .collect(Collectors.toList());
        }
        return course;
    }
    */
}
