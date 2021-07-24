package data;

import db.AddressVO;

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
