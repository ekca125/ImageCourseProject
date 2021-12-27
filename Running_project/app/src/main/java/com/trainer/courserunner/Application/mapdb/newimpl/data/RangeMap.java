package com.trainer.courserunner.Application.mapdb.newimpl.data;

import java.util.List;

public class RangeMap {
    List<AddressVO> mapVoList;

    public RangeMap(List<AddressVO> mapVoList) {
        this.mapVoList = mapVoList;
    }

    public List<AddressVO> getMapVoList() {
        return mapVoList;
    }
}
