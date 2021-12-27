package com.trainer.courserunner.component.maker.layer.quanzation;

import com.trainer.courserunner.component.maker.scopetype.ScopeDot;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotsImage;
import com.trainer.courserunner.component.maker.scopetype.ScopeDotsMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuanzationMininumGuarantee extends QuanzationLayerProximate {
    static final int AREANUMBER = 10;
    static final double PIXEL_LIMIT_PERCENTAGE = 0.75;

    @Override
    public ScopeDotsMap apply(ScopeDotsImage scopeDots, ScopeDotsMap scopeDots2) {
        ScopeDotsMap scopeDotsMapResult = super.apply(scopeDots, scopeDots2);
        List<ScopeDot> scopeDotAddressMininumGuarantee = new ArrayList<>();
        //최소개수 보장
        for (int i = 0; i < AREANUMBER; i++) {
            for (int j = 0; j < AREANUMBER; j++) {
                double areaSize = ((double) 1 / (double) AREANUMBER);
                double startNormalizeX = i * areaSize;
                double startNormalizeY = j * areaSize;
                double endNormalizeX = (i + 1) * areaSize;
                double endNormalizeY = (j + 1) * areaSize;

                List<ScopeDot> extractDot = scopeDotsMapResult.stream()
                        .filter((scopeDot -> {
                            if (startNormalizeX <= scopeDot.getNormalizeX() && scopeDot.getNormalizeX() <= endNormalizeX) {
                                if (startNormalizeY <= scopeDot.getNormalizeY() && scopeDot.getNormalizeY() <= endNormalizeY)
                                    return true;
                            }
                            return false;
                        }))
                        .limit(1)
                        .collect(Collectors.toList());
                scopeDotAddressMininumGuarantee.addAll(extractDot);
            }
        }
        //임의의 점 지우기(랜덤제거)
        Collections.shuffle(scopeDotsMapResult);
        scopeDotsMapResult.subList(0, (int) (PIXEL_LIMIT_PERCENTAGE * scopeDotsMapResult.size()));
        scopeDotsMapResult.addAll(scopeDotAddressMininumGuarantee);
        return scopeDotsMapResult;
    }
}
