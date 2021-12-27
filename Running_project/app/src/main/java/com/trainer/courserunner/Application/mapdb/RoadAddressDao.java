package com.trainer.courserunner.Application.mapdb;

import com.trainer.courserunner.Application.mapdb.newimpl.data.AddressVO;
import com.trainer.courserunner.Application.mapdb.newimpl.data.RangeMap;
import com.trainer.courserunner.Application.mapdb.newimpl.data.RangeMapInfo;
import com.trainer.courserunner.Application.mapdb.newimpl.supplier.RangeMapAddressSupplier;
import com.trainer.courserunner.component.maker.scopetype.ScopeMapInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoadAddressDao {
    public static List<RoadAddressDto> getScopeAddress(ScopeMapInfo scopeMapInfo) {
        RangeMapInfo.Builder builder = new RangeMapInfo.Builder();
        builder.setStartLatitude(scopeMapInfo.getStartLatitude());
        builder.setEndLatitude(scopeMapInfo.getEndLatitude());
        builder.setStartLongitude(scopeMapInfo.getStartLongtitude());
        builder.setEndLongitude(scopeMapInfo.getEndLongtitude());

        List<RoadAddressDto> addressDtoList = new ArrayList<>();
        RangeMapAddressSupplier rangeMapAddressSupplier = new RangeMapAddressSupplier(builder.build());
        try {
            RangeMap rangeMap = rangeMapAddressSupplier.get().get();
            List<AddressVO> addressVOList = rangeMap.getMapVoList();

            for (AddressVO addressVO : addressVOList) {
                RoadAddressDto roadAddressDto = new RoadAddressDto();
                roadAddressDto.setLatitude(addressVO.getLatitude());
                roadAddressDto.setLongitude(addressVO.getLongitude());
                addressDtoList.add(roadAddressDto);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return addressDtoList;
    }
}
