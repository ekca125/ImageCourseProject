package com.trainer.courserunner.course;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.trainer.courserunner.maps.MapDrawer;
import com.trainer.courserunner.scopetype.ScopeDotAddress;
import com.trainer.courserunner.scopetype.ScopeDotLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseOverseer {
    //User Location
    ScopeDotLocation currentLocation;
    //Map Data
    List<Object> markers;
    List<ScopeDotAddress> markersLocationData;

    public void startCourse(List<ScopeDotAddress> course, ScopeDotLocation currentLocation){
        this.currentLocation=currentLocation;

    }
    public void refreshOversight(double latitude, double longtitude){
        this.currentLocation.refreshLocation(latitude,longtitude);

    }
    //course를 db로 분리해서 쓰는 것이 결과물
    //course와 현재데이터는 지속적으로 DB에 갱신하고 이를 사용하는 식으로 구현되어야 한다.
    //마커, 코스데이터, 그려진 코스 데이터
    //현재까지 지나갔던 데이터까지 저장하게 된다. 대략 15미터 간격으로
    //DB 만들기
}
