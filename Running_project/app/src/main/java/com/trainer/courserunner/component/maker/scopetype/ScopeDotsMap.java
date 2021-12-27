package com.trainer.courserunner.component.maker.scopetype;

import com.trainer.courserunner.Application.mapdb.RoadAddressDao;
import com.trainer.courserunner.Application.mapdb.RoadAddressDto;

import java.util.List;
import java.util.stream.Collectors;

public class ScopeDotsMap extends ScopeDots {
    public ScopeDotsMap(ScopeMapInfo scopeMapInfo) {
        super();
        List<ScopeDot> scopeDotList = RoadAddressDao.getScopeAddress(scopeMapInfo).parallelStream()
                .map((RoadAddressDto roadAddressDto) -> new ScopeDotAddress(scopeMapInfo, roadAddressDto.getX(), roadAddressDto.getY()))
                .collect(Collectors.toList());
        this.addAll(scopeDotList);
    }

    public ScopeDotsMap(List<ScopeDotAddress> scopeDotAddressList) {
        super();
        this.addAll(scopeDotAddressList);
    }
}
