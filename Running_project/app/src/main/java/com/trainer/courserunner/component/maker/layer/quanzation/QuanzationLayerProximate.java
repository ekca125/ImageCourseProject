package com.trainer.courserunner.component.maker.layer.quanzation;

import com.trainer.courserunner.component.maker.scopetype.ScopeDot;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotAddress;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotsMap;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class QuanzationLayerProximate implements QuanzationLayer {
    @Override
    public ScopeDotsMap apply(ScopeDotsImage scopeDotsImage, ScopeDotsMap scopeDotsMap) {
        HashSet<ScopeDot> quantizationDots = new HashSet<>();
        scopeDotsImage.parallelStream().forEach((ScopeDot scopeDot) -> {
            quantizationDots.add(scopeDotsMap.getClosestDot(scopeDot));
        });
        List<ScopeDotAddress> scopeDotAddressList = quantizationDots.parallelStream().map(
                (ScopeDot scopeDot) -> (ScopeDotAddress) scopeDot)
                .collect(Collectors.toList());
        return new ScopeDotsMap(scopeDotAddressList);
    }
}
